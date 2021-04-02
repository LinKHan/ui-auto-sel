package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractBasePage {
    private static Logger log = LogManager.getLogger(MainPage.class);

    @FindBy(css = "#cityPair-orig-0")
    WebElement departCity;
    @FindBy(css = "#cityPair-dest-0")
    WebElement destCity;

    @FindBy(xpath = "//span[contains(text(),'New York John F Kennedy International, NY (JFK)')]")
    WebElement setDepartCity;
    @FindBy(xpath = "//span[contains(text(),'Dhaka Hazrat Shahjalal International, Bangladesh (DAC)" +
            "")
    WebElement setDestCity;

    public MainPage(final WebDriver driver) {
        super(driver);
    }


    public void setDepartCity(String departCity){
        log.info("inside Main class");
        this.departCity.click();
        this.departCity.sendKeys(departCity);
        try{
            this.setDepartCity.click();
        }catch (StaleElementReferenceException e){
            driver.findElement(By.xpath("//span[contains(text(),'New York John F Kennedy International, NY (JFK)')]")).click();
        }


    }
    public void setDestCity(String destCity){
        this.destCity.click();
        this.destCity.sendKeys(destCity);
        try{
            this.setDepartCity.click();
        }catch (StaleElementReferenceException e){
            driver.findElement(By.xpath("//span[contains(text(),'Dhaka Hazrat Shahjalal International, Bangladesh (DAC)")).click();
        }
    }




}
