package CreateClassification;

import db.ClassificationDAO;

public class CreateClassificationHandler {

    ClassificationDAO dao;

    public CreateClassificationHandler(ClassificationDAO dao) {
        this.dao = dao;
    }

    public CreateClassificationResponse handle(CreateClassificationRequest request) {
        CreateClassificationResponse response;

        try {
            if(dao.createClassification(request.getName(), request.getParentClassName())) {
                response = new CreateClassificationResponse(request.getName() + "," + request.getParentClassName(), 200);
            } else {
                response = new CreateClassificationResponse(request.getName(), 409, "Classification already exists.");
            }
        } catch (Exception e) {
            response = new CreateClassificationResponse("Unable to create classification: " + request.getName() + " with parent " + request.getParentClassName() + "\n(" + e.getMessage() + ")", 400);
        }

        return response;
    }

}
