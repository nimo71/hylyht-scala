define(['jquery'], function($) {

    function UserRegister() {

    }

    UserRegister.prototype.create = function(username, password, success, failed) {
        var request = $.ajax({
            url: "./user",
            type: "PUT",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({
                username: username,
                password: password
            })
        });
        request.done(success);
        request.fail(failed);
    }

    return UserRegister;

});