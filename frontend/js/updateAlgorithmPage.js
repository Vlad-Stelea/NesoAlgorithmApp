function updateAlgorithmPageHierarchy() {
    console.log("updatingHierarchyImpl")
    let onSuccessCallback = function (data) {
        renderImplementationDisplay(data);
    };

    let onFailCallback = function (data, status) {
        // TODO handle when there is an issue Not implemented rn as out of scope
    }

    algorithmRepo.getAlgorithmHierarchy(vm.selectedAlgo, onSuccessCallback, onFailCallback)

}

function renderImplementationDisplay(algorithmHierarchy){
    let output2 = '<ol style="list-style: none;">';

    output2 = output2 + addImplementationListItem(algorithmHierarchy.algorithmPage.algorithm)


    let implementation = document.getElementById('Implementation');
    implementation.innerHTML = output2;
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
    console.log(item)
    for (let j = 0; j < item.implementations.length; j++) {
        output = output + createImplementationView(item.implementations[j],vm.user.token !== '')
    }

    output = output + '</ul></li>'



    return output



}