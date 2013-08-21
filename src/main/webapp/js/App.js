define(['knockout', 'LocationHasher'], function(ko, LocationHasher) {

    function App(routes) {
        var oldLocation;

        setInterval(function() {
            if(location.href != oldLocation) {
                if (routes.respond) {
                    var hashPath = location.href.split('#!')[1];
                    ko.cleanNode(document.body);
                    routes.respond(hashPath).send();
                }
                oldLocation = location.href
            }
        }, 500);
    }

    App.go = function(hashPath) {
        var locationHasher = new LocationHasher(location.href);
        location.href = locationHasher.setHashPath(hashPath);
    }

    return App;
});