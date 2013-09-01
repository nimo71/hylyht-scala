define(['SecureLoader', 'knockout'], function(SecureLoader, ko) {

    var loader = new SecureLoader();

    function ProfilePage() {
        ko.cleanNode(document.body);
    }

    ProfilePage.prototype.render = function() {
        loader.load('view/profile.html', function() {
            console.log('Profile Loaded...');
        }.bind(this));
    };

    return ProfilePage;
});