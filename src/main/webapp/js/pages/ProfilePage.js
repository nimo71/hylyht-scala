define(['SecureLoader'], function(SecureLoader) {

    var loader = new SecureLoader();

    function ProfilePage() {
    }

    ProfilePage.prototype.send = function() {
        loader.load('view/profile.html', function() {
            console.log('Profile Loaded...');
        }.bind(this));
    }

    return ProfilePage;
});