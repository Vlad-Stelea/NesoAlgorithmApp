package GetHierarchy;

import db.AlgorithmDAO;
import db.ClassificationDAO;

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
            response = new GetHierarchyResponse(classificationDAO.getAllClassifications(),
                    algorithmDAO.getAllAlgorithms(),
                    200);
        }
        catch (Exception e) {
            response = new GetHierarchyResponse(400, "Unable to get hierarchy");
        }

        return response;
    }


}
