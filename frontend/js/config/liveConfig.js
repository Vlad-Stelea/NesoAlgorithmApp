// config file used for deployments
const API_GATEWAY_URL = "https://jdkme371z4.execute-api.us-east-2.amazonaws.com/preAlpha"
const LiveConfig = {
    LOGIN_URL_CALLBACK : "https://nesoalgorithmapp.s3.us-east-2.amazonaws.com/frontend/html/NavContainer.html",
    ALGORITHMS_REPO : new AlgorithmsRepo(API_GATEWAY_URL),
    CLASSIFICATIONS_REPO : new ClassificationRepo(API_GATEWAY_URL),
    PROBLEMINSTANCES_REPO : new ProblemInstanceRepo(API_GATEWAY_URL),
    IMPLEMENTATIONS_REPO : new ImplementationRepo(API_GATEWAY_URL),
    BENCHMARKS_REPO : new BenchmarkRepo(API_GATEWAY_URL),
    MACHINECONFIGS_REPO : new MachineConfigurationRepo(API_GATEWAY_URL)
};
