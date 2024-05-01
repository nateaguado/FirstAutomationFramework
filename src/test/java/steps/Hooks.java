package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.Beta;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

public class Hooks {

    WebDriver driver;

    @Before
    public void setup(){
        driver= Driver.getDriver();
        System.out.println("Before Scenario");
    }
    @After
    public void teardown(){
        driver.quit();
        System.out.println("After Scenario");
    }

}
