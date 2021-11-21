class ImplementationRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.removeImplementationUrl_initial = this.apiGatewayUrl + "/Implementation/Remove/";
    }

    removeImplementation(implName, algoName, onSuccess, onFail) {
        let xhr = new XMLHttpRequest();

        // TODO: implName concatenated with algo name is a hack, fix when we fix our YAML
        xhr.open("POST", this.removeImplementationUrl_initial + implName + "," + algoName, true);
        console.log("sending: " + this.removeImplementationUrl_initial + implName + "," + algoName);
        xhr.send();

        xhr.onloadend = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if(xhr.status === 200) {
                    onSuccess(xhr);
                } else {
                    onFail(xhr);
                }
            }
        }
    }
}

class MockImplementationRepo {
    constructor() {
        console.log("constructing mock implementation repo");
    }

    removeImplementation(implName, algoName, onSuccess, onFail) {
        console.log("mocking remove implementation");

        let response = {
            "implementationID" : implName + "," + algoName
        };

        onSuccess(
            response,
            200,
            new MockXHR()
        );
    }
}