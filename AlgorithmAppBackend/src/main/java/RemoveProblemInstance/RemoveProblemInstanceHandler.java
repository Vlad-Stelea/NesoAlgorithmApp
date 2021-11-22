package RemoveProblemInstance;

import db.BenchmarkDAO;
import db.ProblemInstanceDAO;

public class RemoveProblemInstanceHandler {

    ProblemInstanceDAO problemInstanceDAO;
    BenchmarkDAO benchmarkDAO;

    public RemoveProblemInstanceHandler(ProblemInstanceDAO problemInstanceDAO, BenchmarkDAO benchmarkDAO) {
        this.problemInstanceDAO = problemInstanceDAO;
        this.benchmarkDAO = benchmarkDAO;
    }

    public RemoveProblemInstanceResponse handle(RemoveProblemInstanceRequest request) {
        RemoveProblemInstanceResponse response;
        String problemInstanceID = request.getProblemInstanceID();
        try {
            if(benchmarkDAO.removeBenchmarksByProbInstanceUUID(problemInstanceID) && problemInstanceDAO.removeProblemInstance(problemInstanceID)) {
                response = new RemoveProblemInstanceResponse(problemInstanceID, 200);
            } else {
                response = new RemoveProblemInstanceResponse(problemInstanceID, 404, "Problem instance not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new RemoveProblemInstanceResponse("Unable to remove Problem Instance: " + problemInstanceID + "\n(" + e.getMessage() + ")", 400);
        }

        return response;
    }

}
