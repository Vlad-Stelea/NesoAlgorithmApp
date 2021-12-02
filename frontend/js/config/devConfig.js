// Config file used for development
const DevConfig = {
    LOGIN_URL_CALLBACK : "http://localhost:63342/NesoAlgorithmApp/frontend/html/NavContainer.html",
    ALGORITHMS_REPO : new MockAlgorithmRepo(),
    CLASSIFICATIONS_REPO : new MockClassificationRepo(),
    PROBLEMINSTANCES_REPO : new MockProblemInstanceRepo(),
    IMPLEMENTATIONS_REPO : new MockImplementationRepo(),
    USERS_REPO : new MockUserRepo()
}
