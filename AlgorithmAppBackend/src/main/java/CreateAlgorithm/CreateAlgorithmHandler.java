package CreateAlgorithm;

import db.AlgorithmDAO;
import entities.Algorithm;

public class CreateAlgorithmHandler{

    AlgorithmDAO dao;

    public CreateAlgorithmHandler(AlgorithmDAO dao) {
        this.dao = dao;
    }


    public CreateAlgorithmEvent<CreateAlgorithmRequest, CreateAlgorithmResponse> handle(CreateAlgorithmEvent<CreateAlgorithmRequest, CreateAlgorithmResponse> event)  {


        CreateAlgorithmResponse response;
        CreateAlgorithmRequest req = event.getRequest();

        try {
            AlgorithmDAO db = new AlgorithmDAO();
            db.createAlgorithm(req.algoName, req.className);
            response = new CreateAlgorithmResponse(req.algoName);

        } catch (Exception e) {
            response = new CreateAlgorithmResponse("Unable to create Algorithm: " + req.algoName + "(" + e.getMessage() + ")", 400);
        }

        return new CreateAlgorithmEvent<>(event.getRequest(), response);
    }

}