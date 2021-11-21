class ProblemInstanceRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.createProblemInstanceUrl = this.apiGatewayUrl + "/ProblemInstance";
        this.removeProblemInstanceUrl_initial = this.apiGatewayUrl + "/ProblemInstance/Remove/";
    }

    addProblemInstance(probInstanceName, datasetByteStream, algoName, onSuccess, onFail) {

        // TODO update datasetByteStream when we figure out S3 stuff
        // note: we can set the UUID to a placeholder value since it'll be set up in Java anyways
        let body = {
            "probInstanceUUID" : "placeholder",
            "probInstanceName" : probInstanceName,
            "datasetByteStream" : datasetByteStream,
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

    addProblemInstance(probInstanceName, datasetByteStream, algoName, onSuccess, onFail) {
        console.log("mocking add problem instance");

        let response = {
            "probInstanceUUID" : "placeholder",
            "probInstanceName" : probInstanceName,
            "datasetByteStream" : datasetByteStream,
            "algoName" : algoName
        };

        onSuccess(
            response,
            200,
            new MockXHR()
        );
    }

    removeProblemInstance(probInstanceUUID, onSuccess, onFail) {
        console.log("mocking remove problem instance");

        let response = {
            "probInstanceUUID" : probInstanceUUID
        };

        onSuccess(
            response,
            200,
            new MockXHR()
        );
    }
}