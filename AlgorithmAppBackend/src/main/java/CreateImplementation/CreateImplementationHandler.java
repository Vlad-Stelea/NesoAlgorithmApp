package CreateImplementation;


import db.ImplementationDAO;


public class CreateImplementationHandler{

    ImplementationDAO dao;

    public CreateImplementationHandler(ImplementationDAO dao) {
        this.dao = dao;
    }


    public CreateImplementationResponse handle(CreateImplementationRequest request)  {
        try {
            // Successful case of creating an implementation
            if(dao.createImplementation(request.getImplName(),request.getCode(), request.getLanguage() ,request.getAlgoName())) {
                return new CreateImplementationResponse(
                        200,
                        request.getImplName(),
                        request.getAlgoName(),
                        request.getCode(),
                        request.getLanguage()
                );
            } else {
                // Case where an implementation already exists in the db
                return new CreateImplementationResponse(
                        409,
                        "Implementation already exists"
                );
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

}
