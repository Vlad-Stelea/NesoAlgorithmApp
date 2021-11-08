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
            AlgorithmDAO db = new AlgorithmDAO();
            db.createAlgorithm(req.algoName, req.className);

            response = new CreateAlgorithmResponse(req.algoName);

        } catch (Exception e) {
            e.printStackTrace();
            response = new CreateAlgorithmResponse("Unable to create Algorithm: " + req.algoName + "(" + e.getMessage() + ")", 400);
        }

        return  response;
    }

}