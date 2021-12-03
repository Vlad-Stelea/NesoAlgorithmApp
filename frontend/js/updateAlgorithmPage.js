function updateAlgorithmPageHierarchy(admin = false) {
    console.log("updatingAlgoHierarchyImpl")
    let onSuccessCallback = function (data) {
        console.log(data);
        let pis = renderProblemInstanceList(data.algorithmPage.algorithm.problemInstances);
        let mcs = renderMachineConfigurationList(data.algorithmPage.machineConfigurations);

        renderImplementationDisplay(data, pis, mcs, admin);
    };

    let onFailCallback = function (data, status) {
        // TODO handle when there is an issue Not implemented rn as out of scope
    }

    algorithmRepo.getAlgorithmHierarchy(vm.selectedAlgo, onSuccessCallback, onFailCallback)

}

function renderImplementationDisplay(algorithmHierarchy, pis, mcs, admin){
    let output2 = '<ol style="list-style: none;">';

    output2 = output2 + addImplementationList(algorithmHierarchy.algorithmPage.algorithm, pis, mcs, admin)

    let implementation = document.getElementById('Implementation');
    implementation.innerHTML = output2;
}

function createImplementationView(item, isUserRegistered, admin){
    //Implementation
    //'<h3 style="margin-left: 20px;" class="button"> Code Url: <a href=' + item.codeURL +' target="_blank">'+item.codeURL + '</a></h3>'+
    let output = '<li class="listItem" style="background-color: sandybrown">' +
        '<h2 style="display:inline;"> Implementation: ' + item.implName +'</h2>';
    if(!admin){
        output += '<button style="background-color: green; margin-left: 20px;" class="button" onclick="handleAdd(this)">Add Benchmark</button>';
    }
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


function addImplementationList(item, pis, mcs, admin){
    let output = '';
    //Implementation
    console.log(item)
    for (let j = 0; j < item.implementations.length; j++) {
        output = output + createImplementationView(item.implementations[j],vm.user.token !== '', admin) +
            '<li style="list-style-type:none">'+
            '<ul style="list-style: none;">'
        output = output + renderBenchmarkList(item.implementations[j].benchmark, pis, mcs) + '</ul></li>';
    }


    return output
}

function renderMachineConfigurationList(machineConfigs){
    mcDictionary = {};

    let output = '<ol style="list-style: none;">';

    for (let j = 0; j < machineConfigs.length; j++) {

        output = output + createMachineConfigurationView(machineConfigs[j],vm.user.token !== '')

        //save for latter
        mcDictionary[machineConfigs[j].machineConfigUUID] = machineConfigs[j];

    }

    let machineConfigurationList = document.getElementById('MachineConfigs');
    machineConfigurationList.innerHTML = output;

    return mcDictionary;
}

function createMachineConfigurationView(machineConfig, isRegisteredUser){
    //Implementation
    //'<h3 style="margin-left: 20px;" class="button"> Code Url: <a href=' + item.codeURL +' target="_blank">'+item.codeURL + '</a></h3>'+
    let output = '<li class="listItem" style="background-color: pink">' +
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
    piDictionary = {}
    let output = '<ol style="list-style: none;">';

    for (let j = 0; j < problemInstance.length; j++) {

        output = output + createProblemInstanceView(problemInstance[j],vm.user.token !== '')

        //save it for later so we can easialy display it in the implementation hierarchy
        piDictionary[problemInstance[j].probInstanceUUID] = problemInstance[j];
    }

    let problemInstanceList = document.getElementById('ProblemInstances');
    problemInstanceList.innerHTML = output;
    return piDictionary;
}

function createProblemInstanceView(problemInstance, isRegisteredUser){
    // console.log(problemInstance);
    //Implementation
    //'<h3 style="margin-left: 20px;" class="button"> Code Url: <a href=' + item.codeURL +' target="_blank">'+item.codeURL + '</a></h3>'+
    let output = '<li class="listItem" style="background-color: tomato">' +
        '<h2 style="display:inline;">' + problemInstance.probInstanceName +'</h2>'
    if(isRegisteredUser) {
        output = output + '<button style="background-color: red; margin-left: 20px;" class="button" onclick="handleProblemInstanceDelete(this, \'' + problemInstance.probInstanceUUID + '\')">Del</button>';
    }
    output = output + '<div>' +
        '<h style="display:inline;"> Download Link: </h>' + '<a href="' + problemInstance.datasetURL + '">Download</a>' +
        '</li>';

    return output
}

function createProblemInstanceLabledView(problemInstance, isRegisteredUser){
    // console.log(problemInstance);
    //Implementation
    //'<h3 style="margin-left: 20px;" class="button"> Code Url: <a href=' + item.codeURL +' target="_blank">'+item.codeURL + '</a></h3>'+
    let output = '<li class="listItem" style="background-color: tomato">' +
        '<h2 style="display:inline;">Problem Instance: ' + problemInstance.probInstanceName +'</h2>'
    if(isRegisteredUser) {
        output = output + '<button style="background-color: red; margin-left: 20px;" class="button" onclick="handleProblemInstanceDelete(this, ' + problemInstance.probInstanceUUID + ')">Del</button>';
    }
    output = output + '<div>' +
        '<h style="display:inline;"> Download Link: </h>' + '<a href="' + problemInstance.datasetURL + '">Download</a>' +
        '</li>';

    return output
}

function createMachineConfigurationLabledView(machineConfig, isRegisteredUser){
    //Implementation
    //'<h3 style="margin-left: 20px;" class="button"> Code Url: <a href=' + item.codeURL +' target="_blank">'+item.codeURL + '</a></h3>'+
    let output = '<li class="listItem" style="background-color: pink">' +
        '<h2 style="display:inline;">Machine Configuration: ' + machineConfig.machineConfigName +'</h2>'
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


function renderBenchmarkList(benchmarks, pis, mcs){
    let output = '<ol style="list-style: none;">';

    let piL = getProbInstanceList(benchmarks);

    for (let j = 0; j < piL.length; j++){

        output = output + createProblemInstanceLabledView(pis[piL[j].name],false) +
            '<li style="list-style-type:none">'+
            '<ul style="list-style: none;">'
        for (let k = 0; k < piL[j].bms.length; k++) {
            output = output + createBenchmarkView(piL[j].bms[k],vm.user.token !== '') +
                '<li style="list-style-type:none">'+
                '<ul style="list-style: none;">'
            output = output + createMachineConfigurationLabledView(mcs[benchmarks[j].machineConfigName], false) +
                '</ul></li>'
        }
        output = output + '</ul></li>'
    }


    output = output + '</ul></li>';
    return output;
}

function getProbInstanceList(benchmarks){
    let piL = [];
    let pis = {};
    for(let j = 0; j < benchmarks.length; j++){
        if(pis[benchmarks[j].problemInstanceName]==undefined){
            //add a new PI
            let newPI = {};
            newPI["name"] = benchmarks[j].problemInstanceName;
            let bms = [benchmarks[j]];
            newPI["bms"] = bms;
            piL.push(newPI);
            pis[benchmarks[j].problemInstanceName] = newPI;
        }else{
            //there already exists this PI
            pis[benchmarks[j].problemInstanceName]["bms"].push(benchmarks[j])
        }
    }
    return piL;

}

function createBenchmarkView(benchmark, isRegisteredUser){
    // console.log(problemInstance);
    //Implementation
    //'<h3 style="margin-left: 20px;" class="button"> Code Url: <a href=' + item.codeURL +' target="_blank">'+item.codeURL + '</a></h3>'+
    let output = '<li class="listItem" style="background-color: deeppink">' +
        '<h2 style="display:inline;">Benchmark: ' + benchmark.benchName +'</h2>'
    if(isRegisteredUser) {
        output = output + '<button style="background-color: red; margin-left: 20px;" class="button" onclick="handleBenchmarkDelete(this, ' + benchmark.benchamrkUUID + ')">Del</button>';
    }
    output = output + '<div>' +
        '<h style="display:inline;"> Time to Run: ' + benchmark.timeToRun + '</h>' + '<h style="display:inline; margin-left: 15px"> Date Run: ' + benchmark.dateRun + '</h>' +
        '</li>';

    return output
}