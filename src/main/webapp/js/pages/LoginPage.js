define(['jquery', 'knockout'], function($, ko) {

    function LoginPage() {

    }

    LoginPage.prototype.render = function() {
        $('#content').load('view/loginForm.html', function() {
            this.viewModel = {
                email : ko.observable(),
                password : ko.observable(),
                login : this.login.bind(this)
            };
            ko.applyBindings(this.viewModel);
        }.bind(this));
    };

    LoginPage.prototype.login = function(form) {
        console.log("email: "+ this.viewModel.email());
        console.log("password: "+ this.viewModel.password());
        ko.cleanNode($(form).children('[name=email]')[0]);
        ko.cleanNode($(form).children('[name=password]')[0]);
    };

    return LoginPage;
});