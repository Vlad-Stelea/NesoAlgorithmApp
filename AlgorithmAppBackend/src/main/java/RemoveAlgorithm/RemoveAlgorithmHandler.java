package RemoveAlgorithm;

import db.AlgorithmDAO;
import db.BenchmarkDAO;
import db.ImplementationDAO;
import db.ProblemInstanceDAO;

public class RemoveAlgorithmHandler {

    AlgorithmDAO algoDAO;
    ImplementationDAO implDAO;
    BenchmarkDAO benchmarkDAO;
    ProblemInstanceDAO probDAO;

    public RemoveAlgorithmHandler(AlgorithmDAO algoDAO, ImplementationDAO implDAO, BenchmarkDAO benchmarkDAO, ProblemInstanceDAO probDAO) {
        this.algoDAO = algoDAO;
        this.implDAO = implDAO;
        this.benchmarkDAO = benchmarkDAO;
        this.probDAO = probDAO;
    }

    public RemoveAlgorithmResponse handle(RemoveAlgorithmRequest request) {
        RemoveAlgorithmResponse response;
        // TODO this is a hack because we have the remove Implementation set to take a path ID in our YAML, but our db key is impl/algo name
        String algoName = request.getAlgoName();

        try {
            benchmarkDAO.removeBenchmarksByAlgorithm(algoName);
            implDAO.removeImplementationsByAlgorithm(algoName);
            probDAO.removeProblemInstancesByAlgorithm(algoName);
            if(algoDAO.removeAlgorithm(algoName)){
                response = new RemoveAlgorithmResponse(algoName, 200);
            }else{
                response = new RemoveAlgorithmResponse(algoName, 404, "Algorithm not found.");

            }

        } catch (Exception e) {
            e.printStackTrace();
            response = new RemoveAlgorithmResponse("Unable to remove Algorithm: " + algoName + ".\n(" + e.getMessage() + ")", 400);
        }

        return response;
    }

}
