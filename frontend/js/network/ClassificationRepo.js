class ClassificationRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.getHierachyUrl = this.apiGatewayUrl + '/' + "Classification/Hierachy";
    }

    getClassificationHeiracy(onSuccess, onFail) {
        console.log("Getting classification")
        $.get(
            this.getHierachyUrl,
            onSuccess
        ).fail(
            onFail
        )
    }
}

class MockClassificationRepo {
    constructor() {
        console.log("Creating Mock Classification repo")
    }

    getClassificationHeiracy (onSuccess, onFail) {
        console.log("Mock Class repo getting heirachy")
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
            "200",
            new MockXHR()
        );
    }
}