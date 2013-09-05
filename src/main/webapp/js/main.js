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
                .register('/login(/[a-zA-Z0-9@\\.]*)?', LoginPage)
                .register('/registration', RegistrationPage(UserRegister))
                .register('/profile', ProfilePage)
        );

    }
);
