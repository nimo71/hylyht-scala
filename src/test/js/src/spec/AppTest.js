define(['App'], function(App) {

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
            window.location.href = window.location.href + '#!/changed';
        });

    });
});