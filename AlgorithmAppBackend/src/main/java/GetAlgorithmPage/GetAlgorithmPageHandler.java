package GetAlgorithmPage;

import db.AlgorithmDAO;
import db.ImplementationDAO;
import db.MachineConfigurationDAO;
import db.ProblemInstanceDAO;
import entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetAlgorithmPageHandler {

    ImplementationDAO implementationDAO;
    AlgorithmDAO algorithmDAO;
    ProblemInstanceDAO probDAO;
    MachineConfigurationDAO machDAO;


    // TODO Ctrl+/ to uncomment ;)
    public GetAlgorithmPageHandler(AlgorithmDAO algorithmDAO, ImplementationDAO implementationDAO, ProblemInstanceDAO probDAO, MachineConfigurationDAO machDAO) {
        this.implementationDAO = implementationDAO;
        this.algorithmDAO = algorithmDAO;
        this.probDAO = probDAO;
        this.machDAO = machDAO;
    }

    public GetAlgorithmPageResponse handle(GetAlgorithmPageRequest req) {
        GetAlgorithmPageResponse response;

        try {
            Algorithm algo = algorithmDAO.getAlgorithm(req.getAlgoName());
            List<Implementation> allImps = implementationDAO.getImplementationForAlgo(req.getAlgoName());
            List<ProblemInstance> allProbs = probDAO.getAllAlgosProblemInstances(req.getAlgoName());
            List<MachineConfiguration> allMachs = machDAO.getAllMachineConfigurations();
            addImplementations(algo, allImps);
            algo.setProblemInstances(allProbs);
            //TODO add benchmarks

            AlgorithmPage page = new AlgorithmPage();
            page.setAlgorithm(algo);
            page.setMachineConfigurations(allMachs);

            response = new GetAlgorithmPageResponse(page, 200);


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
