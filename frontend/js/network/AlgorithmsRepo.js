class AlgorithmsRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.createAlgorithmUrl = this.apiGatewayUrl + '/' + "Algorithm"
        this.getAlgorithmHierarchyUrlBegin = this.apiGatewayUrl + '/' + "Algorithm/"
    }

    addAlgorithm(algorithmName, className, onSuccess, onFail) {
        let body = {
            "algoName" : algorithmName,
            "className" : className
        }

        let stringedBody = JSON.stringify(body);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", this.createAlgorithmUrl, true);

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

    getAlgorithmHierarchy(algorithmName, onSuccess, onFail){


        let xhr = new XMLHttpRequest();
        xhr.open("GET", this.createAlgorithmUrl + algorithmName, true);

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

class MockAlgorithmRepo {
    constructor() {
        console.log("Creating mock algo repo")
    }

    addAlgorithm(algorithmName, className, onSuccess, onFail) {
        console.log("Mock algo repo add algo")
        let response = {
            "algoName":algorithmName,
            "className":className
        }

        onSuccess(
            response,
            200,
            new MockXHR()
        )
    }
}
