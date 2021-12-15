class ClassificationRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.getHierarchyUrl = this.apiGatewayUrl + '/' + "Classification/Hierarchy";
        this.createClassification_url = this.apiGatewayUrl + "/Classification";
        this.mergeClassification_url = this.apiGatewayUrl +"/Classification/Merge"
        this.removeClassificationUrl_initial = this.apiGatewayUrl + "/Classification/Remove/";
    }

    getClassificationHierarchy(onSuccess, onFail) {
        console.log("Getting classification")
        let xhr = new XMLHttpRequest();
        xhr.responseType = "json"
        xhr.open("GET", this.getHierarchyUrl, true);
        xhr.send();
        xhr.onloadend = function () {
            if(xhr.readyState === XMLHttpRequest.DONE) {
                if(xhr.status === 200) {
                    let response = xhr.response;
                    onSuccess(response);
                } else {
                    onFail(xhr.response, xhr.status);
                }
            }
        }
    }

    addClassification(className, parentClassName, onSuccess, onFail){
        console.log("adding classification");
        let cData = {};
        cData["className"] = className;

        cData["parentClassName"] = parentClassName;


        if(cData["className"] === "") {
            alert("Please enter a non-empty Classification name");
        }else {
            let js = JSON.stringify(cData);
            console.log("Create Classification JSON: " + js);

            let xhr = new XMLHttpRequest();
            xhr.responseType = "json";
            xhr.open("POST", this.createClassification_url, true);
            xhr.send(js);

            xhr.onloadend = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    console.log(xhr.response)
                    let xhrJSON = xhr.response;
                    // let actualResponse = JSON.parse(xhrJSON);
                    if (xhrJSON["httpCode"] === 200) {
                        let response = xhrJSON;
                        onSuccess(response);
                    } else {
                        onFail(xhrJSON);
                    }
                }
            }
        }
    }
    mergeClassification(class1,class2,newName,onSuccess, onFail){
        let iData = {};
        iData["class1"] = class1;
        iData["class2"] = class2;
        iData["newName"] = newName;
        if(class2 === "" || newName ==="") {
            alert("Please enter a sibling of the class you are trying to merge and an original new name")
        }else {
            let js = JSON.stringify(iData);
            console.log("Create merge Classification JSON: " + js);

            let xhr = new XMLHttpRequest();
            xhr.responseType = "json";
            xhr.open("POST", this.mergeClassification_url, true);
            xhr.send(js);

            xhr.onloadend = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    console.log(xhr.response)
                    let xhrJSON = xhr.response;

                    if (xhrJSON["httpCode"] === 200) {
                        let response = xhrJSON;
                        onSuccess(response);
                    } else {
                        onFail(xhrJSON);
                    }
                }
            }
        }
    }

    removeClassification(classificationName, onSuccess, onFail) {
        console.log("attempting to remove classification " + classificationName);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", this.removeClassificationUrl_initial + classificationName, true);
        xhr.send();

        xhr.onloadend = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                console.log(xhr);
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

class MockClassificationRepo {
    constructor() {
        console.log("Creating Mock Classification repo")
    }

    getClassificationHierarchy (onSuccess, onFail) {
        console.log("Mock Class repo getting hierachy")
        let response = {
            "topClassifications":[
                {
                    "className":"newTopDog",
                    "parentClassification":{
                        "algorithms":[
                        ],
                        "subclassifications":[
                        ],
                        "topLevel":true
                    },
                    "algorithms":[
                        {
                            "algoName":"Euclid",
                            "implementations":[
                                {
                                    "implName":"gcd",
                                    "codeURL":"public class Euclid { public static int gcd(int p, int q) { if (q == 0) return p; else return gcd(q, p % q); } }",
                                    "language":"Java",
                                    "benchmark":[
                                    ]
                                }
                            ]
                        },
                        {
                            "algoName":"fake algo",
                            "implementations":[
                            ]
                        }
                    ],
                    "subclassifications":[
                    ],
                    "topLevel":false
                },
                {
                    "className":"secondOne",
                    "parentClassification":{
                        "algorithms":[
                        ],
                        "subclassifications":[
                        ],
                        "topLevel":true
                    },
                    "algorithms":[
                        {
                            "algoName":"test algo",
                            "implementations":[
                            ]
                        }
                    ],
                    "subclassifications":[
                    ],
                    "topLevel":false
                }
            ],
            "statusCode":200,
            "error":""
        };

        onSuccess (
            response,
            200
        );
    }

    addClassification(className, parentClassName, onSuccess, onFail){
        console.log("Mock Class repo adding hierachy")
        let response = {
            "response":"worked",
            "statusCode":200,
            "error":""
        };

        let username = vm.user.username;
        let action = ""
        if(parentClassName != null) {
            action = username + " added Classification " + className + " to " + parentClassName;
        }else{
            action = username + " added top Classification " + className;
        }
        addActivity(username, action);

        onSuccess (response);
    }
    mergeClassification(class1,class2,newName,onSuccess, onFail){
        console.log("Mock Class repo mergingClassification")
        let response = {
            "response":"worked",
            "statusCode":200,
            "error":""
        };

        onSuccess (
            response,
            200
        );
    }

    removeClassification(classificationName, onSuccess, onFail) {
        console.log("mocking remove classification");
        let response = {
            "classificationName" : classificationName,
            "httpCode" : 200,
            "error" : ""
        };

        onSuccess(response);
    }
}
