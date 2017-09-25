// conf.js
exports.config = {
    framework: 'jasmine2',
    seleniumAddress: 'http://localhost:4444/wd/hub',
    capabilities: {
        'browserName' : 'firefox',
        'seleniumAddress' : 'http://localhost:4444/wd/hub',
        'unexpectedAlertBehaviour' : 'ignore',
        'marionette': 'true',
        'disable-popup-blocking' : true
    },
    specs: ['*test.js'],

    onPrepare : function() {
        let SpecReporter = require('jasmine-spec-reporter').SpecReporter;
        jasmine.getEnv().addReporter(new SpecReporter({displayStacktrace: 'all'}));
        protractor.basePath = __dirname;
        protractor.count=0;
        protractor.count1=0;
        browser.manage().timeouts().pageLoadTimeout(400000);
        browser.manage().timeouts().implicitlyWait(25000);
        // For non-angular page
        browser.ignoreSynchronization = true;

        browser.getCapabilities().then(function(capabilities) {
            console.log("PREPARE CAP" + capabilities);
            console.log("BROWSERTYPE" + capabilities.get('browserName'));
            browser.browserName = capabilities.get('browserName');
        });

        protractor.ActionSequence.prototype.sleep = function(delay) {
            var driver = this.driver_;
            this.schedule_("sleep", function() {
                driver.sleep(delay);
            });
            return this;
        };


        protractor.ActionSequence.prototype.perform = function() {
            var actions = this.actions_.slice();
            var driver = this.driver_;
            return driver.controlFlow().execute(function() {
                actions.forEach(function(action) {
                    var command = action.command;
                    if (typeof command === "function")
                        driver.flow_.execute(command);
                    else
                        driver.schedule(command, action.description);
                });
            }, 'ActionSequence.perform');
        };

        protractor.ActionSequence.prototype.clickAndHold = function(elm) {
            return this.mouseDown(elm).sleep(3000).mouseUp(elm);
        };

    },


    jasmineNodeOpts: {
        // Default time to wait in ms before a test fails.
        defaultTimeoutInterval: 90000 // 90 seconds
    }
};