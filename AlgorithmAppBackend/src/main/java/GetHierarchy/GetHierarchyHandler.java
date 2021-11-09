package GetHierarchy;

import db.AlgorithmDAO;
import db.ClassificationDAO;
import entities.Algorithm;
import entities.Classification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetHierarchyHandler {

    ClassificationDAO classificationDAO;
    AlgorithmDAO algorithmDAO;
    // TODO uncomment
//    ImplementationDAO implementationDAO;

    public GetHierarchyHandler(ClassificationDAO classificationDAO, AlgorithmDAO algorithmDAO) {
        this.classificationDAO = classificationDAO;
        this.algorithmDAO = algorithmDAO;
    }

    // TODO Ctrl+/ to uncomment ;)
//    public GetHierarchyHandler(ClassificationDAO classificationDAO, AlgorithmDAO algorithmDAO, ImplementationDAO implementationDAO) {
//        this.classificationDAO = classificationDAO;
//        this.algorithmDAO = algorithmDAO;
//        this.implementationDAO = implementationDAO;
//    }

    public GetHierarchyResponse handle() {
        GetHierarchyResponse response;

        try {
            // TODO Get implementations too
           // response = new GetHierarchyResponse(classificationDAO.getAllClassifications(),
           //         algorithmDAO.getAllAlgorithms(),
           //         200);
            List<Classification> allClassL = classificationDAO.getAllClassifications();
            List<Algorithm> allALgosL = algorithmDAO.getAllAlgorithms();
            HashMap<String, Classification> allClassesHM = generateHashMap(allClassL);

            ArrayList<Classification> topClasses = new ArrayList<>();
            System.out.println("here 1");
            buildClassificationTree(topClasses, allClassL, allClassesHM);
            System.out.println("here 2");
            addAlgorithms(allALgosL, allClassesHM);
            System.out.println("here 3");



            response = new GetHierarchyResponse(topClasses, null, 200);


        }
        catch (Exception e) {
            response = new GetHierarchyResponse(400, "Unable to get hierarchy");
        }

        return response;
    }

    private void addAlgorithms(List<Algorithm> allALgosL, HashMap<String, Classification> allClassesHM) {
        //build tree of classifications
        for(int i = 0; i < allALgosL.size(); i++){
            System.out.println("here 2a");
            Algorithm child = allALgosL.get(i);
            System.out.println("here 2aa");
            if(child.getParentClassification() == null){
                //test algo
            }else{
                System.out.println("here 2b");
                //Valid Algo
                Classification parent = allClassesHM.get(child.getParentClassification().getClassName());
                System.out.println("here 2c");
                parent.addAlgorithm(child);
                System.out.println("here 2d");
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

    private HashMap<String, Classification> generateHashMap(List<Classification> allClassL) {
        HashMap<String, Classification> allClassesHM = new HashMap<>();
        //add them to hash map
        for(int i = 0; i < allClassL.size(); i++){
            Classification c = allClassL.get(i);
            allClassesHM.put(c.getClassName(), c);
        }
        return allClassesHM;
    }


}
