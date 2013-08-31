define(['Responses'], function(Responses) {

    function Routes() {
        this.routes = {};
    }

    Routes.prototype.respond = function(path) {
        var route;
        for (route in this.routes) {
            var matches = new RegExp(route).exec(path);
            if (matches && matches.length > 0) {
                var renderer = new (this.routes[route])();
                return renderer.render.apply(renderer, matches.slice(1));
            }
        }
        return Responses.notFound;
    };

    Routes.prototype.register = function(path, response) {
        this.routes[path] = response;
        return this;
    };

    return Routes;
});
