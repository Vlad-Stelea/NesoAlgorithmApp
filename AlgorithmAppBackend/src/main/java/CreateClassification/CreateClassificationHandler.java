package CreateClassification;

import db.ClassificationDAO;

public class CreateClassificationHandler {

    ClassificationDAO dao;

    public CreateClassificationHandler(ClassificationDAO dao) {
        this.dao = dao;
    }

    public CreateClassificationEvent<CreateClassificationRequest, CreateClassificationResponse> handle(CreateClassificationEvent<CreateClassificationRequest, CreateClassificationResponse> request) {
        CreateClassificationResponse response;

        CreateClassificationRequest req = request.getRequest();

        try {
            if(dao.createClassification(req.getName(), req.getParentClassName())) {
                response = new CreateClassificationResponse(req.getName() + "," + req.getParentClassName(), 200);
            } else {
                response = new CreateClassificationResponse(req.getName(), 409, "Classification already exists.");
            }
        } catch (Exception e) {
            response = new CreateClassificationResponse("Unable to create classification: " + req.getName() + " with parent " + req.getParentClassName() + "\n(" + e.getMessage() + ")", 400);
        }

        return new CreateClassificationEvent<>(request.getRequest(), response);
    }

}
