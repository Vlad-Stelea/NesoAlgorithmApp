package CreateClassification;

public class CreateClassificationEvent<Req, Res> {

    String className;
    String parentClassName;

    Req request;
    Res response;

    public CreateClassificationEvent() {

    }

    public CreateClassificationEvent(Req request, Res response) {
        this.request = request;
        this.response = response;
    }

    public Res getResponse() {
        return response;
    }

    public Req getRequest() {
        return request;
    }
}
