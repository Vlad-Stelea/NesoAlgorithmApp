function getFileBase64Encoding(file, onReadCallback, ele) {
    console.log("in getFileBase64Encoding");

    let fileReader = new FileReader();
    fileReader.readAsDataURL(file);

    fileReader.onload = function() {
        onReadCallback(fileReader.result.split(",")[1], ele);
    }
}
