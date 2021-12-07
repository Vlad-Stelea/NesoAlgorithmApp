package RemoveClassification;

import GetHierarchy.GetHierarchyHandler;
import GetHierarchy.GetHierarchyResponse;
import db.AlgorithmDAO;
import db.ClassificationDAO;
import entities.Classification;

import java.util.ArrayList;
import java.util.List;

public class RemoveClassificationHandler {

    AlgorithmDAO algoDAO;
    ClassificationDAO classDAO;

    public RemoveClassificationHandler(AlgorithmDAO algoDAO, ClassificationDAO classDAO) {
        this.algoDAO = algoDAO;
        this.classDAO = classDAO;
    }

    public RemoveClassificationResponse handle(RemoveClassificationRequest req) {
        GetHierarchyHandler hierarchyHandler = new GetHierarchyHandler(this.classDAO, this.algoDAO);
        GetHierarchyResponse hierarchyResponse = hierarchyHandler.handle();
        String classNameToRemove = req.getClassificationName();

        if(hierarchyResponse.getStatusCode() == 200) {
            List<Classification> topClasses = hierarchyResponse.getTopClassifications();

            // find the classification we want to remove
            Classification classToRemove = null;
            for(Classification classification : topClasses) {
                if(classification.getClassName().equals(classNameToRemove)) {
                    classToRemove = classification;
                }
            }

            if(classToRemove != null) {
                // need to iterate through children in the hierarchy with a stack?
                // after getting all child classification names, can get a list of all algo names we want to delete (select algo where parent class = found name, for all found names)
                // remove all algos using Erich's algo handler
                // remove all found class names using our DAO
            }
            else {
                new RemoveClassificationResponse(404, "could not find classification with name: " + classNameToRemove);
            }
        }
        else {
            return new RemoveClassificationResponse(hierarchyResponse.getStatusCode(), "error when getting hierarchy for removal");
        }



        return new RemoveClassificationResponse(400, "error!");
    }

}
