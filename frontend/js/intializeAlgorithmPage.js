

function displayProblemInstances(item, isUserRegistered){
    let output = ""
    //Problem Instance
    output = '<li class="listItem">'+
        '<h2> +  Problem Instance: ' + item.Name +'</h2>'+
        '<h3 style=" background-color: red; margin-left: 10px;" class="button" onclick="handleProblemInstanceDelete(this)">Del</h3>'+
        '<h3 style="margin-left: 20px;" class="button"> Data set Location: <a href='+ item.datasetUR +'>' + item.datasetURL + '</a></h3>'+
        '</li>'+
        '<li style="list-style-type:none">'+
        '<ul style="list-style: none;">'
    for (var j = 0; j < item.BenchMark.length; j++) {
        output = output + displayBenchMarks(item.BenchMark[j],isUserRegistered)
    }
    output = output + '</ul></li>'


    return output
}

function displayBenchMarks(item, isUserRegistered){
    let output = ""
    // BenchMark

    output = '<li class="listItem">'+
        '<h2> +  Benchmark: ' + item.Name +'</h2>'+
        '<h3 style="background-color: green; margin-left: 10px;" class="button" onclick="handleAdd(this)">Add Machine Configuration</h3>'+
        '<h3 style=" background-color: red; margin-left: 10px;" class="button" onclick="handleBenchmarkDelete(this)">Del</h3>'+
        '<h3 style="margin-left: 20px;" class="button"> Time To Run: ' + item.timeToRun + ' Seconds </h3>'+
        '<h3 style="margin-left: 20px;" class="button"> Data run on: <a href=' + item.dataRun + '>' +item.dataRun + '</a></h3>'+
        '</li>'


    return output

}




