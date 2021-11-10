function handleAdd(ele){
    //we need to add the html for the form right after the element that was added too,
    // so find the index and insert it in

    console.log("add")
    let ender = '</li>'
    let li = ele.parentElement.innerHTML
    let hierarchyHTML = document.getElementById('Hierarchy').innerHTML
    let formHTML = '<ul style="list-style: none;">' +
        '<li>'+
        ' <br><label for="Algorithm Name">Algorithm name:</label>' +
        '<input type="text" id="fname" name="fname">'+
        '<input type="submit" value="Submit" onclick="handleAddAlgorithmSubmit(\''+ ele.parentElement.children[1].textContent +'\', this)"><br><br>' +
        '</li>' +
        '</ul>'
    let newHierarchyHTML = ""
    //check if add was allready clicked
    if(hierarchyHTML.indexOf(formHTML) == hierarchyHTML.indexOf(li) + li.length + ender.length){
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

function handleAddAlgorithmSubmit(className, ele){
    console.log("Attempt to add Algorithm", ele.parentElement.children[2].value, " to ", className)

    //remove form
    ele.parentElement.parentElement.innerHTML = ''

    var data = {};
    data["algoName"] = ele.parentElement.children[2].value;


    data["className"] = className;

    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", createAlgorithm_url, true);

    // send the collected data as JSON
    xhr.send(js);

    // This will process results and update HTML as appropriate.
    xhr.onloadend = function () {



        console.log("addAlgorithm xhr: ", xhr);
        console.log(xhr.request);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if(xhr.status === 200) {
                console.log("XHR: " + xhr.responseText);
                console.log("added Algorithm", ele.parentElement.children[2].value, " to ", className)

                updateHierarchy()
            }
            else {
                console.log("Status != 200. Actual create response: " + xhr.responseText);
                let newJS = JSON.parse(xhr.responseText);
                let err = newJS["response"];
                alert(err);
            }
            //I know this leads to extra lambda function calls, we can fix it later
            //add the new algorithm to the hierarchy GUI

        } else {
            //failed so the hierarchy GUI doesn't need to be updated
        }
    };

}