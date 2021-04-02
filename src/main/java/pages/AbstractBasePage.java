package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractBasePage {

    protected WebDriver driver;

    public AbstractBasePage(final WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);



    }



}
