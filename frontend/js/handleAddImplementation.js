function handleAddImpPrep(){
    let addImplForm = document.getElementById("addImplementationForm");
    addImplForm.style.visibility = "visible"
}

function createImplementation() {
    let implName = document.getElementById("implNameInput").value;
    let language = document.getElementById("languageInput").value;
    let selectedFile = document.getElementById("uploadCodeButton").files[0];
    let algoName = vm.selectedAlgo;

    if(implName && language && selectedFile){
        console.log("submitting implementation");
        // Get the base 64 encoding
        getFileBase64EncodingPromise(selectedFile)
            .then((base64String) => {
                let onSuccessCallback = function (response) {
                    processCreateImplementationResponse(response);
                    // Hide the add Implementation dialog
                    document.getElementById("addImplementationForm").style.visibility = "hidden";
                }

                let onFailCallback = function(response, code) {
                    if(code === 400) alert("Unknown Error uploading file. Please try again later.");
                    if(code === 409) alert("Error: implementation already exists. Please create a new implementation.");
                    else alert("Super unkown error: " + code);
                }

                let file = document.getElementById("uploadCodeButton").files[0];
                let fileExtension = file.name.substr(file.name.lastIndexOf("."))
                
                implementationRepo.createImplementation(implName, algoName, base64String, fileExtension, language, onSuccessCallback, onFailCallback)

            })
            .catch(() => alert("Error loading file please try again"))


    } else if(!implName) {
        alert("Please include an implementation name");
    } else if(!language) {
        alert("Please include the language this implementation uses");
    } else if(!selectedFile) {
        alert("Please upload a file");
    }
}

function processCreateImplementationResponse(result) {
    console.log("create implementation response: " + result);

    updateAlgorithmPageHierarchy();
}