function handleAddMachineConfigurationPrep() {
    console.log("prepping to add machine config");
    let addMachineConfigForm = document.getElementById("addMachineConfigForm");

    addMachineConfigForm.innerHTML = "<form id='createMachineConfigForm' method='post'>" +
        "<br/><label for='machineConfigName'>Machine config name: </label>" +
        "<input type='text' id='machineConfigName' name='machineConfigName'/>" +
        "<br/><label for='machineConfigL1Cache'>Machine config L1 Cache size: </label>" +
        "<input type='number' id='machineConfigL1Cache' name='machineConfigL1Cache'/><span> (bytes)</span>" +
        "<br/><label for='machineConfigL2Cache'>Machine config L2 Cache size: </label>" +
        "<input type='number' id='machineConfigL2Cache' name='machineConfigL2Cache'/><span> (bytes)</span>" +
        "<br/><label for='machineConfigChip'>Machine config chip: </label>" +
        "<input type='text' id='machineConfigChip' name='machineConfigChip'/>" +
        "<br/><label for='machineConfigThreads'># threads in machine config: </label>" +
        "<input type='number' id='machineConfigThreads' name='machineConfigThreads'/>" +
        "<br/><input id='submitMachineConfigButton' type='button' value='Submit' onclick='handleAddMachineConfigurationFormSubmit(this)'/>" +
        "</form><br/>";
}

function handleAddMachineConfigurationFormSubmit(ele) {
    console.log("handling machine config add submit");

    let machineConfigName = ele.parentElement[0].value;
    let l1Cache = ele.parentElement[1].value;
    let l2Cache = ele.parentElement[2].value;
    let chip = ele.parentElement[3].value;
    let threads = ele.parentElement[4].value;

    if(machineConfigName === "") {
        alert("Please enter a non-empty Machine Configuration name.");
    }
    else {
        let onSuccessCallback = function (xhr) {
            console.log("XHR response: " + JSON.stringify(xhr, null, 4));
            console.log("added machine configuration: " + xhr["machineConfigName"]);
            updateAlgorithmPageHierarchy();
        }

        let onFailCallback = function (xhr) {
            console.log("XHR response: " + JSON.stringify(xhr, null, 4));
            console.log("failed to add machine configuration.");
        }

        machineConfigRepo.addMachineConfiguration(machineConfigName, l1Cache, l2Cache, chip, threads, onSuccessCallback, onFailCallback);
        document.getElementById(ele.form.id).innerHTML = "";
    }

}