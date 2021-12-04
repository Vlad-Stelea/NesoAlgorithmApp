class ImplementationRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.removeImplementationUrl_initial = this.apiGatewayUrl + "/Implementation/Remove/";
        this.createImplementationUrl = this.apiGatewayUrl + "/Implementation";
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

    createImplementation(implName, algoName, encodedCode, language, onSuccess, onFail) {
        let body = {
            implName: implName,
            algoName: algoName,
            code: encodedCode,
            language: language
        }

        let payload = JSON.stringify(body);
        console.log("Create Implementation Json: " + payload);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", this.createImplementationUrl, true);
        xhr.send(payload);

        xhr.onloadend = function() {
            console.log("Create Implementation Response: " + xhr.responseText);
            console.log("StatusCode: " + xhr.response["statusCode"])
            if(xhr.readyState === XMLHttpRequest.DONE) {
                let parsedPayload = JSON.parse(xhr.response);
                if(parsedPayload.statusCode === 200) {
                    onSuccess(parsedPayload);
                } else {
                    console.log("XHR: " + xhr.responseText);
                    onFail(parsedPayload, parsedPayload.statusCode);
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

    createImplementation(implName, algoName, encodedCode, language, onSuccess, onFail) {
        let response = {
            statusCode: 200,
            implName: implName,
            algoName: algoName,
            codeUrl: "Fake Url",
            language: language
        }

        onSuccess(
            response
        )
    }
}
