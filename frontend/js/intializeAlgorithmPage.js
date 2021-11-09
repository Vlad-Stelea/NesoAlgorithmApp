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
   data = '{"Algorithm": [{"Name": "Implementation 1", "Language": "C","codeURL": "skdjasldaksdjkasjdklasjdnjnjnbkjkjbkjbkjbnkjnbkjjnkjbjbnjnjkbjkbkjnkjbkjbnkbkjbkjbkjbnkjnkjbjkbkjbblkasjdlkasjdlkasjdlkjsadlkjaslkdjaslkdjlaskjdlkasjdlkasjdlkasjdlkasjdlkasjdlkasdjlkasjdlkasjdksjdlksajdksajdlkjaslkdjlskajdlkasjdklajsldkjaskdjalskdjlaksjdlkasjdlkasjdlkasjdlksajdklajsldk"},{"Name": "Implementation 1", "Language": "C","codeURL": "www.google.com"}]}';
    var js = JSON.parse(data);

    var output = '<ol style="list-style: none;">';
    for (var i = 0; i < js.Algorithm.length; i++) {
        output = output + displayImplementations(js.Algorithm[i])
    }
    output = output + '</ol>'

   var hierarchy = document.getElementById('Implementation');
   // Update hierarchy result
   hierarchy.innerHTML = output;
 }



 function displayImplementations(item){
     output = ""
    //Implementation
        //'<h3 style="margin-left: 20px;" class="button"> Code Url: <a href=' + item.codeURL +' target="_blank">'+item.codeURL + '</a></h3>'+
            output = '<li class="listItem">' +
                            '<button type="button" class="collapsible"> +  Implementation: ' + item.Name +'</button>'+
                            '<h3 style="background-color: green; margin-left: 10px;" class="button" onclick="handleAdd(this)">Add Benchmark</h3>'+
                              '<h3 style=" background-color: red; margin-left: 10px;" class="button" onclick="handleImplementationDelete(this)">Del</h3>'+
                              '<h3 style="margin-left: 20px;" class="button"> Language: ' + item.Language + '</h3>'+
                              '<h style="display:inline;word-wrap:break-word">code: ' +item.codeURL + '</h>' +
                             '<div class="content">'+
                            '<h3 style="; margin-left: 20px;" "> Language: '+item.Language + '</h3>'+
                            '<h3 style="display:inline; margin-left: 20px;" ">Url: <a href=' + item.codeURL +'>'+item.codeURL + '</a></h3>'+
                            '</div>'+
                      '</li>'+
                        '<li style="list-style-type:none">'+
                        '<ul style="list-style: none;">'
        if(item.ProblemInstances){
            for (var j = 0; j < item.ProblemInstances.length; j++) {
                output = output + displayProblemInstances(item.ProblemInstances[j])
            }
        }
        output = output + '</ul></li>'



    return output
 }

 function displayProblemInstances(item){
    output = ""
   //Problem Instance
                  output = '<li class="listItem">'+
                                  '<button type="button" class="collapsible"> +  Problem Instance: ' + item.Name +'</button>'+
                                  '<h3 style=" background-color: red; margin-left: 10px;" class="button" onclick="handleProblemInstanceDelete(this)">Del</h3>'+
                                  '<h3 style="margin-left: 20px;" class="button"> Data set Location: <a href='+ item.datasetUR +'>' + item.datasetURL + '</a></h3>'+
                              '</li>'+
                              '<li style="list-style-type:none">'+
                                  '<ul style="list-style: none;">'
                                  for (var j = 0; j < item.BenchMark.length; j++) {
                                              output = output + displayBenchMarks(item.BenchMark[j])
                                          }
                                  output = output + '</ul></li>'


        return output
 }

  function displayBenchMarks(item){
  output = ""
  // BenchMark

                   output = '<li class="listItem">'+
                                               '<button type="button" class="collapsible"> +  Benchmark: ' + item.Name +'</button>'+
                                                  '<h3 style="background-color: green; margin-left: 10px;" class="button" onclick="handleAdd(this)">Add Machine Configuration</h3>'+
                                                  '<h3 style=" background-color: red; margin-left: 10px;" class="button" onclick="handleBenchmarkDelete(this)">Del</h3>'+
                                             '<h3 style="margin-left: 20px;" class="button"> Time To Run: ' + item.timeToRun + ' Seconds </h3>'+
                                             '<h3 style="margin-left: 20px;" class="button"> Data run on: <a href=' + item.dataRun + '>' +item.dataRun + '</a></h3>'+
                                              '</li>'


    return output

  }




 function displayMachineConfiguration(item){
   output = ""
              // Machine Configuration
      output = '<li class="listItem">'+
                                     '<button type="button" class="collapsible"> +  Machince Configuration: ' + item.Name +'</button>'+
                                     '<h3 style="background-color: green; margin-left: 10px;" class="button" onclick="handleAdd(this)">Add</h3>'+
                                     '<h3 style=" background-color: red; margin-left: 10px;" class="button" onclick="handleMachineConfigDelete(this)">Del</h3>'+
                                 '</li>'+
                                 '<li style="list-style-type:none">'+
                                     '<ul style="list-style: none;">'
       return output
 }