class Navigation {

    constructor(containerId, cognitoRedirectUri, admin) {
        this.containerId = containerId;
        this.cognitoRedirectUri = cognitoRedirectUri;
        this.pageMapping = {
            registeredUsersPage : "registeredUserLandingPage.html",
            cognitoAuth : "https://nesoalgorithm.auth.us-east-2.amazoncognito.com//login?response_type=token&client_id=62lcdgq2137nmak9t45kse25q9&redirect_uri={0}",
            cognitoAdminAuth : "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
            algorithmPage : "algorithmLandingPage.html",
            adminLandingPage : "AdminLandingPage.html",
            adminUserActivityPage : "AdminUserActivityPage.html"
        }
        this.admin = admin
    }

    goToRegisteredUsersPage() {
        vm.selectedAlgo = null;
        vm.selectedUser = null;
        this.loadPage(this.pageMapping.registeredUsersPage)
    }

    goToAdminUserActivityPage(user) {
        vm.selectedAlgo = null;
        vm.selectedUser = user;
        this.loadPage(this.pageMapping.adminUserActivityPage)
    }

    goToAdminLandingPage() {
        vm.selectedAlgo = null;
        vm.selectedUser = null;
        this.loadPage(this.pageMapping.adminLandingPage)
    }

    goToLogin() {
        this.redirect(this.pageMapping.cognitoAuth);
    }

    goToAdminLogin() {
        this.redirect(this.pageMapping.cognitoAdminAuth);
    }

     goToAlgorithmPage(algoName) {
        vm.selectedAlgo = algoName;
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
