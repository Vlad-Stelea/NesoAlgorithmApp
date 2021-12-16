function handleReclassifyAlgorithm(ele) {
    console.log("reclassify algo");

    let ender = '</li>';
    let algoName = ele.parentElement.children[1].textContent;
    let algoLI = ele.parentElement.innerHTML;
    let hierarchyHTML = document.getElementById('Hierarchy').innerHTML;
    let reclassifyFormHTML = '<ul style="list-style: none;">' +
        '<li>'+
        '<br><label for="Classification Name">Classification name:</label>' +
        '<input type="text" id="className_reclassify" name="className_reclassify">'+
        '<input type="submit" value="Submit" onclick="reclassifyAlgorithm(\''+ algoName +'\', this)"><br><br>' +
        '</li>' +
        '</ul>';

    let newHierarchyHTML;
    // remove the form if add was hit already
    if(hierarchyHTML.indexOf(reclassifyFormHTML) === hierarchyHTML.indexOf(algoLI) + algoLI.length + ender.length) {
        newHierarchyHTML = hierarchyHTML.substr(0, hierarchyHTML.indexOf(reclassifyFormHTML)) + hierarchyHTML.substr( hierarchyHTML.indexOf(reclassifyFormHTML)+reclassifyFormHTML.length);
    }
    // otherwise, create the form in a couple steps
    else {
        newHierarchyHTML = hierarchyHTML.substr(0, hierarchyHTML.indexOf(algoLI) + algoLI.length + ender.length);
        newHierarchyHTML = newHierarchyHTML + reclassifyFormHTML + hierarchyHTML.substr(hierarchyHTML.indexOf(algoLI) + algoLI.length + ender.length);
    }

    document.getElementById('Hierarchy').innerHTML = newHierarchyHTML;

}

function reclassifyAlgorithm(algoName, ele) {
    // remove the form
    ele.parentElement.parentElement.innerHTML = '';

    let onSuccessCallback = function (data) {
        console.log("XHR: " + data.responseText);
        console.log("reclassified Algorithm" + algoName + " to " + ele.parentElement.children[2].value);
        updateHierarchy();
    }

    // Note, this doesn't work since lambda function doesn't return anything other than 200
    let onFailCallback = function (data, status) {
        console.log("Status != 200. Actual reclassify response: " + xhr.responseText);
        let newJS = JSON.parse(xhr.responseText);
        let err = newJS["response"];
        alert(err);
    }

    let className = ele.parentElement.children[2].value.trim();
    algoName = algoName.trim();
    algorithmRepo.reclassifyAlgorithm(algoName, className, onSuccessCallback, onFailCallback)

}
