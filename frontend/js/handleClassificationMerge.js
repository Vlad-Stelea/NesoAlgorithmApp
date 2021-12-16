function handleClassificationMerge(ele){
    //we need to add the html for the form right after the element that was added too,
    // so find the index and insert it in

    console.log("Merge Classification")
    let ender = '</li>'
    let li = ele.parentElement.innerHTML
    let hierarchyHTML = document.getElementById('Hierarchy').innerHTML
    let formHTML = '<ul style="list-style: none;">' +
        '<li>'+
        ' <br><label for="Classification Name">Classification to Merge:</label>' +
        '<input type="text" id="cOther" name="cOther">'+
        ' <br><label for="New Name">New Name:</label>' +
        '<input type="text" id="nName" name="nname">'+
        '<input type="submit" value="Submit" onclick="mergeClassification(\''+  ele.parentElement.children[1].textContent +'\',this)"><br><br>' +
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
    document.getElementById('Hierarchy').innerHTML = newHierarchyHTML

}

function mergeClassification(parentClass,ele){
    console.log("handleClassificationMerge")
    let class1 = parentClass.trim();
    let class2 = ele.parentElement.children[2].value.trim();
    let newName = ele.parentElement.children[5].value.trim();


    let onSuccessCallback = function (data) {
        console.log("XHR: " + data.responseText);
        console.log("Merge classification" + class1 + " in to " + class2 + "with the name" + newName);
        updateHierarchy();
    }

    let onFailCallback = function (data) {
        console.log("Status != 200. Merge classification response: " + data);

        let err = data["response"];
        alert(err);
    }

    classificationRepo.mergeClassification(class1, class2,newName, onSuccessCallback, onFailCallback)

}
