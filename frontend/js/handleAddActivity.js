function addActivity(username, action){
    if(!vm.admin) {
        console.log("add Activity");

        let onSuccessCallback = function (xhr) {
            console.log("XHR: " + JSON.stringify(xhr, null, 4));
            console.log("added Activity: ", action);
        }

        let onFailCallback = function (xhr) {
            console.log("XHR: " + JSON.stringify(xhr, null, 4));
            console.log("failed to add Activity.");
        }

        activityRepo.addActivity(username, action, onSuccessCallback, onFailCallback)
    }
}
