class BenchmarkRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.addBenchmarkUrl = this.apiGatewayUrl + "/Benchmark/"
        this.removeBenchmarkUrl_initial = this.apiGatewayUrl + "/Benchmark/Remove/"
    }

    addBenchmark(benchmarkName, algoName, machineConfigName, implName, probInstanceUUID, dateRun, timeToRun, onSuccess, onFail) {
        console.log("attempting to add benchmark " + benchmarkName);

        let body = {
            "benchmarkName" : benchmarkName,
            "algoName" : algoName,
            "machineConfigName" : machineConfigName,
            "implName" : implName,
            "probInstanceUUID" : probInstanceUUID,
            "dateRun" : dateRun,
            "timeToRun" : timeToRun
        }

        let stringedBody = JSON.stringify(body);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", this.addBenchmarkUrl, true);

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

    removeBenchmark(benchmarkID, onSuccess, onFail) {
        let xhr = new XMLHttpRequest();

        xhr.open("POST", this.removeBenchmarkUrl_initial + benchmarkID, true);
        console.log("sending POST request to delete benchmark");
        xhr.send();

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
}

class MockBenchmarkRepo {
    constructor() {
        console.log("constructing mock benchmark repo");
    }

    addBenchmark(benchmarkName, algoName, machineConfigName, implName, probInstanceUUID, dateRun, timeToRun, onSuccess, onFail) {
        console.log("mocking add benchmark");

        let response = {
            "benchName" : benchmarkName,
            "algoName" : algoName,
            "machineConfigName" : machineConfigName,
            "implName" : implName,
            "probInstanceUUID" : probInstanceUUID,
            "dateRun" : dateRun,
            "timeToRun" : timeToRun,
            "httpCode" : 200
        };

        onSuccess(response);
    }

    removeBenchmark(benchmarkID, onSuccess, onFail) {
        console.log("mocking remove benchmark");

        let response = {
            "benchmarkID" : benchmarkID,
            "httpCode" : 200
        };

        onSuccess(response);
    }

}
