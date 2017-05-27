// Getting the Chai expect library for assertions
var expect = require('chai').expect;

// Selenium Grid url
var seleniumGridUrl = 'http://localhost:4444/wd/hub';

// Create a new instance of WebDriver
var webDriver = require('selenium-webdriver');

describe('First WebDriver Test in JavaScript and Mocha', function() {
    // Global timeout for Mocha to wait for the callback function to be invoked
    this.timeout(60000);

    // Test to check the page title
    it('Page title should be Schuhe & Mode online kaufen | ZALANDO Online Shop', function(done) {

        var capabilities = new webDriver.Capabilities().
        set(webDriver.Capability.BROWSER_NAME, webDriver.Browser.FIREFOX).
        //set(webDriver.Capability.BROWSER_NAME, webDriver.Browser.CHROME).
        set(webDriver.Capability.PLATFORM, 'LINUX');

        // Use WebDriver to visit a search engine with Chrome
        var driver = new webDriver.Builder()
            .withCapabilities(capabilities)
            .usingServer(seleniumGridUrl)
            .build();

        // Maximize the window
        driver.manage().window().maximize();

        // Go to PHPTravels website
        driver.get("https://www.zalando.de/");

        // Assert the title to the expected value
        driver.getTitle().then(function(title) {
            expect(title).to.equal('Schuhe & Mode online kaufen | ZALANDO Online Shop');
        });

        // Quitting the browser and invoking the callback function to tell Mocha that we are done
        driver.quit().then(done);
    });

});

