function handleAddTopLevelPrep(){
    console.log("addTopPrep")
    let addTopClassForm = document.getElementById('AddTopForm');
   //check to see if the form is allready displayed, ad if it is remove it
    if(addTopClassForm.innerHTML === ''){
        addTopClassForm.innerHTML = ' <br><label for="classificationName">Classification name:</label>' +
            '<input type="text" id="fname" name="fname">'+
            '<input type="submit" value="Submit" onclick="handleAddTopLevelSubmit(this)"><br><br>'
    }else{
        addTopClassForm.innerHTML = ''

    }

}

function handleAddTopLevelSubmit(ele){
    console.log("addTopSubmit");
    //used to remove the form once we submit
    let addTopClassForm = document.getElementById('AddTopForm');

    let onSuccessCallback = function (data) {
        console.log("XHR: " + data.responseText);
        console.log("added top classification", ele.parentElement.children[2].value);
        addTopClassForm.innerHTML = '';
        updateHierarchy();
    }

    // Note, this doesn't work since lambda function doesn't return anything other than 200
    let onFailCallback = function (data, status) {
        console.log("Status != 200. Actual create response: " + xhr.responseText);
        let newJS = JSON.parse(xhr.responseText);
        let err = newJS["response"];
        alert(err);
    }

    let className = ele.parentElement.children[2].value;
    classificationRepo.addClassification(className, null, onSuccessCallback, onFailCallback);


}

