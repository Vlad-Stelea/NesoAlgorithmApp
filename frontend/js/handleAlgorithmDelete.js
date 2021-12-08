function handleAlgorithmDelete(ele){
    let algoName = ele.parentElement.children[1].textContent;
    console.log("handleAlgorithmDelete: " + algoName)

    let onSuccessCallback = function(xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("deleted algorithm with name: " + xhr["algoName"]);
        updateHierarchy();
    }

    let onFailCallback = function(xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("failed to delete algorithm.");
    }

    algorithmRepo.deleteAlgorithm(algoName, onSuccessCallback, onFailCallback);
}