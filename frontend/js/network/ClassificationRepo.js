class ClassificationRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.getHierarchyUrl = this.apiGatewayUrl + '/' + "Classification/Hierarchy";
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
}