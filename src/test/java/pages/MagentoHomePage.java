package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class MagentoHomePage {

    WebDriver driver;

    public MagentoHomePage(){
        driver= Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search")
    public WebElement searchBar;

    @FindBy(id = "ui-id-3")
    public WebElement whatsNewTab;

    @FindBy(id = "ui-id-4")
    public WebElement womenTab;

    @FindBy(id = "ui-id-5")
    public WebElement menTab;

    @FindBy(id = "ui-id-6")
    public WebElement gearTab;

    @FindBy(id = "ui-id-7")
    public WebElement trainingTab;

    @FindBy(id = "ui-id-8")
    public WebElement saleTab;


}
