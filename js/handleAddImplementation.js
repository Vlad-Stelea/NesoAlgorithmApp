function handleAddImpPrep(){
    console.log("addImpPrep")
    let addTopClassForm = document.getElementById('AddImpForm');
   // Update Hierarchy result
   addTopClassForm.innerHTML = ' <br><label for="ImplementationName">Implementation name:</label>' +
                            '<input type="text" id="fname" name="fname">'+
                            '<label for="Code">Code:</label>' +
                                                        '<input type="text" id="Code" name="Code">'+
                            '<label for="Language">Language:</label>' +
                                    '<input type="text" id="lang" name="lang">'+
                            '<input type="submit" value="Submit" onclick="handleAddTopLevelSubmit(this)"><br><br>'

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

   let Language = ele.parentElement.children[6].value
   iData["implName"] = implementationName
   //should have a url instead
   iData["codeUrl"] = code
   iData["language"] = Language
   //TODO:Add the algorithm name here
   if(implementationName === "") {
        alert("Please enter an Implementation name")
   } else {
            let js = JSON.stringify(iData)
            console.log("Create Implementation JSON: " + js);
            let xhr = new XMLHttpRequest();

            /**xhr.open("POST", createClassification_url, true);
            xhr.send(js);
            console.log("sent create classification request");



            // after we get a response
            xhr.onloadend = function() {
                console.log("create classification response: " + xhr);

                if(xhr.readyState === XMLHttpRequest.DONE) {
                    if(xhr.status === 200) {
                        console.log("XHR: " + xhr.responseText);
                        processCreateClassificationResponse(xhr.responseText);
                    }
                    else {
                        console.log("Status != 200. Actual create response: " + xhr.responseText);
                        let newJS = JSON.parse(xhr.responseText);
                        let err = newJS["response"];
                        alert(err);
                    }
                }
                else {
                    processCreateClassificationResponse("N/A");
                }
            };*/


   }


}