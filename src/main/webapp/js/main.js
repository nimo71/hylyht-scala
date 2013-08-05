define(function() {

    function Main(routes) {
        var oldLocation = location.href;

        setInterval(function() {
            if(location.href != oldLocation) {
                if (routes.respond) {
                    routes.respond(location.href);
                }
                oldLocation = location.href
            }
        }.bind(this), 500);
    }

    return Main;
});
