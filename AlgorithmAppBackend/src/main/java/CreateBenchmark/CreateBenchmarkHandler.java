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
            if(dao.createBenchmark(request.getBenchID(),request.getBenchName(), request.getTimeToRun() ,request.getDateRun(), request.getAlgoName(), request.getImplName(), request.getMachineConfigName(), request.getProblemInstanceName())) {
                response = new CreateBenchmarkResponse(200,request.getBenchID(),request.getBenchName(), request.getTimeToRun() ,request.getDateRun(),request.getAlgoName(),request.getImplName(),request.getMachineConfigName() ,request.getProblemInstanceName());
            } else {
                response = new CreateBenchmarkResponse( 409,"Benchmark already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new CreateBenchmarkResponse(400,"Unable to create Benchmark: " + request.getBenchName() + " with id " + request.getBenchID() + "\n(" + e.getMessage() + ")");
        }

        return  response;
    }
}
