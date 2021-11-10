function updateHierarchy() {
    console.log("updatingHierarchy")
    let xhr = new XMLHttpRequest();
    xhr.open("GET", getHierarchy_url, true);
    xhr.send();
    console.log("sent get hierarchy request");

    xhr.onloadend = function() {
        console.log(xhr.readyState)
        if(xhr.readyState === XMLHttpRequest.DONE) {
            processHierarchyResponse(xhr.responseText);
        }
        else {
            processHierarchyResponse("N/A");
        }
    };
}

function processHierarchyResponse(response) {
    // TODO pack the hierarchy up, then pass it along to display
    console.log("hierarchy response: " + response);
    console.log(JSON.parse(response))

    // TODO this structure can't be right can it?
    let js = JSON.parse(response)
    let output = '<ol style="list-style: none;">';

    for (let i = 0; i < js.topClassifications.length; i++) {
        output = output + addClassificationListItem(js.topClassifications[i])
    }
    output = output + '</ol>'

    let hierarchy = document.getElementById('Hierarchy');
    // Update hierarchy result
    hierarchy.innerHTML = output;
}

function createAlgorithmView(algoName) {
    return '<li class="listItem">'+
        '<h3 style="display:inline;">+</h3>' +
        '<h3 style="display:inline; margin-left: 20px;" onclick="handleAlgorithmView(this)">'+ algoName + '</h3>'
    if(isUserRegistered) {
        output += '<button style="background-color: red; margin-left: 60px;" class="button" onclick="handleAlgorithmDelete(this)">Del</button>'
    }
        output += '</li>'
}

function createClassificationView(classificationName, isUserRegistered) {
    let output ='<li class="listItem">'+
        '<h3 style="display:inline;">+</h3>'+
        '<h3 style="margin-left: 20px;" class="button">' + classificationName + '</h3>'
    if(isUserRegistered) {
        output +=
            '<button style="background-color: purple; margin-left: 50px;" class="button" onclick="handleClassificationMerge(this)">Merge</button>'+
            '<button style="background-color: green; margin-left: 10px;" class="button" onclick="handleAdd(this)">Add</button>'+
            '<button style=" background-color: red; margin-left: 10px;" class="button" onclick="handleClassificationDelete(this)">Del</button>'
    }
    output += '</li>' +
        '<li style="list-style-type:none">'+
        '<ul style="list-style: none;">'

    return output
}

function addClassificationListItem(item){
    let output = ""
    //classification
    output = createClassificationView(item.className, true)
    for (let j = 0; j < item.algorithms.length; j++) {
        output = output + createAlgorithmView(item.algorithms[j].algoName)
    }

    for (let j = 0; j < item.subclassifications.length; j++) {
        output = output + addClassificationListItem(item.subclassifications[j])
    }
     output = output + '</ul></li>'



    return output
}