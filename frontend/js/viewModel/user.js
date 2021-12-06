class User {
    constructor() {
        this.token = null;
    }

    get username() {
        // Make sure token is set already
        if(this.token === null) return null;
        // parse the token out
        return this.parseUsername(this.token);
    }

    parseUsername(token) {
        let encodedPayload = token.split('.')[1];
        return JSON.parse(atob(encodedPayload))["cognito:username"];
    }

}
