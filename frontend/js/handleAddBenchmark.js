function handleBenchmarkAdd(ele, implName, algoName, problemInstances) {
    console.log("handling benchmark add");
    pis = JSON.parse(atob(problemInstances));

    let addBenchmarkForm = document.getElementById("addBenchmarkForm" + implName);

    if(addBenchmarkForm.innerHTML != ''){
        addBenchmarkForm.innerHTML = '';
        return;
    }

    let addBMFormHTML = "<form id='createBenchmarkForm' method='post'>" +
        "<br/><label for='benchmarkName'>Benchmark name: </label>" +
        "<input type='text' id='benchmarkName' name='benchmarkName'/>" +
        "<br/><label for='benchmarkTimeToRun'>Time to run: </label>" +
        "<input type='number' id='benchmarkTimeToRun' name='benchmarkTimeToRun'/><span> (seconds)</span>" +
        "<br/><label for='benchmarkDateRun'>Date run: </label>" +
        "<input type='date' id='benchmarkDateRun' name='benchmarkDateRun'/>" +
        "<br/><label for='benchmarkMachineConfigName'>Machine configuration name: </label>" +
        "<input type='text' id='benchmarkMachineConfigName' name='benchmarkMachineConfigName'/>" +
        "<br/><label for='probInstanceName'>Problem Instance name: </label>" +
        "<select name='probInstanceName' id='probInstanceName'>"
        for(let i = 0; i < pis.length; i++){
            addBMFormHTML += "<option value='" + pis[i].probInstanceUUID + "'>" + pis[i].probInstanceName + "</option>"
        }
        addBMFormHTML += "</select>" +
        "<br/><input id='submitBenchmarkButton' type='button' value='Submit' onclick='handleAddBenchmarkFormSubmit(this.parentElement, \"" + implName + "\", \"" + algoName + "\")'/>" +
        "</form><br/>";

    addBenchmarkForm.innerHTML = addBMFormHTML;

}

function handleAddBenchmarkFormSubmit(formElement, implName, algoName) {
    console.log("benchmark form submit");
    console.log(formElement.id)

    implName = implName.trim();
    algoName = algoName.trim();
    let benchmarkName = formElement[0].value.trim();
    let timeToRun = formElement[1].value;
    let dateRun = formElement[2].value;
    let machineConfigName = formElement[3].value.trim();
    let problemInstanceUUID = formElement[4].value.trim();

    if(benchmarkName && machineConfigName) {
        let onSuccessCallback = function (xhr) {
            console.log("XHR response: " + JSON.stringify(xhr, null, 4));
            console.log("added benchmark: " + xhr["benchName"]);
            updateAlgorithmPageHierarchy();
        }

        let onFailCallback = function (xhr) {
            console.log("XHR response: " + JSON.stringify(xhr, null, 4));
            console.log("failed to add benchmark.");
        }

        benchmarkRepo.addBenchmark(benchmarkName, algoName, machineConfigName, implName, problemInstanceUUID, dateRun, timeToRun, onSuccessCallback, onFailCallback);
        // document.getElementById(formElement.id).innerHTML = "";

    }
    else {
        alert("Please enter non-empty benchmark and machine configuration names.");
    }

}
