function handleBenchmarkDelete(ele, benchmarkID) {
    console.log("delete benchmark " + benchmarkID);

    let onSuccessCallback = function(xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("deleted benchmark with ID: " + xhr["benchmarkID"]);
        updateAlgorithmPageHierarchy();
    }

    let onFailCallback = function(xhr) {
        console.log("XHR: " + JSON.stringify(xhr, null, 4));
        console.log("failed to delete benchmark.");
    }

    benchmarkRepo.removeBenchmark(benchmarkID,onSuccessCallback, onFailCallback);
}
