class Navigation {

    constructor(containerId, cognitoRedirectUri) {
        this.containerId = containerId;
        this.cognitoRedirectUri = cognitoRedirectUri;
        this.pageMapping = {
            registeredUsersPage : "registeredUserLandingPage.html",
            cognitoAuth : "https://nesoalgorithm.auth.us-east-2.amazoncognito.com//login?response_type=token&client_id=62lcdgq2137nmak9t45kse25q9&redirect_uri={0}",
            algorithmPage : "algorithmLandingPage.html"
        }
    }

    goToRegisteredUsersPage() {
        this.loadPage(this.pageMapping.registeredUsersPage)
    }

    goToLogin() {
        this.redirect(this.pageMapping.cognitoAuth);
    }

     goToAlgorithmPage(algoName) {
            vm.selectedAlgo= algoName;
            this.loadPage(this.pageMapping.algorithmPage);
     }

    // Loads a page into the redirect
    loadPage(url) {
        $(this.containerId).load(url)
    }

    redirect(url) {
        url = url.replace('{0}', this.cognitoRedirectUri);
        window.location.href = url;
    }
}
