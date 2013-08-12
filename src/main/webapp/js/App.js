define([], function() {

    function App(routes) {
        var oldLocation = location.href;

        setInterval(function() {
            if(location.href != oldLocation) {
                if (routes.respond) {
                    var hash = location.href.split('#!')[1];
                    routes.respond(hash).send();
                }
                oldLocation = location.href
            }
        }, 500);
    }

    return App;
});