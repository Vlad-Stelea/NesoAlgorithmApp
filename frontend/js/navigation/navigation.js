class Navigation {

    constructor(containerId, cognitoRedirectUri) {
        this.containerId = containerId;
        this.cognitoRedirectUri = cognitoRedirectUri;
        this.pageMapping = {
            registeredUsersPage : "registeredUserLandingPage.html",
            adminHierarchyPage : "AdminHierarchyPage.html",
            cognitoAuth : "https://nesoalgorithm.auth.us-east-2.amazoncognito.com//login?response_type=token&client_id=62lcdgq2137nmak9t45kse25q9&redirect_uri={0}",
            algorithmPage : "algorithmLandingPage.html",
            adminAlgorithmPage : "AdminAlgorithmLandingPage.html",
            adminLandingPage : "AdminLandingPage.html",
            adminUserActivityPage : "AdminUserActivityPage.html"
        }
    }

    goToRegisteredUsersPage() {
        vm.selectedAlgo = null;
        vm.selectedUser = null;
        this.loadPage(this.pageMapping.registeredUsersPage)
    }

    goToAdminHierarchyPage() {
        vm.selectedAlgo = null;
        vm.selectedUser = null;
        this.loadPage(this.pageMapping.adminHierarchyPage)
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

     goToAlgorithmPage(algoName, admin) {
        vm.selectedAlgo = algoName;
        if(admin){
            this.loadPage(this.pageMapping.adminAlgorithmPage);
        }else {
            this.loadPage(this.pageMapping.algorithmPage);
        }
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
