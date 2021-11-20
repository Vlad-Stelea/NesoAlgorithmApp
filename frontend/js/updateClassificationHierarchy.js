function updateHierarchy() {
    console.log("updatingHierarchy")
    let onSuccessCallback = function (data) {
        console.log("success")
        renderHierarchy(data);
    };

    let onFailCallback = function (data, status) {
        // TODO handle when there is an issue Not implemented rn as out of scope
    }
    classificationRepo.getClassificationHierarchy(onSuccessCallback, onFailCallback)
}

function renderHierarchy(hierarchy) {
    console.log("hierarchy response: " + hierarchy);

    let output = '<ol style="list-style: none;">';

    for (let i = 0; i < hierarchy.topClassifications.length; i++) {
        output = output + addClassificationListItem(hierarchy.topClassifications[i])
    }

    output = output + '</ol>'
    let h = document.getElementById('Hierarchy');
    h.innerHTML = output;
}

function createAlgorithmView(algoName, isUserRegistered) {
    let output = '<li class="listItem" style="background-color: pink">'+
        '<h3 style="display:inline;">+</h3>' +
        '<h3 style="display:inline; margin-left: 20px;" onclick="handleAlgorithmView(this)">'+ algoName + '</h3>' +
        '<button style="background-color: LightSkyBlue; margin-left: 50px;" class="button" onclick="navigation.goToAlgorithmPage(\'' + algoName  + '\')">Algo Page</button>';

    if(isUserRegistered) {
        output += '<button style="background-color: red; margin-left: 10px;" class="button" onclick="handleAlgorithmDelete(this)">Del</button>' +
        '<button style="background-color: LightSeaGreen; margin-left: 10px;" class="button" onclick="handleReclassifyAlgorithm(this)">Reclassify</button>';
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
            '<button style="background-color: green; margin-left: 10px;" class="button" onclick="handleAddSubClassificationPrep(this)">Add Classification</button>' +
            '<button style="background-color: limegreen; margin-left: 10px;" class="button" onclick="handleAddAlgorithm(this)">Add Algorithm</button>'+
            '<button style=" background-color: red; margin-left: 10px;" class="button" onclick="handleClassificationDelete(this)">Del</button>'
    }
    output += '</li>' +
        '<li style="list-style-type:none">'+
        '<ul style="list-style: none;">'

    return output
}

function addClassificationListItem(item){
    let output = createClassificationView(item.className, vm.user.token !== '')

    for (let j = 0; j < item.algorithms.length; j++) {
        output = output + createAlgorithmView(item.algorithms[j].algoName,vm.user.token !== '');
    }

    for (let j = 0; j < item.subclassifications.length; j++) {
        output = output + addClassificationListItem(item.subclassifications[j]);
    }
     output = output + '</ul></li>';

    return output
}