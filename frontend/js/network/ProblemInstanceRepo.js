class ProblemInstanceRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.createProblemInstanceUrl = this.apiGatewayUrl + "/ProblemInstance";
        this.removeProblemInstanceUrl_initial = this.apiGatewayUrl + "/ProblemInstance/Remove/";
    }

    addProblemInstance(probInstanceName, datasetPayload, algoName, onSuccess, onFail) {
        console.log("attempting to add problem instance with name: " + probInstanceName);

        // note: UUIDs will be set up in Java, so no need to create/add one to our request
        let body = {
            "probInstanceName" : probInstanceName,
            "datasetPayload" : datasetPayload,
            "algoName" : algoName
        }

        let stringedBody = JSON.stringify(body);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", this.createProblemInstanceUrl, true);

        xhr.send(stringedBody);

        xhr.onloadend = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                let xhrJSON = JSON.parse(xhr.response);
                if(xhrJSON["httpCode"] === 200) {
                    onSuccess(xhrJSON);
                } else {
                    onFail(xhrJSON);
                }
            }
        }

    }

    removeProblemInstance(probInstanceUUID, onSuccess, onFail) {
        let xhr = new XMLHttpRequest();
        xhr.open("POST", this.removeProblemInstanceUrl_initial + probInstanceUUID, true);

        xhr.send();

        xhr.onloadend = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                let xhrJSON = JSON.parse(xhr.response);
                if (xhrJSON["httpCode"] === 200) {
                    onSuccess(xhrJSON);
                } else {
                    onFail(xhrJSON);
                }
            }
        }
    }

}

class MockProblemInstanceRepo {
    constructor() {
        console.log("constructing mock problem instance repo");
    }

    addProblemInstance(probInstanceName, datasetPayload, algoName, onSuccess, onFail) {
        console.log("mocking add problem instance");

        let response = {
            "instanceName" : probInstanceName,
            "datasetPayload" : datasetPayload,
            "algoName" : algoName
        };

        onSuccess(response);
    }

    removeProblemInstance(probInstanceUUID, onSuccess, onFail) {
        console.log("mocking remove problem instance");

        let response = {
            "probInstanceUUID" : probInstanceUUID
        };

        onSuccess(response);
    }
}