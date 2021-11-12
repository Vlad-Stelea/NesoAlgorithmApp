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
            if(child.getAlgorithmName() != null){
                //Valid impl
                Algorithm parent = allAlgosHM.get(child.getAlgorithmName());
                parent.addImplementation(child);

            }else{
                //test impl
            }
        }

    }

    private void addAlgorithms(List<Algorithm> allALgosL, HashMap<String, Classification> allClassesHM) {
        //add algorithms to the tree
        for(int i = 0; i < allALgosL.size(); i++){
            Algorithm child = allALgosL.get(i);
            if(child.getParentClassificationName() != null){
                //Valid Algo
                Classification parent = allClassesHM.get(child.getParentClassificationName());
                parent.addAlgorithm(child);

            }else{
                //test algo
            }
        }

    }

    private void buildClassificationTree(ArrayList<Classification> topClasses, List<Classification> allClassL, HashMap<String, Classification> allClassesHM) {
        //build tree of classifications
        for(int i = 0; i < allClassL.size(); i++){
            Classification child = allClassL.get(i);
            if(child.getParentClassificationName() == null){
                //top level classification
                topClasses.add(child);
            }else{
                //subClass
                Classification parent = allClassesHM.get(child.getParentClassificationName());
                parent.addSubclassification(child);
            }
        }
    }

    private HashMap<String, Classification> generateClassHashMap(List<Classification> allClassL) {
        HashMap<String, Classification> allClassesHM = new HashMap<>();
        //add them to hash map
        for(Classification c: allClassL){
            allClassesHM.put(c.getClassName(), c);
        }
        return allClassesHM;
    }

    private HashMap<String, Algorithm> generateAlgoHashMap(List<Algorithm> allAlgosL) {
        HashMap<String, Algorithm> allAlgosHM = new HashMap<>();
        //add them to hash map
        for(Algorithm a: allAlgosL){
            allAlgosHM.put(a.getAlgoName(), a);
        }
        return allAlgosHM;
    }


}
