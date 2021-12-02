class ProblemInstanceRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.createProblemInstanceUrl = this.apiGatewayUrl + "/ProblemInstance";
        this.removeProblemInstanceUrl_initial = this.apiGatewayUrl + "/ProblemInstance/Remove/";
    }

    addProblemInstance(probInstanceName, datasetPayload, algoName, onSuccess, onFail) {
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
                if(xhr.status === 200) {
                    onSuccess(xhr);
                } else {
                    onFail(xhr);
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
                if(xhr.status === 200) {
                    onSuccess(xhr);
                } else {
                    onFail(xhr);
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