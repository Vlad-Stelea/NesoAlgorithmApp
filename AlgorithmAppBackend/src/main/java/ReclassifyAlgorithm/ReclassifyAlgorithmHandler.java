package ReclassifyAlgorithm;

import db.AlgorithmDAO;

public class ReclassifyAlgorithmHandler {

    AlgorithmDAO dao;

    public ReclassifyAlgorithmHandler(AlgorithmDAO dao) {
        this.dao = dao;
    }

    public ReclassifyAlgorithmResponse handle(ReclassifyAlgorithmRequest request) {
        ReclassifyAlgorithmResponse response;

        try {
            if(dao.reclassifyAlgorithm(request.getAlgoName(),request.getNewClassName())) {
                response = new ReclassifyAlgorithmResponse(request.getAlgoName() + "," + request.getNewClassName(), 200);
            } else {
                response = new ReclassifyAlgorithmResponse(request.getAlgoName() + " (" + request.getNewClassName() + ")", 404, "Classification does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ReclassifyAlgorithmResponse("Unable to reclassify Algorithm: " + request.getAlgoName() + " (" + request.getNewClassName() + ")\n(" + e.getMessage() + ")", 400);
        }

        return response;

    }

}
