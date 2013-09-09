define(['jquery', 'knockout', 'App', 'UserRegister'], function($, ko, App, UserRegister) {

    function RegistrationPage() {
        ko.cleanNode(document.body);
    }

    RegistrationPage.prototype.populate = function(email, confirmEmail, password, confirmPassword) {
        this.viewModel.email(email);
        this.viewModel.confirmEmail(confirmEmail);
        this.viewModel.password(password);
        this.viewModel.confirmPassword(confirmPassword);
    };
    RegistrationPage.prototype.register = function(form) {
        // TODO: Process a new registration
        // Validate form
        // Create a new user with valid fields
        // On success redirect to login form with username filled in
        // On failure redisplay form with error message

        new UserRegister().create(
                this.viewModel.email(),
                this.viewModel.password(),
                this.registrationSuccess,
                this.registrationFailed );
    };

    RegistrationPage.prototype.registrationSuccess = function(jsonResponse) {
        console.log('Registration successful, jsonResponse:'+ jsonResponse);
        App.go("/login/"+ jsonResponse.username);
    }


    RegistrationPage.prototype.render = function() {
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
    };

    RegistrationPage.prototype.registrationFailed = function(jqXHR, textStatus) {
        console.log('Registration failed, status: '+ textStatus);
        App.go("/registration")
    }

    return RegistrationPage;
});