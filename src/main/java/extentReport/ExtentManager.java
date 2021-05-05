package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utilAPI.DateFormatAPI;

public class ExtentManager {

    private static ExtentManager extentManager;
    private static ExtentReports extent;
    public static String projDir = System.getProperty("user.dir");
    private static DateFormatAPI dateFormatAPI;

    public static synchronized ExtentManager getInstance(){
        if (extentManager == null){
            extentManager = new ExtentManager();
        }
        return extentManager;
    }

    public static ExtentReports setUpExtentReport(){
        dateFormatAPI = new DateFormatAPI();
        String fileName = dateFormatAPI.getTimeDateMonthYear();
        String path = projDir + "/test-output/reports/" + fileName + ".html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setAutoCreateRelativePathMedia(true);
        htmlReporter.config().setDocumentTitle("Extent Report");
        htmlReporter.config().setReportName("Demo Site Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }

}
