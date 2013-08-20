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

require(['Routes', 'App', 'LocationHasher', 'pages/LoginPage', 'pages/RegistrationPage', 'pages/ProfilePage'],
    function (Routes, App, LocationHasher, LoginPage, RegistrationPage, ProfilePage) {

        new App(
            new Routes()
                .register('/login', new LoginPage())
                .register('/registration', new RegistrationPage())
                .register('/profile', new ProfilePage())
        );

        window.location.href = new LocationHasher(window.location.href).setHashPath('/login')
    }
);
