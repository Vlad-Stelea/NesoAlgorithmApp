class AlgorithmsRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.createAlgorithmUrl = this.apiGatewayUrl + '/' + "Algorithm";
        this.reclassifyAlgorithmUrl = this.apiGatewayUrl + '/' + "Algorithm/Reclassify";
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

    reclassifyAlgorithm(algoName, newClassName, onSuccess, onFail) {
        let body = {
            "algoName": algoName,
            "newClassName": newClassName
        };

        let stringedBody = JSON.stringify(body);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", this.reclassifyAlgorithmUrl, true);

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

    reclassifyAlgorithm(algoName, newClassName, onSuccess, onFail) {
        console.log("Mock algo repo reclassify algo")
        let response = {
            "algoName": algoName,
            "newClassName": newClassName
        }

        onSuccess(
            response,
            200,
            new MockXHR()
        );
    }
}
