package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.SmartBearLoginPage;
import pages.SmartbearHomePage;
import pages.SmartbearOrderPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SmartbearSteps {
   WebDriver driver = Driver.getDriver();

   SmartBearLoginPage smartBearLoginPage = new SmartBearLoginPage();

   SmartbearHomePage smartbearHomePage = new SmartbearHomePage();

   SmartbearOrderPage smartbearOrderPage = new SmartbearOrderPage();

    List<Map<String, Object>> data;

    @Given("user navigates to smartbear application")
    public void user_navigates_to_smartbear_application() {
        driver.get(ConfigReader.getProperty("smartbearUrl"));
    }
    @When("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String username, String password) {
        smartBearLoginPage.usernameInput.sendKeys(username);
        smartBearLoginPage.passwordInput.sendKeys(password);
        smartBearLoginPage.loginBtn.click();
    }

    @Then("user is successfully logged in and the title is {string}")
    public void user_is_successfully_logged_in_and_the_title_is(String title) {
     Assert.assertEquals(title, driver.getTitle());
    }

    @Then("user is not logged in and gets error message {string}")
    public void user_is_not_logged_in_and_gets_error_message(String expectedErrorMsg) {
        Assert.assertEquals(expectedErrorMsg, smartBearLoginPage.InvalidLoginMsg.getText());
    }

    @Given("user clicks on Order Tab")
    public void user_clicks_on_order_tab() {
    smartbearHomePage.orderTab.click();
    }




    @When("user selects product {string} and quantity {int}")
    public void user_selects_product_and_quantity(String product, Integer quantity) {
        BrowserUtils.selectOptionByValue(smartbearOrderPage.productSelect, product);
       smartbearOrderPage.quantitySelect.clear();
        smartbearOrderPage.quantitySelect.sendKeys(quantity.toString());
        smartbearOrderPage.calculateBtn.click();
    }
    @Then("user validates the price is calculates correctly for quantity {int}")
    public void user_validates_the_price_is_calculates_correctly_for_quantity(Integer quantity) {
        int priceInt = Integer.parseInt(smartbearOrderPage.priceInput.getAttribute("value"));
        int discountInt = Integer.parseInt(smartbearOrderPage.discount.getAttribute("value"));
        int actualTotal = Integer.parseInt((smartbearOrderPage.total.getAttribute("value")));

        System.out.println(priceInt+", "+discountInt+", "+actualTotal);
        int expectedTotal;


        if(quantity<10){
           expectedTotal= priceInt*quantity;
        }else{
            expectedTotal = priceInt*quantity*(100-discountInt)/100;
        }
        Assert.assertEquals(expectedTotal, actualTotal);
    }

    @When("user places an order with data and validates with success message {string}")
    public void user_places_an_order_with_data_and_validates_with_success_message(String successMessage, DataTable dataTable) throws InterruptedException {
        data = dataTable.asMaps(String.class, Object.class);
        for (int i = 0; i < data.size(); i++) {
            BrowserUtils.selectOptionByValue(smartbearOrderPage.productSelect, data.get(i).get("PRODUCT").toString());
            smartbearOrderPage.quantitySelect.sendKeys(data.get(i).get("QUANTITY").toString());
            smartbearOrderPage.customerNameInput.sendKeys(data.get(i).get("CUSTOMER NAME").toString());
            smartbearOrderPage.streetInput.sendKeys(data.get(i).get("STREET").toString());
            smartbearOrderPage.cityInput.sendKeys(data.get(i).get("CITY").toString());
            smartbearOrderPage.stateInput.sendKeys(data.get(i).get("STATE").toString());
            smartbearOrderPage.zipInput.sendKeys(data.get(i).get("ZIP").toString());
            switch ((data.get(i).get("CARD").toString())){
                case "Amex":
                    smartbearOrderPage.amexBtn.click();
                    break;
                case "MasterCard":
                    smartbearOrderPage.masterCardBtn.click();
                    break;
                case "Visa":
                    smartbearOrderPage.visaBtn.click();
            }
            smartbearOrderPage.cardNumber.sendKeys(data.get(i).get("CARD NUM").toString());
            smartbearOrderPage.expDateInput.sendKeys((data.get(i).get("EXP DATE").toString()));
            smartbearOrderPage.processBtn.click();

        }
        Thread.sleep(4000);
        Assert.assertEquals(successMessage, smartbearOrderPage.successfullyAddedMsg.getText());
    }


    @Then("user validates that created order is in the list of all orders")
    public void user_validates_that_created_order_is_in_the_list_of_all_orders() {
       smartbearOrderPage.viewOrdersTab.click();
       Assert.assertEquals(data.get(2).get("CUSTOMER NAME"), smartbearHomePage.lastCustomerName.getText());
    }

}
