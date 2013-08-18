define(['App', 'LocationHasher'], function(App, LocationHasher) {

    var locationHasher = new LocationHasher(window.location.href);
    if (locationHasher.hasHashPath())
        window.location.href = locationHasher.clearHashPath();

    describe('App', function() {

        function ResponseStub(path, done) {
            this.send = function() {
                try {
                    expect(path).to.match(/^\/changed$/);
                    done();
                }
                catch (e) {
                    done(e);
                }
            }
        }

        function RoutesStub(done) {
            this.respond = function(path) {
                return new ResponseStub(path, done);
            }
        }

        it ('should send a response from routes on window location change', function(done) {
            new App(new RoutesStub(done));
            window.location.href = new LocationHasher(window.location.href).setHashPath('/changed');
        });

    });
});
