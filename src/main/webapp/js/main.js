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

require(['Routes', 'App', 'LoginPage', 'LocationHasher'], function (Routes, App, LoginPage, LocationHasher) {
    var routes = new Routes();
    routes.register('/login', LoginPage);

    new App(routes);

    window.location.href = new LocationHasher(window.location.href).setHashPath('login')
});
