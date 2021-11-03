



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
   data = '{"Algorithm": [{"Name": "Implementation 1", "Language": "C","codeURL": "www.google.com" ,"ProblemInstances" : [{"Name": "Problem 1","datasetURL":"ww.test.data","BenchMark":[]}]},{"Name": "Implementation 1", "Language": "C","codeURL": "www.google.com" ,"ProblemInstances" : [{"Name": "Problem 1","datasetURL":"ww.test.data","BenchMark": [{"Name": "TheoTest","timeToRun": 1000, "dataRun": "10/20/20"},{"Name": "VladTest","timeToRun": 1000, "dataRun": "10/21/20"}]}]}]}';
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

            output = '<li class="listItem">' +
                            '<button type="button" class="collapsible"> +  Implementation: ' + item.Name +'</button>'+
                            '<h3 style="background-color: green; margin-left: 10px;" class="button" onclick="handleAdd(this)">Add</h3>'+
                              '<h3 style=" background-color: red; margin-left: 10px;" class="button" onclick="handleImplementationDelete(this)">Del</h3>'+
                              '<h3 style="margin-left: 20px;" class="button"> Language: ' + item.Language + '</h3>'+
                               '<h3 style="margin-left: 20px;" class="button"> CodeUrl: <a href=' + item.codeURL +'>'+item.codeURL + '</a></h3>'+
                             '<div class="content">'+
                            '<h3 style="; margin-left: 20px;" "> Language: '+item.Language + '</h3>'+
                            '<h3 style="display:inline; margin-left: 20px;" ">Url: <a href=' + item.codeURL +'>'+item.codeURL + '</a></h3>'+
                            '</div>'+
                      '</li>'+
                        '<li style="list-style-type:none">'+
                        '<ul style="list-style: none;">'

        for (var j = 0; j < item.ProblemInstances.length; j++) {
            output = output + displayProblemInstances(item.ProblemInstances[j])
        }

        output = output + '</ul></li>'



    return output
 }

 function displayProblemInstances(item){
    output = ""
   //Problem Instance
                  output = '<li class="listItem">'+
                                  '<button type="button" class="collapsible"> +  ProblemInstance: ' + item.Name +'</button>'+
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
                                                  '<h3 style="background-color: green; margin-left: 10px;" class="button" onclick="handleAdd(this)">Add</h3>'+
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
                                     '<button type="button" class="collapsible"> +  MachinceConfiguration: ' + item.Name +'</button>'+
                                     '<h3 style="background-color: green; margin-left: 10px;" class="button" onclick="handleAdd(this)">Add</h3>'+
                                     '<h3 style=" background-color: red; margin-left: 10px;" class="button" onclick="handleMachineConfigDelete(this)">Del</h3>'+
                                 '</li>'+
                                 '<li style="list-style-type:none">'+
                                     '<ul style="list-style: none;">'
       return output
 }