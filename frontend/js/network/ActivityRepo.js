class ActivityRepo {
    constructor(apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.addActivityURL = this.apiGatewayUrl + "/User/LogAction";
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
                let xhrJSON = JSON.parse(xhr.response);
                if(xhrJSON["statusCode"] === 200) {

                    let response = xhr.response;
                    onSuccess(xhrJSON);
                } else {
                    onFail(xhrJSON);
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

        onSuccess(response);

    }

}