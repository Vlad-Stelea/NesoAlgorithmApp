package GetAlgorithmPage;

import db.AlgorithmDAO;
import db.ImplementationDAO;
import entities.Algorithm;
import entities.Classification;
import entities.Implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetAlgorithmPageHandler {

    ImplementationDAO implementationDAO;
    AlgorithmDAO algorithmDAO;


    // TODO Ctrl+/ to uncomment ;)
    public GetAlgorithmPageHandler(AlgorithmDAO algorithmDAO, ImplementationDAO implementationDAO) {
        this.implementationDAO = implementationDAO;
        this.algorithmDAO = algorithmDAO;
    }

    public GetAlgorithmPageResponse handle(GetAlgorithmPageRequest req) {
        GetAlgorithmPageResponse response;

        try {
            Algorithm algo = algorithmDAO.getAlgorithm(req.getAlgoName());
            List<Implementation> allImps = implementationDAO.getImplementationForAlgo(req.getAlgoName());

            addImplementations(algo, allImps);
            //TODO add benchmarks and problem instances



            response = new GetAlgorithmPageResponse(algo, 200);


        }
        catch (Exception e) {
            e.printStackTrace();
            response = new GetAlgorithmPageResponse(400, "Unable to get Algorithm Page");
        }

        return response;
    }

    private void addImplementations(Algorithm algo, List<Implementation> allImps) {
        for(Implementation i: allImps){
            algo.addImplementation(i);
        }
    }











}
