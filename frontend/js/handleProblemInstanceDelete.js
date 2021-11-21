function handleProblemInstanceDelete(ele, probInstanceUUID){
    // console.log("handleProblemInstanceDelete")
    // console.log(ele.parentElement.children[1].textContent )
    console.log("deleting Problem Instance with UUID: " + probInstanceUUID);

    let onSuccessCallback = function(xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("deleted problem instance: " + xhr["probInstanceUUID"]);
        updateAlgorithmPageHierarchy();
    }

    let onFailCallback = function(xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("failed to delete problem instance.");
    }

    problemInstanceRepo.removeProblemInstance(probInstanceUUID, onSuccessCallback, onFailCallback);

}