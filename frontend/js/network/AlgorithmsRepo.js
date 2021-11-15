class AlgorithmsRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.createAlgorithmUrl = this.apiGatewayUrl + '/' + "Algorithm"
    }

    addAlgorithm(algorithmName, className, onSuccess, onFail) {
        let body = {
            "algoName" : algorithmName,
            "className" : className
        }

        $.post(
            this.createAlgorithmUrl,
            body,
            onSuccess,
            "json"
        ).fail(
            onFail
        )
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
