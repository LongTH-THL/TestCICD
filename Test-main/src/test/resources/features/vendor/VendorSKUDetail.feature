#mvn clean verify -Dtestsuite="AddToCartTestSuite" -Dcucumber.options="src/test/resources/features/addtocart" -Denvironments=product
@feature=vendorSKUDetail
Feature: Vendor SKU Detail

  @TC_01
  Scenario: Test
    Given ADMIN open web
    When ADMIN login to web with role Admin
    And Navigate to "Stores" to "All stores" by sidebar
    And Search the vendor by info then system show result
      | fullName      | email                | vendorCompany | brand | region | addressCity | addressState | tags | approved | shopify |
      | Auto_vender 1 | autovender@email.com |               |       |        |             |              |      |          |         |
      | Vendor 2601   |                      |               |       |        |             |              |      |          |         |

