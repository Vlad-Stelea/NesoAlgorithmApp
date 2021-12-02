function handleAddImpPrep(){
    let addTopClassForm = document.getElementById("addImplementationForm");
    addTopClassForm.style.visibility = "visible"
}

function createImplementation() {
    implName = document.getElementById("implNameInput").value;
    language = document.getElementById("languageInput").value;
    // TODO make sure file upload is also included
    if(implName && language){
        // TODO submit
        console.log("submitting implementation")
        document.getElementById("addImplementationForm").style.visibility = "hidden";
    } else if(!implName) {
        alert("Please include an implementation name")
    } else if(!language) {
        alert("Please include the language this implementation uses")
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