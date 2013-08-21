define(['jquery'], function($) {

    function UserRegister() {

    }

    UserRegister.prototype.create = function(username, password) {
        var request = $.ajax({
            url: "/user",
            type: "PUT",
            data: {
                username: username,
                password: password
            }
        });
        request.done(function(jsonResponse) {
            console.log('done, jsonResponse:'+ jsonResponse);
        });
        request.fail(function(jqXHR, textStatus) {
            console.log('fail, status:'+ textStatus);
        });
    }

    return UserRegister;

});