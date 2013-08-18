define([], function() {

    function LocationHasher(location) {
        this.location = location;
    }

    LocationHasher.prototype.setHashPath = function(hashPath) {
        var locationParts = this.location.split('#!/');
        if (locationParts[1] === hashPath) return this.location;
        return locationParts[0] +'#!/'+ hashPath;
    }

    LocationHasher.prototype.clearHashPath = function () {
        return this.location.split('#!/')[0];
    };

    LocationHasher.prototype.hasHashPath = function () {
        return this.location.split('#!/').length > 1;
    };

    return LocationHasher;
});