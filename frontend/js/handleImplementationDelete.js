function handleImplementationDelete(ele, implName, algoName){
    console.log("delete implementation " + implName + " from " + algoName);

    let onSuccessCallback = function(xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("deleted implementation: " + xhr["implementationID"]);
        updateAlgorithmPageHierarchy();
    }

    let onFailCallback = function(xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("failed to delete implementation.");
    }

    implementationRepo.removeImplementation(implName, algoName, onSuccessCallback, onFailCallback);
}