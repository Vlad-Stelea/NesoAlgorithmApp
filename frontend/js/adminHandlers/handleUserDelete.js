function handleUserDelete(ele, userName){
    console.log( userName)
    let onSuccessCallback = function (xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("removed User: ", xhr["username"]);
        updateUserList();
    }

    let onFailCallback = function (xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("failed to Remove User.");
    }

    userRepo.removeUser(userName, onSuccessCallback, onFailCallback);


}