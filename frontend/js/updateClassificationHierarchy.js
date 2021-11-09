function updateHierarchy() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", getHierarchy_url, true);
    xhr.send();
    console.log("sent get hierarchy request");

    xhr.onloadend = function() {
        if(xhr.readyState === XMLHttpRequest.DONE) {
            processHierarchyResponse(xhr.responseText);
        }
        else {
            processHierarchyResponse("N/A");
        }
    };
}

function processHierarchyResponse(response) {
    // TODO pack the hierarchy up, then pass it along to display
    console.log("hierarchy response: " + response);
}
