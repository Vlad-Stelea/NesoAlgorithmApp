function handleAddTopLevelPrep(){
    console.log("addTopPrep")
    var addTopClassForm = document.getElementById('AddTopForm');
   // Update Hierarchy result
   addTopClassForm.innerHTML = ' <br><label for="classificationName">Classification name:</label>' +
                            '<input type="text" id="fname" name="fname">'+
                            '<input type="submit" value="Submit" onclick="handleAddTopLevelSubmit(this)"><br><br>'

}


function handleAddTopLevelSubmit(ele){
    console.log("addTopSubmit")
    console.log(ele.parentElement.children[2].value)
    var addTopClassForm = document.getElementById('AddTopForm');



   // Update Hierarchy result
   addTopClassForm.innerHTML = ''
}