package CreateBenchmark;

import db.BenchmarkDAO;

import java.util.UUID;

public class CreateBenchmarkHandler {

    BenchmarkDAO dao;

    public CreateBenchmarkHandler(BenchmarkDAO dao) {
        this.dao = dao;
    }


    public CreateBenchmarkResponse handle(CreateBenchmarkRequest request)  {


        CreateBenchmarkResponse response;
        String uuid = UUID.randomUUID().toString();

        try {
            if(dao.createBenchmark(uuid,request.getBenchmarkName(), request.getTimeToRun() ,request.getDateRun(), request.getAlgoName(), request.getImplName(), request.getMachineConfigName(), request.getProbInstanceUUID())) {
                response = new CreateBenchmarkResponse(200,uuid,request.getBenchmarkName(), request.getTimeToRun() ,request.getDateRun(),request.getAlgoName(),request.getImplName(),request.getMachineConfigName() ,request.getProbInstanceUUID());
            } else {
                response = new CreateBenchmarkResponse( 409,"Benchmark already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new CreateBenchmarkResponse(400,"Unable to create Benchmark: " + request.getBenchmarkName() + " with id " + uuid + "\n(" + e.getMessage() + ")");
        }

        return  response;
    }
}
