class ProblemInstanceRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.createProblemInstanceUrl = this.apiGatewayUrl + "/ProblemInstance";
        this.removeProblemInstanceUrl_initial = this.apiGatewayUrl + "/ProblemInstance/Remove/";
    }

    addProblemInstance(probInstanceName, datasetPayload, algoName, fileExtension, onSuccess, onFail) {
        console.log("attempting to add problem instance with name: " + probInstanceName);

        // note: UUIDs will be set up in Java, so no need to create/add one to our request
        let body = {
            "probInstanceName" : probInstanceName,
            "datasetPayload" : datasetPayload,
            "fileExtension" : fileExtension,
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

                    let username = vm.user.username;
                    let action = username + " added Problem Instance " + probInstanceName;
                    addActivity(username, action);

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
                    let username = vm.user.username;
                    let action = username + " removed Problem Instance " + probInstanceUUID;
                    addActivity(username, action);

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

    addProblemInstance(probInstanceName, datasetPayload, algoName, fileExtension, onSuccess, onFail) {
        console.log("mocking add problem instance");

        let response = {
            "instanceName" : probInstanceName,
            "datasetPayload" : datasetPayload,
            "fileExtension": fileExtension,
            "algoName" : algoName
        };

        let username = vm.user.username;
        let action = username + " added Problem Instance " + probInstanceName;
        addActivity(username, action);

        onSuccess(response);
    }

    removeProblemInstance(probInstanceUUID, onSuccess, onFail) {
        console.log("mocking remove problem instance");

        let response = {
            "probInstanceUUID" : probInstanceUUID
        };

        let username = vm.user.username;
        let action = username + " removed Problem Instance " + probInstanceUUID;
        addActivity(username, action);

        onSuccess(response);
    }
}