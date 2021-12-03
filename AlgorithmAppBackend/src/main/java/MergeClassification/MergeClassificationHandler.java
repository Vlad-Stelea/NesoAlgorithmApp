package MergeClassification;


import db.ClassificationDAO;

public class MergeClassificationHandler {

    ClassificationDAO dao;

    public MergeClassificationHandler(ClassificationDAO dao) {
        this.dao = dao;
    }

    public MergeClassificationResponse handle(MergeClassificationRequest request) {
        MergeClassificationResponse response;

        try {
            if(dao.createClassification(request.getNewName(), request.getParentName())){
                if(dao.mergeClassification(request.getClass1(),request.getNewName()) && dao.mergeClassification(request.getClass2(), request.getNewName())) {
                    if(dao.removeClassification(request.getClass1()) && dao.removeClassification(request.getClass2())){
                        response = new MergeClassificationResponse(request.getNewName() + " ," + request.getParentName(), 200);
                    } else {
                        response = new MergeClassificationResponse(request.getNewName() + " (" + request.getParentName() + ")", 404, "Cannot remove original classification.");
                    }

                } else {
                    response = new MergeClassificationResponse(request.getNewName() + " (" + request.getParentName() + ")", 404, "Classification does not exist.");
                }
            } else{
                response = new MergeClassificationResponse(request.getNewName() + " (" + request.getParentName() + ")", 404, "Classification already exists.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = new MergeClassificationResponse("Unable to merge Classification: " + request.getNewName() + " (" + request.getParentName() + ")\n(" + e.getMessage() + ")", 400);
        }
        return response;

    }
}
