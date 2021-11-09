function handleAdd(ele){
    //we need to add the html for the form right after the element that was added too,
    // so find the index and insert it in
    console.log("add")
    let ender = '</li>'
    let li = ele.parentElement.innerHTML
    let hierarchyHTML = document.getElementById('Hierarchy').innerHTML
    //front half
    let newHierarchyHTML = hierarchyHTML.substr(0, hierarchyHTML.indexOf(li) + li.length + ender.length)
    //add in and append the rest of the html
    newHierarchyHTML = newHierarchyHTML +
        '<ul style="list-style: none;">' +
        '<li>'+
        ' <br><label for="Algorithm Name">Algorithm name:</label>' +
        '<input type="text" id="fname" name="fname">'+
        '<input type="submit" value="Submit" onclick="handleAddClassificationSubmit(\''+ ele.parentElement.children[1].textContent +'\', this)"><br><br>' +
        '</li>' +
        '</ul>' + hierarchyHTML.substr(hierarchyHTML.indexOf(li) + li.length + ender.length)
    document.getElementById('Hierarchy').innerHTML = newHierarchyHTML
}

function handleAddClassificationSubmit(className, ele){
    console.log("addAlgorithm")
    console.log(ele.parentElement.children[2].value)
    console.log("to")
    console.log(className)

    //remove form
    ele.parentElement.parentElement.innerHTML = ''

    var data = {};
    data["algoName"] = ele.parentElement.children[2].value;


    data["className"] = className;

    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://jdkme371z4.execute-api.us-east-2.amazonaws.com/preAlpha/Algorithm", true);

    // send the collected data as JSON
    xhr.send(js);

    // This will process results and update HTML as appropriate.
    xhr.onloadend = function () {



        console.log(xhr);
        console.log(xhr.request);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            //I know this leads to extra lambda function calls, we can fix it later
            initialize()
        } else {
            initialize()
        }
    };

}