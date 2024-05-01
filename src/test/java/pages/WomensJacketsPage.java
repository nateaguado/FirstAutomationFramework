package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class WomensJacketsPage {

    WebDriver driver;

    public WomensJacketsPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(text(), 'Price')]")
    public WebElement priceFilterTab;

    @FindBy(xpath = "//span[contains(text(), '$50.00')]")
    public WebElement fiftySixtyRange;

    @FindBy(xpath = "//span[@class='price']")
    public List<WebElement> itemPrices;
}
