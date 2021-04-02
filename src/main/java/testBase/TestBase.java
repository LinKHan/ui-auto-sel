package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.TestWebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestBase {


    public static WebDriver driver;
    protected EventFiringWebDriver eventFiringWebDriver;
    protected TestWebEventListener testWebEventListener;

    @BeforeClass
    public void setUpWebDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println("driver :: " + driver.hashCode());
        eventFiringWebDriver = new EventFiringWebDriver(driver);
        testWebEventListener = new TestWebEventListener();
        driver = eventFiringWebDriver.register(testWebEventListener);
        driver.get("https://matrix.itasoftware.com/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.out.println("driver :: " + driver.hashCode());

    }

//    @AfterClass
//    public void quitDriver(){
//        if (driver != null)
//            driver.quit();
//    }





}
