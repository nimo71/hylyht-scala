define(['knockout'], function(ko) {

    function App(routes) {
        var oldLocation;

        // TODO: Remove loop to set the url, get notification from LocationHasher instead
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

    return App;
});