package CreateImplementation;


import db.ImplementationDAO;


public class CreateImplementationHandler{

    ImplementationDAO dao;

    public CreateImplementationHandler(ImplementationDAO dao) {
        this.dao = dao;
    }


    public CreateImplementationResponse handle(CreateImplementationRequest request)  {


        CreateImplementationResponse response;

        try {
            if(dao.createImplementation(request.getImplName(),request.getCodeUrl(), request.getLanguage() ,request.getAlgoName())) {
                response = new CreateImplementationResponse(request.getImplName() + "," + request.getCodeUrl() + "," + request.getLanguage() + "," + request.getAlgoName(), 200);
            } else {
                response = new CreateImplementationResponse(request.getImplName(), 409, "Implementation already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new CreateImplementationResponse("Unable to create Implementation: " + request.getImplName() + " with parent " + request.getAlgoName() + "\n(" + e.getMessage() + ")", 400);
        }

        return  response;
    }

}