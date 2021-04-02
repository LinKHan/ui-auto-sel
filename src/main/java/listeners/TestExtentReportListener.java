package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import extentReport.ExtentManager;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.TestBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class TestExtentReportListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance().setUpExtentReport();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static Logger log = LogManager.getLogger(TestExtentReportListener.class);


    public void onTestStart(ITestResult result) {
        log.info(result.getMethod().getMethodName() + "starting");
        ExtentTest  test = extent.createTest(result.getTestClass().getName() + " :: " + result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @SneakyThrows
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b> Test Method " + result.getMethod().getMethodName() + " Successful</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS, m);
    }

    @SneakyThrows
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String exceptionMsg = Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details><summary><b><font color =red>" +
                "Exception Occurred, click to see details:" + "</font></b></summary>" +
                exceptionMsg.replaceAll(",", "<br>") + "</details> \n");
        WebDriver driver = TestBase.driver;
        String path = takeScreenshot(driver, methodName);
        try {
            log.info("attaching screenshot to report");
            extentTest.get().fail("<b><font color=red>" + "Screenshot of failure " + "</font></b>",
                    //extentTest.get().addScreenCaptureFromPath(path)
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            log.info("screenshot attached to report");
        } catch (IOException e) {
            extentTest.get().fail("Test Failed cannot attach screenshot");
        }
        String logText ="<b>Test Method " + methodName + " Failed</b>";
        Markup m = MarkupHelper.createLabel(logText,ExtentColor.RED);
        extentTest.get().log(Status.FAIL, m); //extentTest.get().addScreenCaptureFromPath(path) +"test failed"
        extent.flush();

    }

    public void onTestSkipped(ITestResult result) {
        String logText = "<b> Test Method" + result.getMethod().getMethodName() + " Skipped</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP, m);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onTestFailedWithTimeout(ITestResult result) {

    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {
        if(extent != null){
            extent.flush();
        }
    }

    public static String takeScreenshot(WebDriver driver, String methodName){
        String fileName = getScreenshotName(methodName);
        String path = System.getProperty("user.dir")+"/test-output/screenshots/" + fileName;
        File screenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


    public static String getScreenshotName(String methodName){
        String pattern = "MMM:dd:yyyy hh:mm:ss";
        Date d = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formattedDate = simpleDateFormat.format(d);
        String fileName = methodName + "_" + formattedDate.replace(":","_").replace(" ", "_") + ".jpg";
        return fileName;
    }



}
