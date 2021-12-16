class UserRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.getUserActivityUrl = this.apiGatewayUrl + '/' + "Admin/Activity";
        this.getUserUrl = this.apiGatewayUrl + '/' + "Admin/Users";
        this.removeUserUrl = this.apiGatewayUrl + '/Admin/RemoveUser/';
    }

    getUsers(onSuccess, onFail) {
        console.log("Getting user")
        let xhr = new XMLHttpRequest();
        xhr.responseType = "json"
        xhr.open("GET", this.getUserUrl, true);
        xhr.send();
        xhr.onloadend = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                let xhrJSON = xhr.response;
                if(xhrJSON["statusCode"] === 200) {
                    onSuccess(xhrJSON);
                } else {
                    onFail(xhrJSON);
                }

                // onSuccess(xhr.response);
            }

        }
    }

    getUserActivity(userName, onSuccess, onFail) {
        console.log("Getting userActions")
        let xhr = new XMLHttpRequest();
        xhr.responseType = "json"
        xhr.open("GET", this.getUserActivityUrl +"/" + userName, true);
        xhr.send();
        xhr.onloadend = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                console.log(xhr.response);
                let xhrJSON = xhr.response;
                if(xhrJSON["statusCode"] === 200) {
                    onSuccess(xhrJSON);
                } else {
                    onFail(xhrJSON);
                }
            }
        }
    }

    removeUser(userName, onSuccess, onFail) {
        console.log("Removing user")

        let body = {
            "username" : userName
        }

        let stringedBody = JSON.stringify(body);
        let xhr = new XMLHttpRequest();
        xhr.responseType = "json"
        xhr.open("POST", this.removeUserUrl + userName, true);
        xhr.send(stringedBody);
        xhr.onloadend = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                console.log(xhr.response);
                let xhrJSON = xhr.response;
                if(xhrJSON["statusCode"] === 200) {
                    onSuccess(xhrJSON);
                } else {
                    onFail(xhrJSON);
                }
            }
        }
    }


}

class MockUserRepo {
    constructor() {
        console.log("Creating mock algo repo")
    }

    getUsers(onSuccess, onFail) {
        console.log("mock getting users")

        let response = ["george", "Lisa", "Huegh", "Micky"];

        onSuccess(
            response,
            200,
            new MockXHR()
        );
    }

    getUserActivity(userName, onSuccess, onFail) {
        console.log("mock getting users")
        let response = {"username" : userName,
                        "activity": [
                            {
                                "activityLogUUID" : "1",
                                "username" : "george",
                                "action" : "delete Fake impl from Algorithm 1",
                                "date" : "1/1/1"
                            },
                            {
                                "activityLogUUID" : "2",
                                "username" : "george",
                                "action" : "add real imp to algo 2",
                                "date" : "1/3/1"
                            },
                            {
                                "activityLogUUID" : "3",
                                "username" : "george",
                                "action" : "merge class 1 and class 2",
                                "date" : "1/2/1"
                            }
            ]}

        onSuccess(
            response,
            200,
            new MockXHR()
        );
    }


}