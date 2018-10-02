#Author: nikhilnew2008@yahoo.co.in

@tag
Feature: Creating test for Coading Chalange
  In order to create example tests

  @FirstTest
  Scenario: Verify important informations on page
    Given User creates an instance of Chrome browser
    When User navigates to https://openweathermap.org/ page
    Then User validate the Page Title to be urrent weather and forecast - OpenWeatherMap
		And User validate page lable to be openweathermap
		And User validate the primary header menu options
		And User closes the instance of browser

  @SecondTest
  Scenario: Verify weather detail not found for invalid city
    Given User creates an instance of Chrome browser
    When User navigates to https://openweathermap.org/ page
		And User enters city as Nikhil in the search box
		And User clicks on search button
	Then User validate the website suggests city is Not found
		And User closes the instance of browser

  @ThirdTest
  Scenario: Verify weather detail found for valid city
    Given User creates an instance of Chrome browser
    When User navigates to https://openweathermap.org/ page
		And User enters city as Mumbai in the search box
		And User clicks on search button
	Then User validate the website successfully returns weather details for the city
		And User closes the instance of browser