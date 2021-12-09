function handleClassificationDelete(ele, classificationName){
    console.log("handling classification delete for classification " + classificationName);

    let onSuccessCallback = function(xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("removed classification with name: " + xhr["classificationName"]);
        updateHierarchy();
    }

    let onFailCallback = function(xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("failed to remove classification.");
    }

    classificationRepo.removeClassification(classificationName, onSuccessCallback, onFailCallback);

}
