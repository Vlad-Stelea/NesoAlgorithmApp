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

        console.log("getting algo hierarchy")
        let xhr = new XMLHttpRequest();
        xhr.responseType = "json"
        xhr.open("GET", this.getAlgorithmHierarchyUrlBegin + algorithmName, true);

        xhr.send();

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

    getAlgorithmHierarchy(algorithmName, onSuccess, onFail){

        console.log("mock getting algo hierarchy")

       let response = {
            "algorithmPage":{
                "algorithm":{
                    "algoName":"efsTest",
                    "parentClassificationName":"test",
                    "implementations":[
                        {
                            "implName":"efsTest1",
                            "codeURL":"dfsdf",
                            "language":"sdfds",
                            "algorithmName":"efsTest",
                            "benchmark":[]
                        },
                        {"implName":"efsTest2",
                            "codeURL":"kjhgf",
                            "language":"dfbdfb",
                            "algorithmName":"efsTest",
                            "benchmark":[]
                        }
                        ],
                    "problemInstances":[
                        {
                            "probInstanceUUID":"123",
                            "probInstanceName":"pi1",
                            "datasetURL":"3",
                            "algoName":"efsTest"
                        },
                        {
                            "probInstanceUUID":"234",
                            "probInstanceName":"pi2",
                            "datasetURL":"3",
                            "algoName":"efsTest"
                        }
                        ]
                },
                "machineConfigurations":[
                    {
                        "machineConfigName":"m1",
                        "machineConfigUUID":"1111111",
                        "chip":"rer",
                        "threads":5,
                        "l1Cache":12,
                        "l2Cache":12
                    },
                    {
                        "machineConfigName":"m2",
                        "machineConfigUUID":"222222",
                        "chip":"sdf",
                        "threads":7,
                        "l1Cache":23,
                        "l2Cache":23
                    }
                    ]
            }
        }

        onSuccess(
            response,
            200,
            new MockXHR()
        )
    }

}
