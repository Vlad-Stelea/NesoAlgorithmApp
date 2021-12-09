class MachineConfigurationRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.createMachineConfigurationUrl = this.apiGatewayUrl + "/MachineConfiguration";
        this.removeMachineConfigurationUrl_initial = this.apiGatewayUrl + "/MachineConfiguration/Remove/";
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

                    let username = vm.user.username;
                    let action = ""
                    if(username == null){
                        action = "Unregistered user added MachineConfig " + machineConfigName;
                    }else {
                        action = username + " added MachineConfig " + machineConfigName;
                    }
                    addActivity(username, action);

                    onSuccess(xhrJSON);
                } else {
                    onFail(xhrJSON);
                }
            }
        }
    }

    removeMachineConfiguration(machineConfigurationID, onSuccess, onFail) {
        console.log("sending request to delete machine config with ID: " + machineConfigurationID)
        let xhr = new XMLHttpRequest();
        xhr.open("POST", this.removeMachineConfigurationUrl_initial + machineConfigurationID, true);
        xhr.send();

        xhr.onloadend = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                console.log(xhr.response);
                let xhrJSON = JSON.parse(xhr.response);
                if(xhrJSON["httpCode"] === 200) {

                    let username = vm.user.username;
                    let action = username + " removed MachineConfig " + machineConfigurationID;
                    addActivity(username, action);

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

        let username = vm.user.username;
        let action = ""
        if(username == null){
            action = "Unregistered user added MachineConfig " + machineConfigName;
        }else {
            action = username + " added MachineConfig " + machineConfigName;
        }
        addActivity(username, action);

        onSuccess(response);
    }

    removeMachineConfiguration(machineConfigurationID, onSuccess, onFail) {
        console.log("mocking remove machine config");

        let response = {
            "machineConfigurationID" : "fake-news-ID",
            "httpCode": 200
        };

        let username = vm.user.username;
        let action = username + " removed MachineConfig " + machineConfigurationID;
        addActivity(username, action);

        onSuccess(response);
    }
}
