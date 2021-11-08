package lambda;

import com.amazonaws.services.lambda.runtime.Context;

public class LambdaTest {

    /**
     * Helper method that creates a context that supports logging so you can test lambda functions
     * in JUnit without worrying about the logger anymore.
     *
     * @param apiCall      An arbitrary string to identify which API is being called.
     * @return
     *
     * note: taken from AWSCalculator project
     */
    public Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }


}
