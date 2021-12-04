package RemoveBenchmark;

import db.BenchmarkDAO;

public class RemoveBenchmarkHandler {

    BenchmarkDAO dao;

    public RemoveBenchmarkHandler(BenchmarkDAO dao) {
        this.dao = dao;
    }

    public RemoveBenchmarkResponse handle(RemoveBenchmarkRequest request) {
        RemoveBenchmarkResponse response;

        String benchmarkID = request.getBenchmarkID();
        try {
            if(dao.removeBenchmark(benchmarkID)) {
                response = new RemoveBenchmarkResponse(benchmarkID, 200);
            }
            else {
                response = new RemoveBenchmarkResponse(benchmarkID, 404, "Error: Benchmark with ID " + benchmarkID + " not found.");
            }
        }
        catch (Exception e) {
            response = new RemoveBenchmarkResponse(benchmarkID, 400, "Error: Exception occurred when attempting to delete benchmark with ID " + benchmarkID);
        }

        return response;
    }

}
