define(['jquery', 'knockout'], function($, ko) {

    function RegistrationPage() {

    }

    RegistrationPage.prototype.send = function() {
        $('#content').load('view/registrationForm.html', function() {
            this.viewModel = {
                email : ko.observable(),
                confirmEmail : ko.observable(),
                password : ko.observable(),
                confirmPassword : ko.observable(),
                register : this.register.bind(this)
            };
            ko.applyBindings(this.viewModel);
        }.bind(this));
    }

    RegistrationPage.prototype.register = function(form) {
        console.log("email: "+ this.viewModel.email());
        console.log("confirmEmail: "+ this.viewModel.confirmEmail());
        console.log("password: "+ this.viewModel.password());
        console.log("confirmPassword: "+ this.viewModel.confirmPassword());
//        return false;
    }

    return RegistrationPage;
});