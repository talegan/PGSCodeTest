#Author: nikhilnew2008@yahoo.co.in

@tag
Feature: Creating test for Coading Chalange
  In order to create example tests

  @tag1
  Scenario: Verify important informations on page
    Given User creates an instance of Chrome browser
    When User navigates to https://openweathermap.org/ page
    Then I validate the Page Title to be urrent weather and forecast - OpenWeatherMap
    And I validate page lable to be openweathermap
    And I validate the primary header menu options

