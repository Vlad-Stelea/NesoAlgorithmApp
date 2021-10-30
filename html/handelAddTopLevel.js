function handelAddTopLevelPrep(){
    console.log("addTopPrep")
    var addTopClassForm = document.getElementById('AddTopForm');
   // Update Heirarchy result
   addTopClassForm.innerHTML = ' <br><label for="classificationName">Classification name:</label>' +
                            '<input type="text" id="fname" name="fname">'+
                            '<input type="submit" value="Submit" onclick="handelAddTopLevelSubmit(this)"><br><br>'

}


function handelAddTopLevelSubmit(ele){
    console.log("addTopSubmit")
    console.log(ele.parentElement.children[2].value)
    var addTopClassForm = document.getElementById('AddTopForm');
   // Update Heirarchy result
   addTopClassForm.innerHTML = ''
}