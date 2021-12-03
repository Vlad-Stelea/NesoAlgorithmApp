function getFileBase64Encoding(currTarget, file) {
    console.log("in getFileBase64Encoding");

    let fileReader = new FileReader();
    fileReader.readAsDataURL(file);

    fileReader.onload = function() {
        document.getElementById(currTarget.formName).elements[currTarget.fileInputName].value = fileReader.result;
        document.getElementById(currTarget.formName).elements[currTarget.buttonID].disabled = false;
    }
}

function handleFileSelect(evt) {
    console.log("in handleFileSelect");

    let currTarget = evt.currentTarget;
    let files = currTarget.files;

    // accept files up to a certain size
    if(files[0].size > currTarget.maxFileSize) {
        document.getElementById(currTarget.formName).innerHTML = "";
        alert("File size too large! (" + files[0].size + " bytes, " + currTarget.maxFileSize + " max)");
    }
    else {
        getFileBase64Encoding(currTarget, files[0]);
    }
}