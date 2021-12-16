function updateUserList() {
    console.log("updatingUserList")
    let onSuccessCallback = function (data) {
        console.log("success")
        renderUsers(data);
    };

    let onFailCallback = function (data, status) {
        // TODO handle when there is an issue Not implemented rn as out of scope
    }
    userRepo.getUsers(onSuccessCallback, onFailCallback)
}

function renderUsers(data){
    let userList = data.users;

    console.log(userList);

    let output = '<ol style="list-style: none;">';

    for (let i = 0; i < userList.length; i++) {
        output = output + addUserListItem(userList[i])
    }

    output = output + '</ol>'
    let h = document.getElementById('UserList');
    h.innerHTML = output;
}


function addUserListItem(item){
    let output = '<li class="listItem" style="background-color: goldenrod">'+
        '<h3 style="display:inline;">+</h3>'+
        '<h3 style="margin-left: 20px;" class="button">' + item + '</h3>' +
        '<button style=" background-color: lightblue; margin-left: 10px;" class="button" onclick="navigation.goToAdminUserActivityPage(\'' + item + '\')">View Activity</button>' +
        '<button style=" background-color: red; margin-left: 10px;" class="button" onclick="handleUserDelete(this, \'' + item + '\')">Del</button>' +
        '</li>'

    return output
}