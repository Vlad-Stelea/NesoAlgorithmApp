class ViewModel {
    constructor() {
        // js recommends property access instead of getters and setters so we will just modify the property directly
        // If we need more complex logic for getting and setting, we can add get/set methods in the form "get user(){}"
        // instead which we still access property directly in code
        this.user = new User();
        this.selectedAlgo = null;
        this.selectedUser = null;
    }
}
