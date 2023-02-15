#mvn clean verify -Dtestsuite="LoginTestSuite" -Dcucumber.options="src/test/resources/features/addtocart" -Denvironments=product
@feature=addToCart
Feature: Add To Cart

  @TC_01
  Scenario: Test
    Given THUYEXAM open web
    When THUYEXAM login to web with role buyer
    And Search product by name "Product exam1" and add to cart with amount = "1"
    Then Verify info product in popup cart of "Product exam1"
      | brand       | name          | variant    | casePrice | quantity | total  |
      | exam brand1 | Product exam1 | Sku1 exam1 | 10.00     | 1        | $10.00 |
      | exam brand1 | Product exam1 | Sku1 exam1 | 10.00     | 1        | $10.00 |
      | exam brand1 | Product exam1 | Sku1 exam1 | 10.00     | 1        | $10.00 |
      | exam brand1 | Product exam1 | Sku1 exam1 | 10.00     | 1        | $10.00 |
    Then Verify report price in popup cart
      | orderValue | subTotal | smallOrderSurcharge | logisticsSurcharge | total |
      | 10.00      | 10.00    | 30.00               | 60.00              | 60.00 |


  @TC_02
  Scenario: Test
    Given THUYEXAM open web
    When THUYEXAM login to web with role buyer
    And Search product by name "Product exam1" and add to cart with amount = "1"
    Then Verify info product in popup cart of "Product exam1"
      | brand       | name          | variant    | casePrice | quantity | total  |
      | exam brand1 | Product exam1 | Sku1 exam1 | 10.00     | 1        | $10.00 |
    Then Verify report price in popup cart
      | orderValue | subTotal | smallOrderSurcharge | logisticsSurcharge | total |
      | 10.00      | 10.00    | 30.00               | 60.00              | 60.00 |