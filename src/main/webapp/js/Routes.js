define(['NotFound'], function(NotFound) {

    function Routes() {
        this.routes = {};
    }

    Routes.prototype.respond = function(path) {
        if (path in this.routes) {
            return new this.routes[path];
        }
        return new NotFound();
    };

    Routes.prototype.register = function(path, response) {
        this.routes[path] = response;
        return this;
    };

    return Routes;
});
