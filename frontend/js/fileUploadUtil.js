function getFileBase64Encoding(file, onReadCallback, ele) {
    console.log("in getFileBase64Encoding");

    let fileReader = new FileReader();
    fileReader.readAsDataURL(file);

    fileReader.onload = function() {
        onReadCallback(fileReader.result.split(",")[1], ele);
    }
}

function getFileBase64EncodingPromise(file) {
    let fileReader = new FileReader();
    let promise = new Promise((resolve, reject) => {

        fileReader.readAsDataURL(file);
        fileReader.onload = () => {
            resolve(fileReader.result.split(",")[1]);
        }
        fileReader.onerror = () => {
            reject();
        }
    });

    return promise;
}
