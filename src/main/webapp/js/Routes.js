define(['Responses'], function(Responses) {

    function Routes() {
        this.routes = {};
    }

    Routes.prototype.respond = function(path) {
        if (path in this.routes) {
            return this.routes[path];
        }
        return Responses.notFound;
    };

    Routes.prototype.register = function(path, response) {
        this.routes[path] = response;
        return this;
    };

    return Routes;
});
