(function () {
    "use strict";
    var system = require("system");
    var url = system.args[1];

    phantom.viewportSize = {width: 800, height: 600};

    console.log("Opening " + url);

    var page = new WebPage();

    // This is required because PhantomJS sandboxes the website and it does not
    // show up the console messages from that page by default
    page.onConsoleMessage = function (msg) {

        var formatted = msg;
        var replacementCount = msg.split(/%/).length - 1;

        if (replacementCount > 0) {
            var newParts = [];
            var parts = msg.split(' ');

            var i;
            var j=0;
            for (i=0; i < parts.length - replacementCount; i++) {
                if (parts[i].split(/%/).length === 1) {
                    newParts.push(parts[i]);
                }
                else {
                    var replacee = parts[i];
                    var replacement = parts[parts.length - replacementCount + j++];
                    newParts.push(replacee.replace(/%[ds]/, replacement));
                }
            }
            formatted = newParts.join(' ');
        }
        console.log(formatted);

        // Exit as soon as the last test finishes.
        if (formatted && formatted.indexOf("complete") !== -1) {
            phantom.exit();
        }
    };

    page.open(url, function (status) {

        if (status !== 'success') {
            console.log('Unable to load the address!');
            phantom.exit(-1);
        }
        else {
            // Timeout - kill PhantomJS if still not done after 2 minutes.
            window.setTimeout(function () {
                phantom.exit();
            }, 120 * 1000);
        }
    });
}());
