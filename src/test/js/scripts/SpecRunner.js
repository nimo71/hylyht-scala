require.config({
    baseUrl :'../../main/webapp/js',
    //urlArgs : "v="+(new Date()).getTime(),
    paths : {
        spec : '../../../test/js/src/spec',
        vendor: '../../../test/js/src/vendor'
    }
});

require(['require', 'vendor/chai', 'vendor/mocha'], function(require, chai){

    assert = chai.assert;
    should = chai.should();
    expect = chai.expect;

    process.stdout.write = function(msg) {
        console.log(msg);
    };

    //mocha.setup('bdd');
    mocha.setup({
        ui: 'bdd',
        reporter: function(runner) {
//            new mocha.reporters.Dot(runner);
            new mocha.reporters.Teamcity(runner);
//            new mocha.reporters.Spec(runner);
//            new mocha.reporters.XUnit(runner);
            new mocha.reporters.HTML(runner);
        },
        ignoreLeaks: true,
        timeout: 5000 // ms
    });

    require(['spec/testsuite'], function(){
        mocha.run();
    });
});
