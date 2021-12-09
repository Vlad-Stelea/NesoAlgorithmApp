class ActivityRepo {
    constructor(apiGatewayUrl) {

    }

    addActivity(username, action, onSuccess, onFail) {

        let body = {
            "username": username,
            "action": action
        }

        let stringedBody = JSON.stringify(body);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", this.addActivityURL, true);

        xhr.send(stringedBody);

        xhr.onloadend = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if(xhr.status === 200) {
                    let response = xhr.response;
                    onSuccess(response);
                } else {
                    onFail(xhr.response, xhr.status);
                }
            }
        }

    }



}

class MockActivityRepo {
    constructor() {
        console.log("Creating mock algo repo")
    }

    addActivity(username, action, onSuccess, onFail) {

        let response = {
            "statusCode": "200",
            "activityLogUUID": "123",
            "username": "joe",
            "action": "d",
            "date": "1/2/3",
            "error": ""
        }

        console.log("here###############\n##########")
        console.log(action)
        console.log(username)

        onSuccess(response);

    }

}