class BenchmarkRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.removeBenchmarkUrl_initial = this.apiGatewayUrl + "/Benchmark/Remove/"
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

                    let username = vm.user.username;
                    let action = username + " removed Benchmark " + benchmarkID;
                    addActivity(username, action);

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

    removeBenchmark(benchmarkID, onSuccess, onFail) {
        console.log("mocking remove benchmark");

        let response = {
            "benchmarkID" : benchmarkID,
            "httpCode" : 200
        };

        let username = vm.user.username;
        let action = username + " removed Benchmark " + benchmarkID;
        addActivity(username, action);

        onSuccess(response);
    }

}
