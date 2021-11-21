package RemoveImplementation;

import db.ImplementationDAO;

public class RemoveImplementationHandler {

    ImplementationDAO dao;

    public RemoveImplementationHandler(ImplementationDAO dao) {
        this.dao = dao;
    }

    public RemoveImplementationResponse handle(RemoveImplementationRequest request) {
        RemoveImplementationResponse response;
        // TODO this is a hack because we have the remove Implementation set to take a path ID, but our key is impl/algo name
        String implName = request.getImplName();
        String algoName = request.getAlgoName();

        try {
            if(dao.removeImplementation(implName, algoName)) {
                response = new RemoveImplementationResponse(request.getImplementationID(), 200);
            } else {
                response = new RemoveImplementationResponse(request.getImplementationID(), 404, "Problem instance not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new RemoveImplementationResponse("Unable to remove Problem Instance: " + request.getImplementationID() + "\n(" + e.getMessage() + ")", 400);
        }

        return response;
    }

}
