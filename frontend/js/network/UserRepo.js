class UserRepo {
    constructor(apiGatewayUrl) {

    }

    getUsers(onSuccess, onFail) {

    }

    getUserActivity(userName, onSuccess, onFail) {

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