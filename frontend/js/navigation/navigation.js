class Navigation {

    constructor(containerId) {
        this.containerId = containerId;
        this.pageMapping = {
            registeredUsersPage : "registeredUserLandingPage.html"
        }
    }

    goToRegisteredUsersPage() {
        this.loadPage(this.pageMapping.registeredUsersPage)
    }

    loadPage(url) {
        $(this.containerId).load(url)
    }
}
