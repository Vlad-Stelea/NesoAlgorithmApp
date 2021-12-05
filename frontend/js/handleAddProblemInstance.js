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
                                    "<br/><input id='fileUploadButton' type='button' value='Submit' onclick='handleProblemInstanceAddFormSubmit(this)'/>" +
                                    "</form><br/>";
}



function handleProblemInstanceAddFormSubmit(ele) {
    console.log("submitting problem instance add");

    let selectedFile = document.getElementById("datasetUpload").files[0];
    // check that the file isn't too large
    if(selectedFile.size < 1000000) {
        let datasetReadCallback = function addProblemInstanceCallback(datasetPayload, formElement) {
            // gather data we'll need to send to in our request
            let algoHeader = document.getElementById("AlgoNameDisplay");
            let algoName = algoHeader.textContent;
            let file = document.getElementById("datasetUpload").files[0];
            let fileExtension = file.name.substr(file.name.lastIndexOf("."))
            let probInstanceName = formElement.parentElement.children[2].value;
            console.log("adding " + probInstanceName + " under " + algoName);
            // console.log("contents: "  + datasetPayload);

            if (probInstanceName === "") {
                alert("Please enter a non-empty Problem Instance name");
            } else {
                let onSuccessCallback = function (xhr) {
                    console.log("XHR: " + JSON.stringify(xhr, null, 4));
                    console.log("added problem instance: " + xhr["instanceName"]);
                    updateAlgorithmPageHierarchy();
                }

                let onFailCallback = function (xhr) {
                    console.log("XHR: " + JSON.stringify(xhr, null, 4));
                    console.log("failed to add problem instance.");
                }

                problemInstanceRepo.addProblemInstance(probInstanceName, datasetPayload, algoName, fileExtension, onSuccessCallback, onFailCallback);
                document.getElementById(formElement.form.id).innerHTML = "";
            }
        }
        getFileBase64Encoding(selectedFile, datasetReadCallback, ele);
    }
    else {
        alert("File size too large! (" + selectedFile.size + " bytes, ~10MB max)");
    }
}


