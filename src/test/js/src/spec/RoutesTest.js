define(['Routes', 'NotFound'], function(Routes, NotFound) {

    function ResponseStub1() {}
    ResponseStub1.prototype.render = function() {
        return 'ResponseStub1';
    };

    function ResponseStub2() {}
    ResponseStub2.prototype.render = function(pathParam) {
        this.pathParam = pathParam;
        return this;
    };

    describe('Routes', function() {

        var routes = new Routes()
            .register('/login', ResponseStub1)
            .register('/regex/([a-z]+)/test', ResponseStub2);


        it('should respond NotFound for unknown route', function() {
            expect(routes.respond('unknown')).to.deep.equal(new NotFound());
        });

        it('should respond with a page for a matching route', function() {
            expect(routes.respond('/login')).to.equal('ResponseStub1');
        });

        it('should match a route supplied as a regex', function() {
            var expectedResponse = new ResponseStub2().render('something');
            expect(routes.respond('/regex/something/test')).to.deep.equal(expectedResponse);
        });

    });
});
