/**
 * Refresh hierarchy
 *
 *    GET tree_url
 *    RESPONSE  tree of name constants 
 */
 function refreshConstantsList() {
     /*
    var xhr = new XMLHttpRequest();
    xhr.open("GET", list_url, true);
    xhr.send();
    
    console.log("sent");
 
   // This will process results and update HTML as appropriate. 
   xhr.onloadend = function () {
     if (xhr.readyState == XMLHttpRequest.DONE) {
       console.log ("XHR:" + xhr.responseText);
       processListResponse(xhr.responseText);
     } else {
       processListResponse("N/A");
     }
   };
   */
 }
 
 /**
  * Respond to server JSON object.
  *
  * Replace the contents of 'Hierarchy' with a <br>-separated list of name,value pairs.
  */
 function processListResponse() {
     /*
   console.log("res:" + result);
   // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
   var js = JSON.parse(result);
    */
   data = '{"Hierarchy": [{"Name": "Greed", "Children": [{"Name": "Recursive", "Children": [{"Algorithm": "Prims"}]}, {"Name": "Other", "Children": []}]}, {"Name": "Divide", "Children": [{"Algorithm": "Merge"}]}]}'
    var js = JSON.parse(data);

    var output = '<ol style="list-style: none;">';
    for (var i = 0; i < js.Hierarchy.length; i++) {
        output = output + addListItem(js.Hierarchy[i])
    }
    output = output + '</ol>'
  
   var hierarchy = document.getElementById('Hierarchy');
   // Update hierarchy result
   hierarchy.innerHTML = output;
 }



 function addListItem(item){
     output = ""
    if(item.Algorithm != undefined){
        //algorythem
        output = '<li class="listItem">'+
                        '<h3 style="display:inline;">+</h3>' + 
                        '<h3 style="display:inline; margin-left: 20px;" onclick="handleAlgorithmView(this)">'+item.Algorithm + '</h3>'+
                        '<h3 style="background-color: red; margin-left: 60px;" class="button" onclick="handleAlgorithmDelete(this)">Del</h3>'+
                  '</li>'
    }else{
        //classification
        output = '<li class="listItem">'+
                        '<h3 style="display:inline;">+</h3>'+
                        '<h3 style="margin-left: 20px;" class="button">' + item.Name + '</h3>'+
                        '<h3 style="background-color: purple; margin-left: 50px;" class="button" onclick="handleClassificationMerge(this)">Merge</h3>'+
                        '<h3 style="background-color: green; margin-left: 10px;" class="button" onclick="handleAdd(this)">Add</h3>'+
                        '<h3 style=" background-color: red; margin-left: 10px;" class="button" onclick="handleClassificationDelete(this)">Del</h3>'+
                    '</li>'+
                    '<li style="list-style-type:none">'+
                        '<ul style="list-style: none;">'
        
        for (var j = 0; j < item.Children.length; j++) {
            output = output + addListItem(item.Children[j])
        }
    
        output = output + '</ul></li>'

    }

    return output
 }


