function handleBenchmarkAdd(ele, probInstanceUUID, implName, algoName) {
    console.log("handling benchmark add");
    console.log(ele);

    let addBenchmarkForm = document.getElementById("addBenchmarkForm");

    addBenchmarkForm.innerHTML = "<form id='createBenchmarkForm' method='post'>" +
        "<br/><label for='benchmarkName'>Benchmark name: </label>" +
        "<input type='text' id='benchmarkName' name='benchmarkName'/>" +
        "<br/><label for='benchmarkTimeToRun'>Time to run: </label>" +
        "<input type='number' id='benchmarkTimeToRun' name='benchmarkTimeToRun'/><span> (seconds)</span>" +
        "<br/><label for='benchmarkDateRun'>Date run: </label>" +
        "<input type='date' id='benchmarkDateRun' name='benchmarkDateRun'/>" +
        "<br/><label for='benchmarkMachineConfigName'>Machine configuration name: </label>" +
        "<input type='text' id='benchmarkMachineConfigName' name='benchmarkMachineConfigName'/>" +
        "<br/><input id='submitBenchmarkButton' type='button' value='Submit' onclick='handleAddBenchmarkFormSubmit(this.parentElement, \"" + probInstanceUUID + "\", \"" + implName + "\", \"" + algoName + "\")'/>" +
        "</form><br/>";

}

function handleAddBenchmarkFormSubmit(formElement, probInstanceUUID, implName, algoName) {
    console.log("benchmark form submit");
    console.log(formElement.id)

    let benchmarkName = formElement[0].value;
    let timeToRun = formElement[1].value;
    let dateRun = formElement[2].value;
    let machineConfigName = formElement[3].value;

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

        benchmarkRepo.addBenchmark(benchmarkName, algoName, machineConfigName, implName, probInstanceUUID, dateRun, timeToRun, onSuccessCallback, onFailCallback);
        // document.getElementById(formElement.id).innerHTML = "";

    }
    else {
        alert("Please enter non-empty benchmark and machine configuration names.");
    }

}
