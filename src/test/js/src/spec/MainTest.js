define(['app/main'], function(main) {

    describe('Main', function() {

        it('should notify when the browser location changes', function() {

            var changedLocation = '';

            function locationChanged(newLocation) {
                changedLocation = newLocation;
            }
            main.locationListener = locationChanged;

            window.location.href = window.location.href + '#!changed';

            var interval;
            interval = setInterval(function(){
                clearInterval(interval);
                changedLocation.should.match(/.+#!changed$/);
            }, 1000);

        });

        it ('should display login form when user unauthenticated');
    });
});
