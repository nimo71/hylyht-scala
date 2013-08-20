define([], function() {

    function LocationHasher(location) {
        this.location = location;
    }

    LocationHasher.prototype.setHashPath = function(hashPath) {
        var locationParts = this.location.split('#!');
        if (locationParts[1] === hashPath) return this.location;
        return locationParts[0] +'#!'+ hashPath;
    }

    LocationHasher.prototype.clearHashPath = function () {
        return this.location.split('#!')[0];
    };

    LocationHasher.prototype.hasHashPath = function () {
        return this.location.split('#!').length > 1;
    };

    LocationHasher.go = function(hashPath) {
        var locationHasher = new LocationHasher(window.location.href);
        window.location.href = locationHasher.setHashPath(hashPath);
        return locationHasher;
    }

    return LocationHasher;
});