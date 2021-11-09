function handleAddImpPrep(){
    console.log("addImpPrep")
    var addTopClassForm = document.getElementById('AddImpForm');
   // Update Hierarchy result
   addTopClassForm.innerHTML = ' <br><label for="ImplementationName">Implementation name:</label>' +
                            '<input type="text" id="fname" name="fname">'+
                            '<label for="Code">Code:</label>' +
                                                        '<input type="text" id="Code" name="Code">'+
                            '<label for="Language">Language:</label>' +
                                    '<input type="text" id="lang" name="lang">'+
                            '<input type="submit" value="Submit" onclick="handleAddTopLevelSubmit(this)"><br><br>'

}


function handleAddTopLevelSubmit(ele){
    console.log("addTopSubmit")
    console.log(ele.parentElement.children[2].value)
    var addTopClassForm = document.getElementById('AddTopForm');
   // Update Hierarchy result
   addTopClassForm.innerHTML = ''
}