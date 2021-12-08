class AlgorithmsRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.createAlgorithmUrl = this.apiGatewayUrl + '/' + "Algorithm";
        this.reclassifyAlgorithmUrl = this.apiGatewayUrl + '/' + "Algorithm/Reclassify";
        this.getAlgorithmHierarchyUrlBegin = this.apiGatewayUrl + '/' + "Algorithm/"
        this.deleteAlgorithmBegin = this.apiGatewayUrl + '/Algorithm/Remove/'
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
                      let response = xhr.response;
                      onSuccess(response);
                  } else {
                      onFail(xhr.response, xhr.status);
                  }
              }
          }
    }

    getAlgorithmHierarchy(algorithmName, onSuccess, onFail){
        let xhr = new XMLHttpRequest();
        xhr.responseType = "json"
        xhr.open("GET", this.getAlgorithmHierarchyUrlBegin + algorithmName, true);

        xhr.send();

        xhr.onloadend = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if(xhr.status === 200) {
                    let response = xhr.response;
                    onSuccess(response);
                } else {
                    onFail(xhr.response, xhr.status);
                }
            }
        }
    }

    deleteAlgorithm(algorithmName, onSuccess, onFail){
        let xhr = new XMLHttpRequest();
        xhr.responseType = "json"

        let body = {
            "algoName" : algorithmName
        }

        let stringedBody = JSON.stringify(body);
        xhr.open("POST", this.deleteAlgorithmBegin + algorithmName, true);

        xhr.send(stringedBody);

        xhr.onloadend = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if(xhr.status === 200) {
                    let response = xhr.response;
                    onSuccess(response);
                } else {
                    onFail(xhr.response, xhr.status);
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
        );
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

    getAlgorithmHierarchy(algorithmName, onSuccess, onFail){
        console.log("mock getting algo hierarchy")

       let response = {
           "algorithmPage": {
               "algorithm": {
                   "algoName": "add algo",
                   "parentClassificationName": "newTopDog",
                   "implementations": [
                       {
                           "implName": "fake impl",
                           "codeURL": "fdasfdf",
                           "language": "java",
                           "algorithmName": "add algo",
                           "benchmark": [
                               {
                                   "benchID": "111",
                                   "benchName": "BM1",
                                   "algoName": "add algo",
                                   "machineConfigName": "33333",
                                   "implName": "fake impl",
                                   "problemInstanceName": "555",
                                   "dateRun": "2000-10-10",
                                   "timeToRun": 6
                               },
                               {
                                   "benchID": "222",
                                   "benchName": "BM2",
                                   "algoName": "add algo",
                                   "machineConfigName": "222222",
                                   "implName": "fake impl",
                                   "problemInstanceName": "555",
                                   "dateRun": "2000-10-10",
                                   "timeToRun": 6
                               },
                               {
                                   "benchID": "333",
                                   "benchName": "BM 3",
                                   "algoName": "add algo",
                                   "machineConfigName": "222222",
                                   "implName": "fake impl",
                                   "problemInstanceName": "666",
                                   "dateRun": "2000-10-10",
                                   "timeToRun": 7
                               }
                           ]
                       },
                       {
                           "implName": "other impl",
                           "codeURL": "google.com",
                           "language": "c",
                           "algorithmName": "add algo",
                           "benchmark": [
                               {
                                   "benchID": "444",
                                   "benchName": "BM 4",
                                   "algoName": "add algo",
                                   "machineConfigName": "33333",
                                   "implName": "other impl",
                                   "problemInstanceName": "666",
                                   "dateRun": "2000-10-10",
                                   "timeToRun": 8
                               }
                           ]
                       }
                   ],
                   "problemInstances": [
                       {
                           "probInstanceUUID": "555",
                           "probInstanceName": "pi1",
                           "datasetURL": "2",
                           "algoName": "add algo"
                       },
                       {
                           "probInstanceUUID": "666",
                           "probInstanceName": "pi 2",
                           "datasetURL": "4",
                           "algoName": "add algo"
                       }
                   ]
               },
               "machineConfigurations": [
                   {
                       "machineConfigName": "m1",
                       "machineConfigUUID": "1111111",
                       "chip": "rer",
                       "threads": 5,
                       "l1Cache": 12,
                       "l2Cache": 12
                   },
                   {
                       "machineConfigName": "m2",
                       "machineConfigUUID": "222222",
                       "chip": "sdf",
                       "threads": 7,
                       "l1Cache": 23,
                       "l2Cache": 23
                   },
                   {
                       "machineConfigName": "m3",
                       "machineConfigUUID": "33333",
                       "chip": "sd",
                       "threads": 11,
                       "l1Cache": 34,
                       "l2Cache": 45
                   }
               ]
           }
       }

        onSuccess(
            response,
            200,
            new MockXHR()
        );
    }

    deleteAlgorithm(algorithmName, onSuccess, onFail){
        let response = {
            "algoName" : algorithmName,
            "httpCode" : 200
        };

        onSuccess(response);

    }
}
