function handleAddImpPrep(){
    let addTopClassForm = document.getElementById("addImplementationForm");
    addTopClassForm.style.visibility = "visible"
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

                implementationRepo.createImplementation(implName, algoName, base64String, language, onSuccessCallback, onFailCallback)

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


function handleAddTopLevelSubmit(ele){
    console.log("addTopSubmit")
    console.log(ele.parentElement.children[2].value)
    console.log(ele.parentElement.children[4].value)
    console.log(ele.parentElement.children[6].value)
    let addTopClassForm = document.getElementById('AddTopForm');
   // Update Hierarchy result
   let iData = {}
   let implementationName = ele.parentElement.children[2].value
   let code = ele.parentElement.children[4].value
   let algoImplAdd = vm.selectedAlgo
   let Language = ele.parentElement.children[6].value
   iData["implName"] = implementationName
   //should have a url instead
   iData["codeUrl"] = code
   iData["language"] = Language
   iData["algoName"] = algoImplAdd

   //TODO:Add the algorithm name here
   if(implementationName === "") {
        alert("Please enter an Implementation name")
   } else {
            let js = JSON.stringify(iData)
            console.log("Create Implementation JSON: " + js);
            let xhr = new XMLHttpRequest();

            xhr.open("POST", createImplementation_url, true);
            xhr.send(js);
            console.log("sent create implementation request");



            // after we get a response
            xhr.onloadend = function() {
                console.log("create implementation response: " + xhr);

                if(xhr.readyState === XMLHttpRequest.DONE) {
                    if(xhr.status === 200) {
                        console.log("XHR: " + xhr.responseText);
                        processCreateImplementationResponse(xhr.responseText);
                    }
                    else {
                        console.log("Status != 200. Actual create response: " + xhr.responseText);
                        let newJS = JSON.parse(xhr.responseText);
                        let err = newJS["response"];
                        alert(err);
                    }
                }
                else {
                    processCreateImplementationResponse("N/A");
                }
            };


   }


}