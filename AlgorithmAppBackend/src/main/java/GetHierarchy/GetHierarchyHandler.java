package GetHierarchy;

import db.AlgorithmDAO;
import db.ClassificationDAO;
import db.ImplementationDAO;
import entities.Algorithm;
import entities.Classification;
import entities.Implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetHierarchyHandler {

    ClassificationDAO classificationDAO;
    AlgorithmDAO algorithmDAO;
    // TODO uncomment
    ImplementationDAO implementationDAO;

    //public GetHierarchyHandler(ClassificationDAO classificationDAO, AlgorithmDAO algorithmDAO) {
    //    this.classificationDAO = classificationDAO;
    //    this.algorithmDAO = algorithmDAO;
    //}

    // TODO Ctrl+/ to uncomment ;)
    public GetHierarchyHandler(ClassificationDAO classificationDAO, AlgorithmDAO algorithmDAO, ImplementationDAO implementationDAO) {
        this.classificationDAO = classificationDAO;
        this.algorithmDAO = algorithmDAO;
        this.implementationDAO = implementationDAO;
    }

    public GetHierarchyResponse handle() {
        GetHierarchyResponse response;

        try {
            // TODO Get implementations too
           // response = new GetHierarchyResponse(classificationDAO.getAllClassifications(),
           //         algorithmDAO.getAllAlgorithms(),
           //         200);
            List<Classification> allClassL = classificationDAO.getAllClassifications();
            List<Algorithm> allAlgosL = algorithmDAO.getAllAlgorithms();
            List<Implementation> allImpsL = implementationDAO.getAllImplementation();
            HashMap<String, Classification> allClassesHM = generateClassHashMap(allClassL);
            HashMap<String, Algorithm> allAlgosHM = generateAlgoHashMap(allAlgosL);

            ArrayList<Classification> topClasses = new ArrayList<>();

            buildClassificationTree(topClasses, allClassL, allClassesHM);
            addAlgorithms(allAlgosL, allClassesHM);
            addImplementations(allImpsL, allAlgosHM);



            response = new GetHierarchyResponse(topClasses, 200);


        }
        catch (Exception e) {
            e.printStackTrace();
            response = new GetHierarchyResponse(400, "Unable to get hierarchy");
        }

        return response;
    }

    private void addImplementations(List<Implementation> allImpsL, HashMap<String, Algorithm> allAlgosHM) {
        //add implementations to the algos
        for(int i = 0; i < allImpsL.size(); i++){
            Implementation child = allImpsL.get(i);
            if(child.getAlgorithmName().getAlgoName() == null){
                //test impl
            }else{
                //Valid impl
                Algorithm parent = allAlgosHM.get(child.getAlgorithmName().getAlgoName());
                parent.addImplementation(child);
                child.setAlgorithmName(null);
            }
        }

    }

    private void addAlgorithms(List<Algorithm> allALgosL, HashMap<String, Classification> allClassesHM) {
        //add algorithms to the tree
        for(int i = 0; i < allALgosL.size(); i++){
            Algorithm child = allALgosL.get(i);
            if(child.getParentClassification() == null){
                //test algo
            }else{
                //Valid Algo
                Classification parent = allClassesHM.get(child.getParentClassification().getClassName());
                parent.addAlgorithm(child);
                child.setParentClassification(null);
            }
        }

    }

    private void buildClassificationTree(ArrayList<Classification> topClasses, List<Classification> allClassL, HashMap<String, Classification> allClassesHM) {
        //build tree of classifications
        for(int i = 0; i < allClassL.size(); i++){
            Classification child = allClassL.get(i);
            if(child.getParentClassification().getClassName() == null){
                //top level classification
                topClasses.add(child);
            }else{
                //subClass
                Classification parent = allClassesHM.get(child.getParentClassification().getClassName());
                parent.addSubclassification(child);
                child.setParentClassification(null);
            }
        }
    }

    private HashMap<String, Classification> generateClassHashMap(List<Classification> allClassL) {
        HashMap<String, Classification> allClassesHM = new HashMap<>();
        //add them to hash map
        for(int i = 0; i < allClassL.size(); i++){
            Classification c = allClassL.get(i);
            allClassesHM.put(c.getClassName(), c);
        }
        return allClassesHM;
    }

    private HashMap<String, Algorithm> generateAlgoHashMap(List<Algorithm> allAlgosL) {
        HashMap<String, Algorithm> allAlgosHM = new HashMap<>();
        //add them to hash map
        for(int i = 0; i < allAlgosL.size(); i++){
            Algorithm c = allAlgosL.get(i);
            allAlgosHM.put(c.getAlgoName(), c);
        }
        return allAlgosHM;
    }


}
