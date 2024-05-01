package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class MagentoSearchResultsPage {

    WebDriver driver;

    public MagentoSearchResultsPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//select[@id='limiter'])[2]")
    public WebElement limiter;

    @FindBy(className = "product-item-link")
   public List<WebElement> items;
}
