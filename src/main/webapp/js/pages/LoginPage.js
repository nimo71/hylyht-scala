define(['jquery', 'knockout'], function($, ko) {

    function LoginPage() {
        //todo: create knockout page to ko.cleanNode etc...
        ko.cleanNode(document.body);
    }

    LoginPage.prototype.render = function(email) {
        $('#content').load('view/loginForm.html', function() {
            this.viewModel = {
                email : ko.observable(email.slice(1)),
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