define(['LocationHasher'], function(LocationHasher) {

    describe('LocationHasher', function() {

        it ('Should set the hash path', function() {
            expect(new LocationHasher('http://test.com/index.html').setHashPath('hash/path'))
                .to.equal('http://test.com/index.html#!/hash/path');
        });

        it ('Should not change location when setting the same hash path', function() {
            expect(new LocationHasher('http://test.com/index.html#!/hash/path').setHashPath('hash/path'))
                .to.equal('http://test.com/index.html#!/hash/path');
        });

        it ('Should not add multiple hash paths', function() {
            expect(new LocationHasher('http://test.com/index.html#!/hash/path').setHashPath('new/hash'))
                .to.equal('http://test.com/index.html#!/new/hash');
        });

        it ('Clears the location hash', function() {
            expect(new LocationHasher('http://test.com/index.html#!/hash/path').clearHashPath())
                .to.equal('http://test.com/index.html');
        });

        it ('Tests the presence of a hash path', function() {
            expect(new LocationHasher('http://test.com/index.html#!/hash/path').hasHashPath())
                .to.be.true;
        });

    });
});