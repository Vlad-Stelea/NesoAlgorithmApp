function handleAddProblemInstancePrep() {
    console.log("prepping to add problem instance");
    let addProbInstanceForm = document.getElementById("AddProblemInstanceForm");

    let fileInputName = "datasetBase64Encoding";
    let formName = "createProblemInstanceForm";
    let buttonID = "fileUploadButton";
    // create our form
    addProbInstanceForm.innerHTML = "<form id=" + formName + " method='post'>" +
                                    "<br/><label for='problemInstanceName'>Problem instance name: </label>" +
                                    "<input type='text' id='problemInstanceName' name='problemInstanceName'/><br/>" +
                                    "<label for='datasetUpload'>Dataset: </label>" +
                                    "<input type='file' id='datasetUpload' name='datasetUpload'/>" +
                                    "<input name=" + fileInputName + " value='' hidden='true'/>" +
                                    "<br/><input id=" + buttonID + " type='button' value='Submit' disabled onclick='handleAddProblemInstanceSubmit(this)'/>" +
                                    "</form><br/>";

    document.getElementById("datasetUpload").addEventListener('change', handleFileSelect, false);
    document.getElementById("datasetUpload").fileInputName = fileInputName;
    document.getElementById("datasetUpload").formName = formName;
    document.getElementById("datasetUpload").maxFileSize = 10000000;
    document.getElementById("datasetUpload").buttonID = buttonID;
}



function handleAddProblemInstanceSubmit(ele) {
    console.log("submitting problem instance add");

    // gather data we'll need to send to in our request
    let algoHeader = document.getElementById("AlgoNameDisplay");
    let algoName = algoHeader.textContent;
    let probInstanceName = ele.parentElement.children[2].value;
    console.log("adding " + probInstanceName + " under " + algoName);
    let datasetPayload = document.getElementById("createProblemInstanceForm").elements["datasetBase64Encoding"].value.split(',')[1];
    // console.log("contents: "  + datasetPayload);

    if(probInstanceName === "") {
        alert("Please enter a non-empty Problem Instance name");
    }
    else {
        let onSuccessCallback = function(xhr) {
            console.log("XHR: " + JSON.stringify(xhr, null, 4));
            console.log("added problem instance: " + xhr["instanceName"]);
            updateAlgorithmPageHierarchy();
        }

        let onFailCallback = function(xhr) {
            console.log("XHR: " + JSON.stringify(xhr, null, 4));
            console.log("failed to add problem instance.");
        }

        problemInstanceRepo.addProblemInstance(probInstanceName, datasetPayload, algoName, onSuccessCallback, onFailCallback);
        document.getElementById(ele.form.id).innerHTML = "";
    }
}
