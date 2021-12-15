class ImplementationRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.removeImplementationUrl = this.apiGatewayUrl + "/Implementation/Remove/";
        this.createImplementationUrl = this.apiGatewayUrl + "/Implementation";
    }

    removeImplementation(implName, algoName, onSuccess, onFail) {
        let xhr = new XMLHttpRequest();

        let jsonBody = {
            "implName" : implName,
            "algoName" : algoName
        };

        let payload = JSON.stringify(jsonBody);
        xhr.open("POST", this.removeImplementationUrl, true);
        xhr.send(payload);

        xhr.onloadend = function() {
            if(xhr.readyState === XMLHttpRequest.DONE) {
                let parsedPayload = JSON.parse(xhr.response);
                if(parsedPayload.httpCode === 200) {
                    let username = vm.user.username;
                    let action = username + " removed Implementation " + implName + " from " + algoName;
                    addActivity(username, action);
                    onSuccess(parsedPayload);
                } else {
                    console.log("XHR: " + xhr.responseText);
                    onFail(parsedPayload);
                }
            }
        }
    }

    createImplementation(implName, algoName, encodedCode, fileExtension, language, onSuccess, onFail) {
        let body = {
            implName: implName,
            algoName: algoName,
            code: encodedCode,
            fileExtension: fileExtension,
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

                    let username = vm.user.username;
                    let action = username + " added Implementation " + implName + " to " + algoName;
                    addActivity(username, action);

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
            "implName" : implName,
            "algoName" : algoName
        };


        let username = vm.user.username;
        let action = username + " removed Implementation " + implName + " from " + algoName;
        addActivity(username, action);

        onSuccess(
            response,
            200,
            new MockXHR()
        );
    }

    createImplementation(implName, algoName, encodedCode, fileExtension, language, onSuccess, onFail) {
        let response = {
            statusCode: 200,
            implName: implName,
            algoName: algoName,
            codeUrl: "Fake Url",
            language: language
        }

        let username = vm.user.username;
        let action = username + " added Implementation " + implName + " to " + algoName;
        addActivity(username, action);

        onSuccess(response)
    }
}
