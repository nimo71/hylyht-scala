define(['jquery', 'App'], function($, App) {

    function UserRegister() {

    }

    UserRegister.prototype.create = function(username, password) {
        var request = $.ajax({
            url: "./user",
            type: "PUT",
            contentType: "application/json",
            dataType: "json",
            data: {
                username: username,
                password: password
            }
        });
        request.done(function(jsonResponse) {
            console.log('done, jsonResponse:'+ jsonResponse);
            App.go("/login")

        });
        request.fail(function(jqXHR, textStatus) {
            console.log('fail, status:'+ textStatus);
            App.go("/registration")
        });
    }

    return UserRegister;

});