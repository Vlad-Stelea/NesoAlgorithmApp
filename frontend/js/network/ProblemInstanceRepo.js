function createUUID() {
    // snagged from stack overflow: https://stackoverflow.com/questions/105034/how-to-create-a-guid-uuid
    return ([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, c =>
        (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
    );
}

class ProblemInstanceRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.createProblemInstanceUrl = this.apiGatewayUrl + "/ProblemInstance";
        this.removeProblemInstanceUrl_initial = this.apiGatewayUrl + "/ProblemInstance/";
    }

    addProblemInstance(probInstanceName, datasetByteStream, algoName, onSuccess, onFail) {

        // TODO update datasetByteStream when we figure out S3 stuff
        let body = {
            "probInstanceUUID" : createUUID(),
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
                    let response = xhr.response
                    onSuccess(response);
                } else {
                    onFail(xhr.response, xhr.status)
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
            "probInstanceUUID" : createUUID(),
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
}