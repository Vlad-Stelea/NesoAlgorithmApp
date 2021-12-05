function handleMachineConfigDelete(ele, machineConfigUUID){
    console.log("handling request to delete machine configuration with UUID: " + machineConfigUUID);

    let onSuccessCallback = function(xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("deleted machine configuration with UUID: " + xhr["machineConfigurationID"]);
        updateAlgorithmPageHierarchy();
    }

    let onFailCallback = function(xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("failed to delete machine configuration.");
    }

    machineConfigRepo.removeMachineConfiguration(machineConfigUUID, onSuccessCallback, onFailCallback);
}
