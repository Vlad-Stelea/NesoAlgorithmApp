package CreateAlgorithm;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class CreateAlgorithmEvent<Req, Res> {

    String className;
    String parentClassName;

    Req request;
    Res response;

    public CreateAlgorithmEvent() {

    }

    public CreateAlgorithmEvent(Req request, Res response) {
        this.request = request;
        this.response = response;
    }

    public Res getResponse() {
        return response;
    }

    public Req getRequest() {
        return request;
    }

    public void log(LambdaLogger logger) {
        logger.log("running int event");
    }
}
