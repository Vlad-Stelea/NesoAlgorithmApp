package RemoveClassification;

import GetHierarchy.GetHierarchyHandler;
import GetHierarchy.GetHierarchyResponse;
import RemoveAlgorithm.RemoveAlgorithmHandler;
import RemoveAlgorithm.RemoveAlgorithmRequest;
import RemoveAlgorithm.RemoveAlgorithmResponse;
import db.*;
import entities.Algorithm;
import entities.Classification;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        String classNameToRemove = req.getClassificationName().replaceAll("%20", " ");

        // make sure we can get the hierarchy, as otherwise we can't delete things
        if(hierarchyResponse.getStatusCode() == 200) {
            List<Classification> topClasses = hierarchyResponse.getTopClassifications();

            // find the classification we want to remove
            Classification classToRemove = null;
            List<Classification> allClasses = getAllClasses(topClasses); // get a "flat" list of classes
            for(Classification c : allClasses) {
                if(c.getClassName().equals(classNameToRemove)) {
                    classToRemove = c;
                    break;
                }
            }

            // if we found the classification, then continue with removal procedures
            if(classToRemove != null) {
                List<String> allSubclassNames = findAllSubclassNames(classToRemove);
                List<String> allAlgoNames = findAllAlgoNames(classToRemove);
                RemoveAlgorithmHandler removeAlgorithmHandler = new RemoveAlgorithmHandler(algoDAO, new ImplementationDAO(), new BenchmarkDAO(), new ProblemInstanceDAO());

                for(String algoName : allAlgoNames) {
                    RemoveAlgorithmResponse removeAlgoResponse = removeAlgorithmHandler.handle(new RemoveAlgorithmRequest(algoName));
                    if(removeAlgoResponse.getHttpCode() != 200) {
                        return new RemoveClassificationResponse(removeAlgoResponse.getHttpCode(), "Error occurred while removing algorithm: " + algoName + "\n" + removeAlgoResponse.getError());
                    }
                }
                // now that we've removed all algorithms, we can remove all subclassifications
                for(String subclassName : allSubclassNames) {
                    try {
                        if(!classDAO.removeClassification(subclassName)) {
                            return new RemoveClassificationResponse(404, "Could not find subclassification \"" + subclassName + "\" to remove.");
                        }
                    }
                    catch(Exception e) {
                        return new RemoveClassificationResponse(400, "Error occurred while removing subclass: " + subclassName);
                    }
                }

                // last but not least, remove the parent Classification
                String parentName = classToRemove.getClassName();
                try {
                    if(classDAO.removeClassification(parentName)) {
                        return new RemoveClassificationResponse(parentName, 200);
                    }
                    else {
                        return new RemoveClassificationResponse(404, "Could not find parent class \"" + parentName + "\" to remove.");
                    }
                }
                catch(Exception e) {
                    return new RemoveClassificationResponse(400, "Error occurred while removing parent classification: " + parentName);
                }
            }
            else {
                return new RemoveClassificationResponse(404, "could not find classification to remove with name: " + classNameToRemove);
            }
        }
        else {
            return new RemoveClassificationResponse(hierarchyResponse.getStatusCode(), "error when getting hierarchy for removal");
        }
    }

    private List<Classification> getAllClasses(List<Classification> topClasses) {
        List<Classification> allClasses = new ArrayList<>();
        Stack<Classification> classStack = new Stack<>();

        // add our top classes to the result list and the stack
        for(Classification c : topClasses) {
            allClasses.add(c);
            classStack.push(c);
        }

        // go through the parent class, its subclasses, and all of their subclasses to add all subclasses onto our list
        while(!classStack.isEmpty()) {
            Classification currClass = classStack.pop();

            // add each subclass to the stack and also grab their names
            for(Classification c : currClass.getSubclassifications()) {
                allClasses.add(c);
                classStack.push(c);
            }
        }

        return allClasses;
    }

    private List<String> findAllSubclassNames(Classification parentClass) {
        List<String> subclassNames = new ArrayList<>();
        Stack<Classification> classStack = new Stack<>();
        classStack.push(parentClass);

        // go through the parentClass, its subclasses, and all of their subclasses to add all subclasses onto our list
        while(!classStack.isEmpty()) {
            Classification currClass = classStack.pop();

            // add each subclass to the stack and also grab their names
            for(Classification c : currClass.getSubclassifications()) {
                subclassNames.add(c.getClassName());
                classStack.push(c);
            }
        }

        return subclassNames;
    }

    private List<String> findAllAlgoNames(Classification parentClass) {
        List<String> algoNames = new ArrayList<>();
        Stack<Classification> classStack = new Stack<>();
        classStack.push(parentClass);

        // go through the parentClass, its subclasses, and all of their subclasses to add all algorithms onto our list
        while(!classStack.isEmpty()) {
            Classification currClass = classStack.pop();

            // add all algos to the return list
            for(Algorithm a : currClass.getAlgorithms()) {
                algoNames.add(a.getAlgoName());
            }

            // add each subclass to our stack
            for(Classification c : currClass.getSubclassifications()) {
                classStack.push(c);
            }
        }

        return algoNames;
    }

}
