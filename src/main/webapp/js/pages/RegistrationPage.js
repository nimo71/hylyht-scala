define(['jquery', 'knockout'], function($, ko) {

    function RegistrationPage(userRegister) {
        this.userRegister = userRegister;

        this.viewModel = {
            email : ko.observable(),
            confirmEmail : ko.observable(),
            password : ko.observable(),
            confirmPassword : ko.observable(),
            register : this.register.bind(this)
        };
    }

    RegistrationPage.prototype.populate = function(email, confirmEmail, password, confirmPassword) {
        this.viewModel.email(email);
        this.viewModel.confirmEmail(confirmEmail);
        this.viewModel.password(password);
        this.viewModel.confirmPassword(confirmPassword);
    };

    RegistrationPage.prototype.render = function() {
        $('#content').load('view/registrationForm.html', function() {
            ko.applyBindings(this.viewModel);
        }.bind(this));
    };

    RegistrationPage.prototype.register = function(form) {
        console.log("email: "+ this.viewModel.email());
        console.log("confirmEmail: "+ this.viewModel.confirmEmail());
        console.log("password: "+ this.viewModel.password());
        console.log("confirmPassword: "+ this.viewModel.confirmPassword());

        // TODO: Process a new registration
        // Validate form
        // Create a new user with valid fields
        // On success redirect to login form with username filled in
        // On failure redisplay form with error message

        this.userRegister.create(this.viewModel.email(), this.viewModel.password());
    };

    return RegistrationPage;
});