define(['jquery', 'knockout'], function($, ko) {

    function LoginPage() {
        this.viewModel = {
            email : ko.observable(),
            password : ko.observable(),
            login : this.login.bind(this)
        }
    }

    LoginPage.prototype.send = function() {
        $('#content').load('loginForm.html', function() {
            ko.applyBindings(this.viewModel);
        }.bind(this));
    }

    LoginPage.prototype.login = function() {
        console.log("email: "+ this.viewModel.email());
        console.log("password: "+ this.viewModel.password());
    }

    return LoginPage;
});