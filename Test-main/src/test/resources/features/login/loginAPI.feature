#mvn clean verify -Dtestsuite="LoginTestSuite" -Dcucumber.options="src/test/resources/features/addtocart" -Denvironments=product
@feature=Login-API
Feature: Login API

  @TC_01
  Scenario: Test API
    Given THUYEXAM login to web with role "buyer" with API
