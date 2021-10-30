/**
 * Refresh Heirarchy
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
  * Replace the contents of 'Heirarchy' with a <br>-separated list of name,value pairs.
  */
 function processListResponse() {
     /*
   console.log("res:" + result);
   // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
   var js = JSON.parse(result);
    */
   data = '{"Heirarchy": [{"Name": "Greed", "Children": [{"Name": "Recursive", "Children": [{"Algorithm": "Prims"}]}, {"Name": "Other", "Children": []}]}, {"Name": "Divide", "Children": [{"Algorithm": "Merge"}]}]}'
    var js = JSON.parse(data);

    var output = '<ol style="list-style: none;">';
    for (var i = 0; i < js.Heirarchy.length; i++) {
        output = output + addListItem(js.Heirarchy[i])
    }
    output = output + '</ol>'
  
   var heirarchy = document.getElementById('Heirarchy');
   // Update Heirarchy result
   heirarchy.innerHTML = output;
 }



 function addListItem(item){
     output = ""
    if(item.Algorithm != undefined){
        //algorythem
        output = '<li class="listItem">'+
                        '<h3 style="display:inline;">+</h3>' + 
                        '<h3 style="display:inline; margin-left: 20px;" onclick="handelAlgorithmView(this)">'+item.Algorithm + '</h3>'+
                        '<h3 style="background-color: red; margin-left: 60px;" class="button" onclick="handelAlgorithmDelete(this)">Del</h3>'+
                  '</li>'
    }else{
        //classification
        output = '<li class="listItem">'+
                        '<h3 style="display:inline;">+</h3>'+
                        '<h3 style="margin-left: 20px;" class="button">' + item.Name + '</h3>'+
                        '<h3 style="background-color: purple; margin-left: 50px;" class="button" onclick="handelClassificationMerge(this)">Merge</h3>'+
                        '<h3 style="background-color: green; margin-left: 10px;" class="button" onclick="handelAdd(this)">Add</h3>'+
                        '<h3 style=" background-color: red; margin-left: 10px;" class="button" onclick="handelClassificationDelete(this)">Del</h3>'+
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


