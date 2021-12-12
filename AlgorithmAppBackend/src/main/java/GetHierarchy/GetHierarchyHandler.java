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


    // TODO Ctrl+/ to uncomment ;)
    public GetHierarchyHandler(ClassificationDAO classificationDAO, AlgorithmDAO algorithmDAO) {
        this.classificationDAO = classificationDAO;
        this.algorithmDAO = algorithmDAO;
    }

    public GetHierarchyResponse handle() {
        GetHierarchyResponse response;

        try {
            List<Classification> allClassL = classificationDAO.getAllClassifications();
            List<Algorithm> allAlgosL = algorithmDAO.getAllAlgorithms();
            HashMap<String, Classification> allClassesHM = generateClassHashMap(allClassL);

            ArrayList<Classification> topClasses = new ArrayList<>();

            addAlgorithms(allAlgosL, allClassesHM);
            buildClassificationTree(topClasses, allClassL, allClassesHM);

            response = new GetHierarchyResponse(topClasses, 200);


        }
        catch (Exception e) {
            e.printStackTrace();
            response = new GetHierarchyResponse(400, "Unable to get hierarchy");
        }

        return response;
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




}
