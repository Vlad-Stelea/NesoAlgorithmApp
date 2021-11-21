function updateAlgorithmPageHierarchy() {
    console.log("updatingHierarchyImpl")
    let onSuccessCallback = function (data) {
        console.log(data);
        renderImplementationDisplay(data);
        renderProblemInstanceList(data.algorithmPage.algorithm.problemInstances);
        renderMachineConfigurationList(data.algorithmPage.machineConfigurations);
    };

    let onFailCallback = function (data, status) {
        // TODO handle when there is an issue Not implemented rn as out of scope
    }

    algorithmRepo.getAlgorithmHierarchy(vm.selectedAlgo, onSuccessCallback, onFailCallback)

}

function renderImplementationDisplay(algorithmHierarchy){
    let output2 = '<ol style="list-style: none;">';

    output2 = output2 + addImplementationList(algorithmHierarchy.algorithmPage.algorithm)

    let implementation = document.getElementById('Implementation');
    implementation.innerHTML = output2;
}

function createImplementationView(item, isUserRegistered){
    output = ""
    //Implementation
    //'<h3 style="margin-left: 20px;" class="button"> Code Url: <a href=' + item.codeURL +' target="_blank">'+item.codeURL + '</a></h3>'+
    output = '<li class="listItem" style="background-color: sandybrown">' +
        '<h2 style="display:inline;"> Implementation: ' + item.implName +'</h2>' +
        '<button style="background-color: green; margin-left: 20px;" class="button" onclick="handleAdd(this)">Add Benchmark</button>'
    if(isUserRegistered) {
        output = output + '<button style=" background-color: red; margin-left: 20px;" class="button" onclick="handleImplementationDelete(this, \'' + item.implName + '\', \'' + item.algorithmName + '\')">Del</button>'
    }
    output = output + '<h3 style="margin-left: 20px;" class="language"> Language: ' + item.language + '</h3>'+
        '<h style="display:inline;word-wrap:break-word">Code Download Link: </h>' + '<a href="' + item.codeURL + '">Download</a>' +
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


function addImplementationList(item){
    let output = ""
    //Implementation
    console.log(item)
    for (let j = 0; j < item.implementations.length; j++) {
        output = output + createImplementationView(item.implementations[j],vm.user.token !== '')
    }

    output = output + '</ul></li>'

    return output
}

function renderMachineConfigurationList(machineConfigs){
    let output = '<ol style="list-style: none;">';

    for (let j = 0; j < machineConfigs.length; j++) {
        output = output + createMachineConfigurationView(machineConfigs[j],vm.user.token !== '')
    }

    output = output + '</ul></li>'
    let machineConfigurationList = document.getElementById('MachineConfigs');
    machineConfigurationList.innerHTML = output;
}

function createMachineConfigurationView(machineConfig, isRegisteredUser){
    output = ""
    //Implementation
    //'<h3 style="margin-left: 20px;" class="button"> Code Url: <a href=' + item.codeURL +' target="_blank">'+item.codeURL + '</a></h3>'+
    output = '<li class="listItem" style="background-color: sandybrown">' +
        '<h2 style="display:inline;">' + machineConfig.machineConfigName +'</h2>'
    if(isRegisteredUser){
        output = output + '<button style="background-color: red; margin-left: 20px;" class="button" >Del</button>';
    }
    output = output + '<div style="margin-left: 15px;">'+
        '<h style="display:inline;" > L1 Cache: ' + machineConfig.l1Cache + '</h>'
        + '<h style="display:inline; margin-left: 15px;" > L2 Cache: ' + machineConfig.l2Cache + '</h>'
        + '<h style="display:inline; margin-left: 15px;" > Threads: ' + machineConfig.threads + '</h>'
        + '<h style="display:inline; margin-left: 15px;" > Chip: ' + machineConfig.chip + '</h>' + '</div>' +
        '</li>'

    return output
}

function renderProblemInstanceList(problemInstance){
    let output = '<ol style="list-style: none;">';

    for (let j = 0; j < problemInstance.length; j++) {
        output = output + createProblemInstanceView(problemInstance[j],vm.user.token !== '')
    }

    output = output + '</ul></li>'
    let problemInstanceList = document.getElementById('ProblemInstances');
    problemInstanceList.innerHTML = output;
}

function createProblemInstanceView(problemInstance, isRegisteredUser){
    console.log(problemInstance);
    output = ""
    //Implementation
    //'<h3 style="margin-left: 20px;" class="button"> Code Url: <a href=' + item.codeURL +' target="_blank">'+item.codeURL + '</a></h3>'+
    output = '<li class="listItem" style="background-color: sandybrown">' +
        '<h2 style="display:inline;">' + problemInstance.probInstanceName +'</h2>'
    if(isRegisteredUser) {
        output = output + '<button style="background-color: red; margin-left: 20px;" class="button" >Del</button>';
//onclick="handleImplementationDelete(this)"
    }
    output = output + '<div>'+
        '<h style="display:inline;"> Download Link: </h>' + '<a href="' + problemInstance.datasetURL + '">Download</a>'
        '</li>'

    return output
}