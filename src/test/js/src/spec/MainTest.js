define(['main', 'Routes'], function(Main) {

    describe('Main', function() {

        it ('should request a response from routes on window location change', function(done) {
            var responsePath = '';
            function RoutesStub() {
                this.respond = function(path) {
                    responsePath = path;
                }
            }
            new Main(new RoutesStub());

            window.location.href = window.location.href + '#!changed';

            var interval = setInterval(function(){
                try {
                    clearInterval(interval);
                    expect(responsePath).to.match(/.+#!changed$/);
                    done();
                }
                catch (e) {
                    done(e);
                }
            }, 600);
        });
    });
});
