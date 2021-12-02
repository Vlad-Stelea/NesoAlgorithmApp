function handleAddProblemInstancePrep() {
    console.log("prepping to add problem instance");
    let addProbInstanceForm = document.getElementById("AddProblemInstanceForm");

    // create our form
    addProbInstanceForm.innerHTML = "<form id='createProblemInstanceForm' method='post'>" +
                                    "<br/><label for='problemInstanceName'>Problem instance name: </label>" +
                                    "<input type='text' id='problemInstanceName' name='problemInstanceName'/><br/>" +
                                    "<label for='datasetUpload'>Dataset: </label>" +
                                    "<input type='file' id='datasetUpload' name='datasetUpload'/>" +
                                    "<input name='datasetBase64Encoding' value='' hidden='true'/>" +
                                    "<br/><input id='fileUploadButton' type='button' value='Submit' disabled onclick='handleAddProblemInstanceSubmit(this)'/>" +
                                    "</form><br/>";
    document.getElementById("datasetUpload").addEventListener('change', handleDatasetFileSelect, false);
}

function getBase64OfDataset(file) {
    console.log("in getBase64OfDataset");

    let fileReader = new FileReader();
    fileReader.readAsDataURL(file);

    fileReader.onload = function() {
        document.getElementById("createProblemInstanceForm").elements["datasetBase64Encoding"].value = fileReader.result;
        document.getElementById("createProblemInstanceForm").elements["fileUploadButton"].disabled = false;
    }
}

function handleDatasetFileSelect(evt) {
    console.log("in handleDatasetFileSelect");

    let files = evt.target.files;
    // accept files up to ~10MB
    if(files[0].size > 10000000) {
        document.getElementById("createProblemInstanceForm").elements["datasetBase64Encoding"].value = "";
        alert("File size too large! (" + files[0].size + " bytes, 10MB max)");
    }
    else {
        getBase64OfDataset(files[0]);
    }
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
    }
}
