function handleAddSubClassificationPrep(ele){
    //we need to add the html for the form right after the element that was added too,
    // so find the index and insert it in

    console.log("add Classification")
    let ender = '</li>'
    let li = ele.parentElement.innerHTML
    let hierarchyHTML = document.getElementById('Hierarchy').innerHTML
    let formHTML = '<ul style="list-style: none;">' +
        '<li>'+
        ' <br><label for="Classification Name">Classification name:</label>' +
        '<input type="text" id="fname" name="fname">'+
        '<input type="submit" value="Submit" onclick="addSubClassification(\''+ ele.parentElement.children[1].textContent +'\', this)"><br><br>' +
        '</li>' +
        '</ul>'
    let newHierarchyHTML;
    //check if add was allready clicked
    if(hierarchyHTML.indexOf(formHTML) === hierarchyHTML.indexOf(li) + li.length + ender.length){
        //remove the form
        newHierarchyHTML = hierarchyHTML.substr(0, hierarchyHTML.indexOf(formHTML)) + hierarchyHTML.substr( hierarchyHTML.indexOf(formHTML)+formHTML.length)
    }else {
        //add the form
        //front half
        newHierarchyHTML = hierarchyHTML.substr(0, hierarchyHTML.indexOf(li) + li.length + ender.length)
        //add in and append the rest of the html
        newHierarchyHTML = newHierarchyHTML + formHTML + hierarchyHTML.substr(hierarchyHTML.indexOf(li) + li.length + ender.length)
    }
    document.getElementById('Hierarchy').innerHTML = newHierarchyHTML;
}

function addSubClassification(parentClassName, ele){
    ele.parentElement.parentElement.innerHTML = '';

    let onSuccessCallback = function (data) {
        console.log("XHR: " + data.responseText);
        console.log("added subclassification", ele.parentElement.children[2].value, " to ", parentClassName)
        updateHierarchy();
    }

    let onFailCallback = function (data, status) {
        console.log("Status != 200. Actual create response: " + xhr.responseText);
        let newJS = JSON.parse(xhr.responseText);
        let err = newJS["response"];
        alert(err);
    }

    let className = ele.parentElement.children[2].value.trim();
    parentClassName = parentClassName.trim();
    classificationRepo.addClassification(className, parentClassName, onSuccessCallback, onFailCallback);

}