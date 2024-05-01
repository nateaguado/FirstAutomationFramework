@regression @magentoSearch
Feature: Magento Search

  Background: Magento setup
    Given : user navigates to magento application

@magentoSearchValidation
  Scenario: Validating search contains keyword
    When : user searches for keyword "jacket"
    Then : user validates search results contain
    | jacket | summit kit | sweatshirt | jackshirt | pullover | shell |

    #Adrienne trek jacket ->
    #Mars HeatTech Pullover

  @magentoPriceRange
  Scenario: Validating Price range filter
    And : user searches for women's jackets
    When : user selects price range fifty to sixty dollars
    Then : user validates items prices are within 50.00 and 59.99 dollars