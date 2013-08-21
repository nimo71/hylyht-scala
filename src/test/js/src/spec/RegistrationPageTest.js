define(['pages/RegistrationPage'], function(RegistrationPage) {
    const testUsername = "someusername";
    const testPassword = "somepassword";

    function StubUserRegister() {
        this.create = function(username, password) {
            this.username = username;
            this.password = password;
        };
    }

    describe("RegistrationPage", function() {

        it("registers users in the registry", function() {
            var userRegister = new StubUserRegister();
            var page = new RegistrationPage(userRegister);
            page.populate(testUsername, testUsername, testPassword, testPassword);
            page.register();

            expect(userRegister.username).to.equal(testUsername);
            expect(userRegister.password).to.equal(testPassword);
        })
    });
});