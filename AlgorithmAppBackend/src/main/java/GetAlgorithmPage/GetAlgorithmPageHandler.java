package GetAlgorithmPage;

import com.amazonaws.services.amplify.model.transform.BackendEnvironmentMarshaller;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import db.*;
import entities.*;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetAlgorithmPageHandler {

    ImplementationDAO implementationDAO;
    AlgorithmDAO algorithmDAO;
    ProblemInstanceDAO probDAO;
    MachineConfigurationDAO machDAO;
    BenchmarkDAO bmDAO;

    // TODO Ctrl+/ to uncomment ;)
    public GetAlgorithmPageHandler(AlgorithmDAO algorithmDAO, ImplementationDAO implementationDAO, ProblemInstanceDAO probDAO, MachineConfigurationDAO machDAO, BenchmarkDAO bmDAO) {
        this.implementationDAO = implementationDAO;
        this.algorithmDAO = algorithmDAO;
        this.probDAO = probDAO;
        this.machDAO = machDAO;
        this.bmDAO = bmDAO;

    }

    public GetAlgorithmPageResponse handle(GetAlgorithmPageRequest req) {
        GetAlgorithmPageResponse response;
        String decodedName = URLDecoder.decode(req.getAlgoName());
        req.setAlgoName(decodedName);
        try {
            Algorithm algo = algorithmDAO.getAlgorithm(req.getAlgoName());
            List<Implementation> allImps = implementationDAO.getImplementationForAlgo(req.getAlgoName());
            List<ProblemInstance> allProbs = probDAO.getAllAlgosProblemInstances(req.getAlgoName());
            List<MachineConfiguration> allMachs = machDAO.getAllMachineConfigurations();
            List<Benchmark> allBms = bmDAO.getBenchmarkForAlgo(req.getAlgoName());
            addImplementations(algo, allImps);
            algo.setProblemInstances(allProbs);
            HashMap<String, Implementation> impHM = generateImplHM(allImps);
            setBenchmarks(allBms, impHM);

            AlgorithmPage page = new AlgorithmPage();
            page.setAlgorithm(algo);
            page.setMachineConfigurations(allMachs);

            response = new GetAlgorithmPageResponse(page, 200);


        }
        catch (Exception e) {
            e.printStackTrace();
            response = new GetAlgorithmPageResponse(400, "Unable to get Algorithm Page: " + req.getAlgoName());
        }

        return response;
    }

    private void setBenchmarks(List<Benchmark> allBms, HashMap<String, Implementation> impHM) {
        for(Benchmark bm: allBms){
            Implementation i = impHM.get(bm.getImplName());
            i.addBenchmark(bm);
        }
    }

    private HashMap<String, Implementation> generateImplHM(List<Implementation> allImps) {
        HashMap<String, Implementation> impHM = new HashMap<>();
        for(Implementation i: allImps){
            impHM.put(i.getImplName(), i);
        }
        return impHM;
    }

    private void addImplementations(Algorithm algo, List<Implementation> allImps) {
        for(Implementation i: allImps){
            algo.addImplementation(i);
        }
    }











}
