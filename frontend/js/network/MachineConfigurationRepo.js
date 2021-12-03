class MachineConfigurationRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.createMachineConfigurationUrl = this.apiGatewayUrl + "/MachineConfiguration";
    }

    addMachineConfiguration(machineConfigName, l1Cache, l2Cache, chip, threads, onSuccess, onFail) {
        console.log("attempting to add machine configuration with name: " + machineConfigName);

        let body = {
            "machineConfigName" : machineConfigName,
            "l1Cache" : l1Cache,
            "l2Cache" : l2Cache,
            "chip" : chip,
            "threads" : threads
        };

        let stringedBody = JSON.stringify(body);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", this.createMachineConfigurationUrl, true);

        xhr.send(stringedBody);

        xhr.onloadend = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
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

class MockMachineConfigurationRepo {
    constructor() {
        console.log("constructing mock machine config repo");
    }

    addMachineConfiguration(machineConfigName, l1Cache, l2Cache, chip, threads, onSuccess, onFail) {
        console.log("mocking machine config add");

        let response = {
            "machineConfigUUID" : "fake-news-ID",
            "machineConfigName" : machineConfigName,
            "l1Cache" : l1Cache,
            "l2Cache" : l2Cache,
            "chip" : chip,
            "threads" : threads,
            "httpCode": 200
        };

        onSuccess(response);
    }
}
