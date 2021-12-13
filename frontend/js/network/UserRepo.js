class UserRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.getUserActivityUrl = this.apiGatewayUrl + '/' + "Admin/Activity";
        this.getUserUrl = this.apiGatewayUrl + '/' + "Admin/Users";

    }

    getUsers(onSuccess, onFail) {
        console.log("Getting user")
        let xhr = new XMLHttpRequest();
        xhr.responseType = "json"
        xhr.open("GET", this.getUserUrl, true);
        xhr.send();
        xhr.onloadend = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    let response = xhr.response;
                    onSuccess(response);
                } else {
                    onFail(xhr.response, xhr.status);
                }
            }

        }
    }

    getUserActivity(userName, onSuccess, onFail) {
        console.log("Getting userActions")
        let xhr = new XMLHttpRequest();
        xhr.responseType = "json"
        xhr.open("GET", this.getUserActivityUrl, true);
        xhr.send();
        xhr.onloadend = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    let response = xhr.response;
                    onSuccess(response);
                } else {
                    onFail(xhr.response, xhr.status);
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

        let response = {"users" : [
                {"username" : "george"},
                {"username" : "Lisa"},
                {"username" : "Huegh"},
                {"username" : "Micky"}
                ]}

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