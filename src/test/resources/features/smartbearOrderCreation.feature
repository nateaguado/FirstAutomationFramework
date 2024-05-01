@regression @smartbear @orderCreation
  Feature: Validating Order Creation

    Background:Order creation setup
      Given user navigates to smartbear application
      And user logs in with username "Tester" and password "test"
      And user clicks on Order Tab
    @discountCalculator
    Scenario Outline: Applying discount to the total amount
      When user selects product "<Product>" and quantity <Quantity>
      Then user validates the price is calculates correctly for quantity <Quantity>
      Examples:
        | Product     | Quantity |
        | MyMoney     | 9        |
        | FamilyAlbum | 10       |
        | ScreenSaver | 11       |

      @placeOrder
    Scenario: Placing an order and validating
      When user places an order with data and validates with success message "New order has been successfully added."
        | PRODUCT | QUANTITY | CUSTOMER NAME | STREET     | CITY     | STATE    | ZIP   | CARD | CARD NUM  | EXP DATE |
        |ScreenSaver | 5     | Alan Jones    | 123 Foo St | New York | NY       | 12345 | MasterCard | 789956789 | 06/34    |
        |MyMoney  | 10       | John Doe      | 123 lee St | Chicago  | IL       | 12345 | Visa | 123456789 | 06/34    |
        |FamilyAlbum | 10    | Jane Adams    | 123 Devon St | Boston | MA       | 12345 | Amex | 789056789 | 06/34    |
      Then user validates that created order is in the list of all orders

