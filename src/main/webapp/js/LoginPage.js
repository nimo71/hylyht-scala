define(['jquery'], function($) {

    function LoginPage() {
        $('#content').load('loginForm.html');
        //ko.applyBindings(new LoginForm())
    }

    LoginPage.prototype.send = function() {

    }

    return LoginPage;
});