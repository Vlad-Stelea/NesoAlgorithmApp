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
            System.out.println("kdjvndv");
            if(req.className == null){
                System.out.println("444444");
            }else{
                System.out.println("555555");
            }
            System.out.println("444444");
            AlgorithmDAO db = new AlgorithmDAO();
            if(req.className.equals("")){
                db.createAlgorithm(req.algoName, null);
            }else{
                db.createAlgorithm(req.algoName, req.className);
            }

            response = new CreateAlgorithmResponse(req.algoName);

        } catch (Exception e) {
            e.printStackTrace();
            response = new CreateAlgorithmResponse("Unable to create Algorithm: " + req.algoName + "(" + e.getMessage() + ")", 400);
        }

        return new CreateAlgorithmEvent<>(event.getRequest(), response);
    }

}