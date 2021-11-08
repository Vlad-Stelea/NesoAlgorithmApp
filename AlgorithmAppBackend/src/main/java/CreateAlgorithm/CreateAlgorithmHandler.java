package CreateAlgorithm;

import db.AlgorithmDAO;
import entities.Algorithm;

public class CreateAlgorithmHandler{

    AlgorithmDAO dao;

    public CreateAlgorithmHandler(AlgorithmDAO dao) {
        this.dao = dao;
    }

    public CreateAlgorithmResponse handle(CreateAlgorithmRequest req)  {
        CreateAlgorithmResponse response;

        try {
            if(dao.createAlgorithm(req.algoName, req.className)){
                response = new CreateAlgorithmResponse(req.algoName + ", " + req.className, 200);
            }else{
                response = new CreateAlgorithmResponse(req.algoName, 409, "Algorithm Already Exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new CreateAlgorithmResponse("Unable to create Algorithm: " + req.algoName + "(" + e.getMessage() + ")", 400);
        }

        return  response;
    }

}