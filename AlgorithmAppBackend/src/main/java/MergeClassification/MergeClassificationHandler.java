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
            String parent1 = dao.getClassification(request.getClass1()).getParentClassificationName();
            String parent2 = dao.getClassification(request.getClass2()).getParentClassificationName();
            if(parent1.equals(parent2)){
                if(dao.createClassification(request.getNewName(), parent1)){
                    if(dao.mergeClassification(request.getClass1(),request.getNewName()) && dao.mergeClassification(request.getClass2(), request.getNewName())) {
                        if(dao.removeClassification(request.getClass1()) && dao.removeClassification(request.getClass2())){
                            response = new MergeClassificationResponse(request.getNewName(), 200);
                        } else {
                                response = new MergeClassificationResponse(request.getClass1() + " (" + request.getClass2()+ ")", 404, "Cannot remove original classification.");
                        dao.removeClassification(request.getNewName());
                        }

                    } else {
                        response = new MergeClassificationResponse(request.getNewName() + " (" + parent1 + ")", 404, "Classification does not exist.");
                        dao.removeClassification(request.getNewName());
                    }
                } else{
                    response = new MergeClassificationResponse(request.getNewName() , 404, "Classification already exists.");
                }
            }else {
                response = new MergeClassificationResponse(request.getClass1() + " " + request.getClass2() , 404, "Classification parents aren't the same.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = new MergeClassificationResponse("Unable to merge Classification: " + request.getClass1() + " ," + request.getClass2() + "\n (" + e + ")", 400);
        }
        return response;

    }
}
