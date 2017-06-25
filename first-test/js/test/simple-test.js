/*
var webdriver = require('selenium-webdriver'),
    By = webdriver.By,
    until = webdriver.until;

var driver = new webdriver.Builder()
    .forBrowser('chrome')
    .build();

driver.get('http://www.google.com/ncr');
driver.findElement(By.name('q')).sendKeys('webdriver');
driver.findElement(By.name('btnG')).click();
driver.wait(until.titleIs('webdriver - Google Search'), 1000);
driver.quit();
*/

/*
var webdriver = require('selenium-webdriver');
var chromeCapabilities = webdriver.Capabilities.chrome();
var driver = new webdriver.Builder().withCapabilities(chromeCapabilities).build();
driver.manage().timeouts().implicitlyWait(2000);
*/

var webdriver = require('selenium-webdriver'),
    By = webdriver.By,
    until = webdriver.until;
var chromeCapabilities = webdriver.Capabilities.chrome();
var driver = new webdriver.Builder().withCapabilities(chromeCapabilities).build();
// driver.manage().timeouts().implicitlyWait(2000);
driver.manage().setTimeouts({implicit: 2000});
driver.get('http://www.google.com/ncr');
driver.findElement(By.name('q')).sendKeys('webdriver');
driver.findElement(By.name('btnG')).click();
driver.wait(until.titleIs('webdriver - Google Search'), 1000);
driver.quit();
