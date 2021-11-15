package CreateProblemInstance;

import db.ProblemInstanceDAO;

public class CreateProblemInstanceHandler {

    ProblemInstanceDAO dao;

    public CreateProblemInstanceHandler(ProblemInstanceDAO dao) {
        this.dao = dao;
    }

    public CreateProblemInstanceResponse handle(CreateProblemInstanceRequest request) {
        CreateProblemInstanceResponse response;

        try {
            if(dao.createProblemInstance(request.getProbInstanceUUID(),request.getProbInstanceName(), request.getDatasetURL() ,request.getAlgoName())) {
                response = new CreateProblemInstanceResponse(request.getProbInstanceUUID() + "," + request.getProbInstanceName() + "," + request.getDatasetURL() + "," + request.getAlgoName(), 200);
            } else {
                response = new CreateProblemInstanceResponse(request.getProbInstanceUUID() + " (" + request.getProbInstanceName() + ")", 409, "Problem instance already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new CreateProblemInstanceResponse("Unable to create Problem Instance: " + request.getProbInstanceUUID() + " (" + request.getProbInstanceName() + ")\n(" + e.getMessage() + ")", 400);
        }

        return response;

    }

}
