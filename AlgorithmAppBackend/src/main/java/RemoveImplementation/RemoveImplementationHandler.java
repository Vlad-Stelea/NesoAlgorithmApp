package RemoveImplementation;

import db.BenchmarkDAO;
import db.ImplementationDAO;

public class RemoveImplementationHandler {

    ImplementationDAO implDAO;
    BenchmarkDAO benchmarkDAO;

    public RemoveImplementationHandler(ImplementationDAO implDAO, BenchmarkDAO benchmarkDAO) {
        this.implDAO = implDAO;
        this.benchmarkDAO = benchmarkDAO;
    }

    public RemoveImplementationResponse handle(RemoveImplementationRequest request) {
        RemoveImplementationResponse response;

        String implName = request.getImplName();
        String algoName = request.getAlgoName();

        try {
            if(benchmarkDAO.removeBenchmarksByImplName(implName, algoName) && implDAO.removeImplementation(implName, algoName)) {
                response = new RemoveImplementationResponse(implName, algoName, 200);
            } else {
                response = new RemoveImplementationResponse(404, "Implementation not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new RemoveImplementationResponse(400, "Unable to remove Implementation: " + implName + " from " + algoName + ".\n(" + e.getMessage() + ")");
        }

        return response;
    }

}
