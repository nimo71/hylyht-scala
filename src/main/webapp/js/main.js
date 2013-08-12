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

require(['Routes', 'App', 'LoginPage'], function (Routes, App, LoginPage) {
    var routes = new Routes();
    routes.register('/login', LoginPage);

    new App(routes);

    window.location.href = window.location.href + '#!/login'
});
