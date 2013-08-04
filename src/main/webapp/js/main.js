define(function() {

    function Main() {
        var oldLocation = location.href;
        setInterval(function() {
            if(location.href != oldLocation) {
                if (this.locationListener) {
                    locationListener(location.href);
                }
                oldLocation = location.href
            }
        }.bind(this), 500);
    }

    return Main;
});
