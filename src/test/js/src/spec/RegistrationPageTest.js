const testUsername = "someusername";
const testPassword = "somepassword";

var created = false;

define('UserRegister', [], function () {

    function StubUserRegister() {
        this.create = function(username, password) {
            expect(username).to.equal(testUsername);
            expect(password).to.equal(testPassword);
            created = true;
        };
    }

    return StubUserRegister;
});

define(['pages/RegistrationPage'], function(RegistrationPage) {

    describe("RegistrationPage", function() {

        it("registers users in the registry", function() {
            var page = new RegistrationPage();
            page.populate(testUsername, testUsername, testPassword, testPassword);
            page.register();

            expect(created).to.be.true;
        })
    });
});