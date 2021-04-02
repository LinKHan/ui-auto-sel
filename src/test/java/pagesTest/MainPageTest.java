package pagesTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;
import testBase.TestBase;

public class MainPageTest extends TestBase {

    MainPage mainPage;

    @BeforeClass
    public void returnObj(){
        mainPage = new MainPage(driver);
    }

    @Test
    public void setDepartAndDestCity(){
        mainPage.setDepartCity("New York John F Kennedy International, NY (JFK)");
        mainPage.setDestCity("Dhaka Hazrat Shahjalal International, Bangladesh (DAC)");

    }




}
