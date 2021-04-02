package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentManager extentManager;
    private static ExtentReports extent;
    public static String projDir = System.getProperty("user.dir");

    public static synchronized ExtentManager getInstance(){
        if (extentManager == null){
            extentManager = new ExtentManager();
        }
        return extentManager;
    }

    public static ExtentReports setUpExtentReport(){
        String fileName = getReportName();
        String path = projDir + "/test-output/reports/" + fileName;
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

    public static String getReportName(){
        String pattern = "MMM:dd:yyyy hh:mm:ss";
        Date d = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formattedDate = simpleDateFormat.format(d);
        String fileName = "auto" + "_" + formattedDate.replace(":","_").replace(" ", "_") + ".html";
        return fileName;
    }


}
