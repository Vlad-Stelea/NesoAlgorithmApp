function handleAdd(ele){
    console.log("add")
    let ender = '</li>'
    let li = ele.parentElement.innerHTML
    let hierarchyHTML = document.getElementById('Hierarchy').innerHTML
    let newHierarchyHTML = hierarchyHTML.substr(0, hierarchyHTML.indexOf(li) + li.length + ender.length)
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
    console.log(ele.innerHTML)
    console.log(ele.parentElement.innerHTML)
    console.log()
    ele.parentElement.parentElement.innerHTML = ''
}

