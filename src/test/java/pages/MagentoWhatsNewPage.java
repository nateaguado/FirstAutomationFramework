package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class MagentoWhatsNewPage {

    WebDriver driver;

    public MagentoWhatsNewPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//a[contains(text(), 'Jackets')])[1]")
    public WebElement womensJacketsLink;
}
