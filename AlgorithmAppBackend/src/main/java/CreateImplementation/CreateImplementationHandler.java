package CreateImplementation;


import db.ImplementationDAO;


public class CreateImplementationHandler{

    ImplementationDAO dao;
    IImplementationStorage storage;

    public CreateImplementationHandler(ImplementationDAO dao, IImplementationStorage storage) {
        this.dao = dao;
        this.storage = storage;
    }


    public CreateImplementationResponse handle(CreateImplementationRequest request)  {
        try {
            // Successful case of creating an implementation
            if(!dao.hasImplementation(request.getImplName(), request.getAlgoName())) {
                String url = storage.storeImplementation(request.getImplName(), request.getCode());
                // Try to create an implementation
                if(dao.createImplementation(request.getImplName(),url, request.getLanguage() ,request.getAlgoName())) {
                    return new CreateImplementationResponse(
                            200,
                            request.getImplName(),
                            request.getAlgoName(),
                            url,
                            request.getLanguage()
                    );
                } else {
                    return createImplementationAlreadyExistsError();
                }
            } else {
                // Case where an implementation already exists in the db
                return createImplementationAlreadyExistsError();
            }
        } catch (Exception e) {
            // Case where an unknown error occurs
            e.printStackTrace();
            return new CreateImplementationResponse(
                    400,
                    "Unknown Error"
            );
        }
    }

    private CreateImplementationResponse createImplementationAlreadyExistsError() {
        return new CreateImplementationResponse(
                409,
                "Implementation already exists"
        );
    }
}
