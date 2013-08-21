define(['jquery', 'App'], function($, App) {

    function SecureLoader() {

    }

    SecureLoader.prototype.load = function (path, success) {
        $('#content').load(path, function(response, status, xhr) {
            if (xhr.status === 401) {
                console.log('Unauthorized... Loading login form');
                // TODO: Add a flash message to display on the login form
                App.go('/login');
            }
            else if (typeof(success) !== 'undefined') success();
        }.bind(this));
    }

    return SecureLoader;
});
