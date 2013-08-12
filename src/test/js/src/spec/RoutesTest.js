define(['Routes', 'NotFound'], function(Routes, NotFound) {

    function ResponseStub() {
        this.response = 'ResponseStub';
    }

    describe('Routes', function() {

        var routes = new Routes()
            .register('/login', ResponseStub);

        it('should respond NotFound for unknown route', function() {
            expect(routes.respond('unknown')).to.deep.equal(new NotFound());
        });

        it('should respond with a page for a matching route', function() {
            expect(routes.respond('/login')).to.deep.equal(new ResponseStub());
        });

    });
});
