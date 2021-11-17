// config file used for deployments
const API_GATEWAY_URL = "https://jdkme371z4.execute-api.us-east-2.amazonaws.com/preAlpha"
const LiveConfig = {
    LOGIN_URL_CALLBACK : "https://nesoalgorithmapp.s3.us-east-2.amazonaws.com/frontend/html/NavContainer.html",
    ALGORITHMS_REPO : new AlgorithmsRepo(API_GATEWAY_URL),
    CLASSIFICATIONS_REPO : new ClassificationRepo(API_GATEWAY_URL)
}
