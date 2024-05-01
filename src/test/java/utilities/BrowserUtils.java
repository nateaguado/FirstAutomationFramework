package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;

public class BrowserUtils {
    public static String getRandomEmail(){
        UUID uuid = UUID.randomUUID();
        return "username"+uuid+"@gmail.com";
    }

    /**
     * This method waits for text to be present in the element
     * @param element
     * @param text
     */
    public static void waitForTextToBePresent(WebElement element, String text){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * This method creates a select object and selects an option by value
     * @param element
     * @param value
     */
    public static void selectOptionByValue(WebElement element, String value){
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public static void scrollByPixel(Integer pixels){
        JavascriptExecutor jse = ((JavascriptExecutor) Driver.getDriver());
        jse.executeScript("window.scrollBy(0,"+pixels.toString()+")");
    }
}
