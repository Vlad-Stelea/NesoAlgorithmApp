package CreateBenchmark;

import db.BenchmarkDAO;

public class CreateBenchmarkHandler {

    BenchmarkDAO dao;

    public CreateBenchmarkHandler(BenchmarkDAO dao) {
        this.dao = dao;
    }


    public CreateBenchmarkResponse handle(CreateBenchmarkRequest request)  {


        CreateBenchmarkResponse response;

        try {
            if(dao.createBenchmark(request.getBenchID(),request.getBenchName(), request.getTimeToRun() ,request.getDateRun(), request.getAlgoName(), request.getImplName(), request.getMachinceConfigName(), request.getProblemInstanceName())) {
                response = new CreateBenchmarkResponse(request.getBenchID() + "," + request.getBenchName() + "," + request.getTimeToRun() + "," + request.getDateRun() + "," + request.getAlgoName()+ "," +  request.getImplName() + "," +  request.getMachinceConfigName() + "," +request.getProblemInstanceName(), 200);
            } else {
                response = new CreateBenchmarkResponse(request.getBenchID(), 409, "Benchmark already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new CreateBenchmarkResponse("Unable to create Benchmark: " + request.getBenchName() + " with id " + request.getBenchID() + "\n(" + e.getMessage() + ")", 400);
        }

        return  response;
    }
}
