 /**
  * Respond to server JSON object.
  *
  * Replace the contents of 'Hierarchy' with a <br>-separated list of name,value pairs.
  */
 function processListResponse() {
    // TODO this structure can't be right can it?
    let js = {"Hierarchy": [{"Name": "Greed", "Children": [{"Name": "Recursive", "Children": [{"Algorithm": "Prims"}]}, {"Name": "Other", "Children": []}]}, {"Name": "Divide", "Children": [{"Algorithm": "Merge"}]}]}

    let output = '<ol style="list-style: none;">';
    for (let i = 0; i < js.Hierarchy.length; i++) {
        output = output + addListItem(js.Hierarchy[i])
    }
    output = output + '</ol>'
  
   let hierarchy = document.getElementById("Hierarchy");
   // Update hierarchy result
   hierarchy.innerHTML = output;
 }

function createAlgorithmView(algoName) {
     return '<li class="listItem">'+
         '<h3 style="display:inline;">+</h3>' +
         '<h3 style="display:inline; margin-left: 20px;" onclick="handleAlgorithmView(this)">'+ algoName + '</h3>'+
         '<button style="background-color: red; margin-left: 60px;" class="button" onclick="handleAlgorithmDelete(this)">Del</button>'+
         '</li>'
}

function createClassificationView(classificationName, isUserRegistered) {
     let output ='<li class="listItem">'+
         '<h3 style="display:inline;">+</h3>'+
         '<h3 style="margin-left: 20px;" class="button">' + classificationName + '</h3>'
         if(isUserRegistered) {
             output +=
                 '<button style="background-color: purple; margin-left: 50px;" class="button" onclick="handleClassificationMerge(this)">Merge</button>'+
                 '<button style="background-color: green; margin-left: 10px;" class="button" onclick="handleAdd(this)">Add</button>'+
                 '<button style=" background-color: red; margin-left: 10px;" class="button" onclick="handleClassificationDelete(this)">Del</button>'
         }
        output += '</li>' +
            '<li style="list-style-type:none">'+
            '<ul style="list-style: none;">'

    return output
}

 function addListItem(item){
     let output = ""
    if(item.Algorithm !== undefined){
        output = createAlgorithmView(item.Algorithm);
    }else{
        //classification
        output = createClassificationView(item.Name, vm.user.token !== '')

        for (let j = 0; j < item.Children.length; j++) {
            output = output + addListItem(item.Children[j])
        }
    
        output = output + '</ul></li>'

    }

    return output
 }
