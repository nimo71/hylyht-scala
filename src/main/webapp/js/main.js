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
