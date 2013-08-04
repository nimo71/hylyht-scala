define(['app/Routes', 'app/NotFound'], function(Routes, NotFound) {

    describe('Routes', function() {

        it('should return NotFound for unknown route', function() {

            var routes = new Routes();
            routes.respond('#!unknown').should.equal(new NotFound());

        });
    });
});
