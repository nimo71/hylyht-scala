requirejs.config({
    baseUrl: 'js',
    paths: {
        jquery: 'jquery-1.9.1.min'
    },
    map: {
        '*': { 'jquery': 'jquery-private' },
        'jquery-private': { 'jquery': 'jquery' }
    }
});

define(function() {

    function Main(routes) {
        var oldLocation = location.href;

        setInterval(function() {
            if(location.href != oldLocation) {
                if (routes.respond) {
                    routes.respond(location.href).send();
                }
                oldLocation = location.href
            }
        }, 500);
    }

    return Main;
});
