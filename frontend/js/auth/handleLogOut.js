function handleLogOut(){
    if(vm.admin){
        handleAdminLogOut();
    }
    console.log("logout")
}

function handleAdminLogOut(){
    console.log("admin logout")
}
