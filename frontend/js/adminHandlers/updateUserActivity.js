function updateUserActivity() {
    console.log("updatingUserList")
    let onSuccessCallback = function (data) {
        console.log("success")
        renderActions(data);
    };

    let onFailCallback = function (data, status) {
        // TODO handle when there is an issue Not implemented rn as out of scope
    }
    userRepo.getUserActivity(vm.selectedUser, onSuccessCallback, onFailCallback)
}

function renderActions(data){
    let userActivity = data.userActionList;
    console.log("rendering user activity");
    console.log(userActivity);

    let output = '<ol style="list-style: none;">';

    for (let i = 0; i < userActivity.length; i++) {
        output = output + addUserActivityItem(userActivity[i])
    }

    output = output + '</ol>'
    let h = document.getElementById('UserActivity');
    h.innerHTML = output;
}


function addUserActivityItem(item){
    let d = new Date(item.date);
    let output = '<li class="listItem" style="background-color: goldenrod">'+
        '<h3 style="display:inline;">+</h3>'+
        '<h3 style="margin-left: 20px;" class="button">' + 'User: ' + item.username + '</h3>' +
        '<h3 style="margin-left: 60px;" >' + 'Action: ' + item.action + '</h3>' +
        '<h3 style="margin-left: 60px;" >' + 'Date: ' + + (d.getMonth()+1) + '/' + d.getDay() + '/' + d.getFullYear() + '</h3>' +
        '</li>'


    return output
}