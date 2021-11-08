function handleAddTopLevelPrep(){
    console.log("addTopPrep")
    let addTopClassForm = document.getElementById('AddTopForm');
   // Update Hierarchy result
   addTopClassForm.innerHTML = ' <br><label for="classificationName">Classification name:</label>' +
                            '<input type="text" id="fname" name="fname">'+
                            '<input type="submit" value="Submit" onclick="handleAddTopLevelSubmit(this)"><br><br>'

}

function processCreateClassificationResponse(result) {
    console.log("create classification response: " + result);

    updateHierarchy();
}

function handleAddTopLevelSubmit(ele){
    console.log("addTopSubmit")
    console.log(ele.parentElement.children[2].value)
    let addTopClassForm = document.getElementById('AddTopForm');

    let className = ele.parentElement.children[2].value
    let cData = {}
    cData["className"] = className
    // TODO need to update this when we get the form to accept non-top level classifications
    cData["parentClassName"] = null


    if(cData["className"] === "") {
        alert("Please enter a non-empty Classification name")
    }
    else {
        let js = JSON.stringify(cData);
        console.log("Create Classification JSON: " + js);
        let xhr = new XMLHttpRequest();

        xhr.open("POST", createClassification_url, true);
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
        };
    }

}

