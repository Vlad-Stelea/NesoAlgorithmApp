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
