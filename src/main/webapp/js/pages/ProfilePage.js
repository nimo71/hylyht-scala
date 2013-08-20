define(['jquery'], function($) {

    function ProfilePage() {
    }

    ProfilePage.prototype.send = function() {
        $('#content').load('view/profile.html', function() {
            console.log('Profile Loaded...');
        }.bind(this));
    }

    return ProfilePage;
});