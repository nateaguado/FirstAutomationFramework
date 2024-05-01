package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.MagentoHomePage;
import pages.MagentoSearchResultsPage;
import pages.MagentoWhatsNewPage;
import pages.WomensJacketsPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;

public class MagentoSteps {
    MagentoSearchResultsPage magentoSearchResultsPage = new MagentoSearchResultsPage();
    MagentoHomePage magentoHomePage = new MagentoHomePage();

    MagentoWhatsNewPage magentoWhatsNewPage = new MagentoWhatsNewPage();

    WomensJacketsPage womensJacketsPage = new WomensJacketsPage();
    WebDriver driver = Driver.getDriver();

    @Given(": user navigates to magento application")
    public void user_navigates_to_magento_application() {
        driver.get(ConfigReader.getProperty("magentoUrl"));
    }


    @When(": user searches for keyword {string}")
    public void user_searches_for_keyword(String keyword) {
        magentoHomePage.searchBar.sendKeys(keyword+ Keys.ENTER);
        BrowserUtils.scrollByPixel(2500);
        BrowserUtils.selectOptionByValue(magentoSearchResultsPage.limiter, "24");
    }

    @Then(": user validates search results contain")
    public void user_validates_search_results_contain(DataTable dataTable) {
        MagentoSearchResultsPage msrp = new MagentoSearchResultsPage();
        List<String> keywords = dataTable.asList();
        for (WebElement item : magentoSearchResultsPage.items) {
            String itemDescription = item.getText().toLowerCase();
            boolean isFound = false;
            for (int i = 0; i < keywords.size(); i++) {
                if (itemDescription.toLowerCase().contains(keywords.get(i).toLowerCase())) {
                    isFound = true;
                    break;
                }
            }
            Assert.assertTrue("["+itemDescription+"] does not contain keywords", isFound);
        }
    }

    @Given(": user searches for women's jackets")
    public void user_searches_for_women_s_jackets() {
        magentoHomePage.whatsNewTab.click();
        magentoWhatsNewPage.womensJacketsLink.click();
    }

    @When(": user selects price range fifty to sixty dollars")
    public void user_selects_price_range_fifty_to_sixty_dollars() {
        womensJacketsPage.priceFilterTab.click();
        womensJacketsPage.fiftySixtyRange.click();
    }

    @Then(": user validates items prices are within {double} and {double} dollars")
    public void user_validates_items_prices_are_within_and_dollars(Double priceMin, Double priceMax) {
        WomensJacketsPage wjp = new WomensJacketsPage();
        for(WebElement itemPrice: wjp.itemPrices){
            double priceDouble = Double.parseDouble(itemPrice.getText().replace("$", ""));
            Assert.assertTrue(priceMin<=priceDouble && priceDouble<=priceMax );
        }
    }
    @When("user clicks on {string}")
    public void user_clicks_on(String tab) {
        switch (tab){
            case "What's New":
                magentoHomePage.whatsNewTab.click();
                break;

            case "Women":
                magentoHomePage.womenTab.click();
                break;
            case "Men":
                magentoHomePage.menTab.click();
                break;
            case "Gear":
                magentoHomePage.gearTab.click();
                break;
            case "Training":
                magentoHomePage.trainingTab.click();
                break;
            case "Sale":
                magentoHomePage.saleTab.click();
                break;
        }
    }

    @Then("user validates the {string} title")
    public void user_validates_the_title(String title) {
      Assert.assertEquals("Title is incorrect", title, driver.getTitle());
    }

}