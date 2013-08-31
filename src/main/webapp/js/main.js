requirejs.config({
    baseUrl: 'js',
    paths: {
        jquery: 'vendor/jquery-1.9.1.min',
        knockout: 'vendor/knockout-2.3.0'
    },
    map: {
        '*': { 'jquery': 'jquery-private' },
        'jquery-private': { 'jquery': 'jquery' }
    }
});

require(['Routes', 'App', 'LocationHasher', 'pages/LoginPage', 'pages/RegistrationPage', 'UserRegister', 'pages/ProfilePage'],
    function (Routes, App, LocationHasher, LoginPage, RegistrationPage, UserRegister, ProfilePage) {

        new App(
            new Routes()
                //todo: update login to accept a username on the path to populate the username field??
                .register('/login', LoginPage)
                .register('/registration', RegistrationPage)
                .register('/profile', ProfilePage)
        );

    }
);
