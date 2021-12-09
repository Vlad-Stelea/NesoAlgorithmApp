function addActivity(username, action){
    if(!vm.admin) {
        console.log("add Activity");

        let onSuccessCallback = function (data) {
            console.log("XHR: " + data.responseText);
            console.log("added Activity: ", action);
        }

        let onFailCallback = function (data, status) {
            console.log("Status != 200. Actual create response: " + data.responseText);
            let newJS = JSON.parse(xhr.responseText);
            let err = newJS["error"];
            alert(err);
        }

        activityRepo.addActivity(username, action, onSuccessCallback, onFailCallback)
    }
}
