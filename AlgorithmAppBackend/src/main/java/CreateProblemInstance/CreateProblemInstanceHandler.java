package CreateProblemInstance;

import db.ProblemInstanceDAO;

import java.util.UUID;

public class CreateProblemInstanceHandler {

    ProblemInstanceDAO dao;
    IProblemInstanceStorage storage;

    public CreateProblemInstanceHandler(ProblemInstanceDAO dao, IProblemInstanceStorage storage) {
        this.dao = dao;
        this.storage = storage;
    }

    public CreateProblemInstanceResponse handle(CreateProblemInstanceRequest request) {
        try {
            String uuid = UUID.randomUUID().toString();
            String url = this.storage.storeProblemInstance(request.datasetPayload);
            if(dao.createProblemInstance(uuid, request.getProbInstanceName(), url ,request.getAlgoName())) {
                return new CreateProblemInstanceResponse(uuid, request.getProbInstanceName(), url, request.getAlgoName(), 200);
            } else {
                return new CreateProblemInstanceResponse(409, "Problem Instance already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CreateProblemInstanceResponse(400, "Unable to craete problem instance");
        }
    }

}
