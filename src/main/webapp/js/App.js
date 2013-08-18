define([], function() {

    function App(routes) {
        var oldLocation = location.href;

        setInterval(function() {
            if(location.href != oldLocation) {
                if (routes.respond) {
                    var hashPath = location.href.split('#!')[1];
                    routes.respond(hashPath).send();
                }
                oldLocation = location.href
            }
        }, 500);
    }

    return App;
});