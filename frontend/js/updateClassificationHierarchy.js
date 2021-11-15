function updateHierarchy() {
    console.log("updatingHierarchy")
    let onSuccessCallback = function (data, textStatus, xhr) {
        if(xhr.status === 200) {
            renderHierachy(data);
        }
    };

    let onFailCallback = function (data, textStatus, xhr) {
        // TODO handle when there is an issue Not implemented rn as out of scope
    }
    classificationRepo.getClassificationHeiracy(onSuccessCallback, onFailCallback)
}

function updateHierarchyImplementation() {
    console.log("updatingHierarchy")
    let onSuccessCallback = function (data, textStatus, xhr) {
        if(xhr.status === 200) {
            renderImplementationDisplay(data);
        }
    };

    let onFailCallback = function (data, textStatus, xhr) {
        // TODO handle when there is an issue Not implemented rn as out of scope
    }

    classificationRepo.getClassificationHeiracy(onSuccessCallback, onFailCallback)

}

function renderHierachy(hierachy) {
    console.log("hierarchy response: " + hierachy);

    // TODO this structure can't be right can it?
    let output = '<ol style="list-style: none;">';

    for (let i = 0; i < hierachy.topClassifications.length; i++) {
        output = output + addClassificationListItem(hierachy.topClassifications[i])

    }
    output = output + '</ol>'
    let hierarchy = document.getElementById('Hierarchy');
    hierarchy.innerHTML = output;
}

function renderImplementationDisplay(heirachy){
    let output2 = '<ol style="list-style: none;">';
    //TODO:dig through subclassifications
    for (let i = 0; i < heirachy.topClassifications.length; i++) {
       item = heirachy.topClassifications[i]
        for (let j = 0; j < item.algorithms.length; j++) {
            if(vm.selectedAlgo === item.algorithms[j].algoName){
                console.log(item.algorithms[j])
                output2 = output2 + addImplementationListItem(item.algorithms[j])
            }
        }
    }

    let implemenation = document.getElementById('Implementation');
    implemenation.innerHTML = output2;
}

function createAlgorithmView(algoName, isUserRegistered) {
    let output = '<li class="listItem" style="background-color: pink">'+
        '<h3 style="display:inline;">+</h3>' +
        '<h3 style="display:inline; margin-left: 20px;" onclick="handleAlgorithmView(this)">'+ algoName + '</h3>' +
        '<button style="background-color: blue; margin-left: 50px;" class="button" onclick="navigation.goToAlgorithmPage(\'' + algoName  + '\')">Algo Page</button>';
    if(isUserRegistered) {
        output += '<button style="background-color: red; margin-left: 60px;" class="button" onclick="handleAlgorithmDelete(this)">Del</button>';
    }
    output += '</li>';
    return output;
}

function createClassificationView(classificationName, isUserRegistered) {
    let output ='<li class="listItem" style="background-color: tomato">'+
        '<h3 style="display:inline;">+</h3>'+
        '<h3 style="margin-left: 20px;" class="button">' + classificationName + '</h3>'
    if(isUserRegistered) {
        output +=
            '<button style="background-color: purple; margin-left: 50px;" class="button" onclick="handleClassificationMerge(this)">Merge</button>'+
            '<button style="background-color: green; margin-left: 10px;" class="button" onclick="handleAdd(this)">Add Algorithm</button>'+
            '<button style=" background-color: red; margin-left: 10px;" class="button" onclick="handleClassificationDelete(this)">Del</button>'
    }
    output += '</li>' +
        '<li style="list-style-type:none">'+
        '<ul style="list-style: none;">'

    return output
}
function createImplementationView(item, isUserRegistered){
    output = ""
    //Implementation
    //'<h3 style="margin-left: 20px;" class="button"> Code Url: <a href=' + item.codeURL +' target="_blank">'+item.codeURL + '</a></h3>'+
    output = '<li class="listItem" style="background-color: sandybrown">' +
        '<h2> Implementation: ' + item.implName +'</h2>'
    if(isUserRegistered) {
        output = output + '<h3 style="background-color: green; margin-left: 20px;display :flex;" class="button" onclick="handleAdd(this)">Add Benchmark</h3>'+
            '<h3 style=" background-color: red; margin-left: 20px;display :flex;" class="button" onclick="handleImplementationDelete(this)">Del</h3>'
    }
    output = output + '<h3 style="margin-left: 20px;" class="button"> Language: ' + item.language + '</h3>'+
        '<h style="display:inline;word-wrap:break-word">code: ' +item.codeURL + '</h>' +
        '<div class="content">'+
        '<h3 style="; margin-left: 20px;" "> Language: '+item.language + '</h3>'+
        '<h3 style="display:inline; margin-left: 20px;" ">Url: <a href=' + item.codeURL +'>'+item.codeURL + '</a></h3>'+
        '</div>'+
        '</li>'+
        '<li style="list-style-type:none">'+
        '<ul style="list-style: none;">'
    if(item.ProblemInstances){
        for (var j = 0; j < item.ProblemInstances.length; j++) {
            output = output + displayProblemInstances(item.ProblemInstances[j],isUserRegistered)
        }
    }
    output = output + '</ul></li>'
    return output
}


function addImplementationListItem(item){
    let output = ""
    //Implementation
    console.log(item.implementations)
    for (let j = 0; j < item.implementations.length; j++) {
        output = output + createImplementationView(item.implementations[j],vm.user.token !== '')
    }

    output = output + '</ul></li>'



    return output



}


function addClassificationListItem(item){
    let output = ""
    //classification

    output = createClassificationView(item.className, vm.user.token !== '')
    for (let j = 0; j < item.algorithms.length; j++) {
        output = output + createAlgorithmView(item.algorithms[j].algoName,vm.user.token !== '')
    }

    for (let j = 0; j < item.subclassifications.length; j++) {
        output = output + addClassificationListItem(item.subclassifications[j])
    }
     output = output + '</ul></li>'



    return output
}