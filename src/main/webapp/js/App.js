define(['knockout', 'LocationHasher'], function(ko, LocationHasher) {

    function App(routes) {
        var lastLocation;

        setInterval(function() {
            if(location.href != lastLocation) {
                if (routes.respond) {
                    var hashPath = location.href.split('#!')[1];
                    routes.respond(hashPath);
                }
                lastLocation = location.href
            }
        }, 500);
    }

    App.go = function(hashPath) {
        var locationHasher = new LocationHasher(location.href);
        location.href = locationHasher.setHashPath(hashPath);
    }

    return App;
});