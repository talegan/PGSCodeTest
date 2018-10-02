package com.test.steps;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.test.pageobjects.OpenWeatherMap;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestScenario {

	private static WebDriver driver;

	@Given("^User creates an instance of (.+) browser$")
	public void givenUserCreatesAnInstanceOfBrowser(String browser) throws Throwable {
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\hp\\Downloads\\Selenium_Pack_7_July\\Selenium_Pack_7_July\\code\\SalesForce-App-Project\\chrome\\ChromeLatest\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
	}

	@When("^User navigates to (.+) page$")
	public void whenUserNavigatesToPage(String pageUrl) throws Throwable {
		driver.navigate().to(pageUrl);
	}

	@Then("^User validate the Page Title to be (.+)$")
	public void thenUserValidateThePageTitleToBe(String pageTitle) throws Throwable {
		String actualTitle = driver.getTitle();
		Assert.assertTrue(actualTitle.contains(pageTitle));
	}

	@Then("^User validate page lable to be (.+)$")
	public void thenUserValidateThePageLableToBe(String pageLable) throws Throwable {
		String actualLable = new OpenWeatherMap(driver).getLabel().getAttribute("alt");
		Assert.assertEquals(pageLable, actualLable);
	}

	@Then("^User validate the primary header menu options$")
	public void thenUserValidateThePrimaryHeaderMenuOptions() throws Throwable {
		List<String> menuOption = new ArrayList<String>();
		new OpenWeatherMap(driver).getPrimaryHeaderMenu().forEach(w -> menuOption.add(w.getText()));
		Assert.assertEquals(8, menuOption.size());

	}
	
	@When("^User enters city as (.+) in the search box$")
	public void whenUserenterscityasinthesearchbox(String city) throws Throwable {
		new OpenWeatherMap(driver).getSearchBox().sendKeys(city);
	}
	
	@When("^User clicks on search button$")
	public void thenUserclicksonsearchbutton() throws Throwable {
		new OpenWeatherMap(driver).getSearchButton().click();
	}
	
	@Then("^User validate the website suggests city is (.+)$")
	public void thenUservalidatethewebsitesuggestscityis(String searchResult) throws Throwable {
		String actualResult = new OpenWeatherMap(driver).getNotFoundMsg().getText();
		Assert.assertTrue(actualResult.contains(searchResult));
	}
	
	@Then("^User validate the website successfully returns weather details for the city$")
	public void thenUservalidatethewebsitesuccessfullyreturnsweatherdetailsforthecity() throws Throwable {
		Assert.assertTrue(new OpenWeatherMap(driver).getForecastList().isDisplayed());
	}
	
	@Then("^User closes the instance of browser$")
	public void thenUserclosestheinstanceofbrowser() throws Throwable {
		driver.quit();
		System.out.println("Browser closed");
	}
}
