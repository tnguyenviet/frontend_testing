// Taken from http://toolsqa.com/selenium-webdriver/testng-listeners/

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.util.logging.Logger;


public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    private static final Logger LOGGER = Logger.getLogger(Listener.class.getName());
    private static double testsToExecute = 0;
    private static double executedTests = 0;

    // This belongs to ISuiteListener and will execute before the Suite start
    @Override
    public void onStart(ISuite arg0) {
        testsToExecute = arg0.getAllMethods().size() * BaseTest.browsersAndPlatformsProvider().length;
        String message = "STARTING TEST SUITE %s with %s tests";
        LOGGER.info(String.format(message, arg0.getName(), (int) testsToExecute));
    }

    // This belongs to ISuiteListener and will execute, once the Suite is finished
    @Override
    public void onFinish(ISuite arg0) {
        LOGGER.info("FINISHING TEST SUITE " + arg0.getName());
    }

    // This belongs to ITestListener and will execute before starting of Test set/batch
    public void onStart(ITestContext arg0) {
        /*
        int tests = arg0.getAllTestMethods().length;
        Reporter.log("BEGINNING TEST " + arg0.getName() + ", tests: " + tests, true);
        */
    }

    // This belongs to ITestListener and will execute, once the Test set/batch is finished
    public void onFinish(ITestContext arg0) {
        // Reporter.log("COMPLETING TEST " + arg0.getName(), true);
    }

    // This belongs to ITestListener and will execute only when the test is pass
    public void onTestSuccess(ITestResult arg0) {
        // This is calling the printTestResults method
        printTestResults(arg0);
    }

    // This belongs to ITestListener and will execute only on the event of fail test
    public void onTestFailure(ITestResult arg0) {
        // This is calling the printTestResults method
        printTestResults(arg0);
    }

    // This belongs to ITestListener and will execute before the main test start (@Test)
    public void onTestStart(ITestResult arg0) {
        /*
        String params = getParameters(arg0);
        String message = "%s Executing test %s with parameters %s";
        LOGGER.info(String.format(message, getThread(), returnMethodName(arg0.getMethod()), params));
        */
    }
    
    private String getThread() {
        return Thread.currentThread().getName() + " - " + Thread.currentThread().getId();
    }

    private String getParameters(ITestResult arg0) {
        String params = "";
        if (arg0.getParameters().length != 0) {
            for (Object parameter : arg0.getParameters()) {
                params = params.concat(parameter.toString()).concat(", ");
            }
        }
        return params;
    }

    // This belongs to ITestListener and will execute only if any of the main test(@Test) get skipped
    public void onTestSkipped(ITestResult arg0) {
        printTestResults(arg0);
    }

    // This is just a piece of shit, ignore this
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        LOGGER.info("onTestFailedButWithinSuccessPercentage");
    }

    // This is the method which will be executed in case of test pass or fail
    // This will provide the information on the test
    private synchronized void printTestResults(ITestResult result) {
        String status = null;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                status = "Pass";
                break;
            case ITestResult.FAILURE:
                status = "Failed";
                break;
            case ITestResult.SKIP:
                status = "Skipped";
        }
        executedTests++;
        double percentage = (executedTests / testsToExecute) * 100;
        String exceptionMessage = "";
        if (result.getThrowable() != null) {
            exceptionMessage = result.getThrowable().getMessage();
        }
        String message = String.format("%s Executed %s from %s (~%s%%), test %s, parameters: %s status: %s %s",
                getThread(), (int) executedTests, (int) testsToExecute, (int) percentage,
                returnMethodName(result.getMethod()), getParameters(result), status,
                exceptionMessage);
        LOGGER.info(message);
    }

    // This belongs to IInvokedMethodListener and will execute before every method including @Before @After @Test
    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
        /*
        String message = String.format("%s Executing method (before) %s", getThread(),
                returnMethodName(arg0.getTestMethod()));
        LOGGER.info(message);
        */
    }

    // This belongs to IInvokedMethodListener and will execute after every method including @Before @After @Test
    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
        /*
        String message = String.format("%s Executing method (after) %s", getThread(),
                returnMethodName(arg0.getTestMethod()));
        LOGGER.info(message);
        */
    }

    // This will return method names to the calling function
    private String returnMethodName(ITestNGMethod method) {
        return method.getRealClass().getSimpleName() + "." + method.getMethodName();
    }

}