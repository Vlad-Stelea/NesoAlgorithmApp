package CreateProblemInstance;

import db.ProblemInstanceDAO;

import java.sql.SQLException;

public class CreateProblemInstanceHandler {

    ProblemInstanceDAO dao;
    IProblemInstanceStorage storage;

    public CreateProblemInstanceHandler(ProblemInstanceDAO dao, IProblemInstanceStorage storage) {
        this.dao = dao;
        this.storage = storage;
    }

    public CreateProblemInstanceResponse handle(CreateProblemInstanceRequest request) {
        try {
            if(!dao.hasProblemInstance(request.getProbInstanceUUID())) {
                String url = storage.storeProblemInstance(request.getProbInstanceUUID(), request.getEncodedDatasetContents());

                if(dao.createProblemInstance(request.getProbInstanceUUID(), request.getProbInstanceName(), url, request.getAlgoName())) {
                    return new CreateProblemInstanceResponse(
                            200,
                            request.getProbInstanceUUID(),
                            request.getProbInstanceName(),
                            url,
                            request.getAlgoName()
                    );
                }
                else {
                    return failCreateProblemInstanceResponse(request.getProbInstanceUUID());
                }
            }
            else {
                return failCreateProblemInstanceResponse(request.getProbInstanceUUID());
            }
        } catch (Exception e) {
            return new CreateProblemInstanceResponse(400, "Error when creating Problem Instance: " + e);
        }
    }

    private CreateProblemInstanceResponse failCreateProblemInstanceResponse(String uuid) {
        return new CreateProblemInstanceResponse(409, "Failed to create Problem Instance: Problem Instance " + uuid + " already exists.");
    }

}
