package RemoveProblemInstance;

import db.ProblemInstanceDAO;

public class RemoveProblemInstanceHandler {

    ProblemInstanceDAO dao;

    public RemoveProblemInstanceHandler(ProblemInstanceDAO dao) {
        this.dao = dao;
    }

    public RemoveProblemInstanceResponse handle(RemoveProblemInstanceRequest request) {
        RemoveProblemInstanceResponse response;

        try {
            if(dao.removeProblemInstance(request.getProbInstanceUUID())) {
                response = new RemoveProblemInstanceResponse(request.getProbInstanceUUID(), 200);
            } else {
                response = new RemoveProblemInstanceResponse(request.getProbInstanceUUID(), 404, "Problem instance not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new RemoveProblemInstanceResponse("Unable to remove Problem Instance: " + request.getProbInstanceUUID() + "\n(" + e.getMessage() + ")", 400);
        }

        return response;
    }

}
