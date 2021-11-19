function handleAddProblemInstancePrep() {
    console.log("prepping to add problem instance");
    let addProbInstanceForm = document.getElementById("AddProblemInstanceForm");

    // TODO add upload functionality for dataset when we get that figured out
    addProbInstanceForm.innerHTML = "<br><label for='problemInstanceName'>Problem instance name: </label>" +
                                    "<input type='text' id='problemInstanceName' name='problemInstanceName'>" +
                                    "<input type='submit' value='Submit' onclick='handleAddProblemInstanceSubmit(this)'><br/><br/>";
}

function handleAddProblemInstanceSubmit(ele) {
    console.log("submitting problem instance add");
    console.log(ele);

    let algoHeader = document.getElementById("AlgoNameDisplay");
    let algoName = algoHeader.textContent;
    let problemInstanceName = ele.parentElement.children[2].value;
    console.log("adding " + problemInstanceName + " under " + algoName);
}
