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
					"E:\\SELENIUM PRANAY\\SELENIUM WEBDRIVER\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
	}

	@When("^User navigates to (.+) page$")
	public void whenUserNavigatesToPage(String pageUrl) throws Throwable {
		System.out.println("Page Url is :" + pageUrl);
		driver.navigate().to(pageUrl);
	}

	@Then("^I validate the Page Title to be (.+)$")
	public void thenIValidateThePageTitleToBe(String pageTitle) throws Throwable {
		String actualTitle = driver.getTitle();
		System.out.println("Actual : "+actualTitle);
		Assert.assertTrue(actualTitle.contains(pageTitle));
	}

	@Then("^I validate page lable to be (.+)$")
	public void thenIValidateThePageLableToBe(String pageLable) throws Throwable {
		String actualLable = new OpenWeatherMap(driver).getLabel().getAttribute("alt");
		System.out.println(actualLable);
		Assert.assertEquals(pageLable, actualLable);
	}

	@Then("^I validate the primary header menu options$")
	public void thenIValidateThePrimaryHeaderMenuOptions() throws Throwable {
		List<String> menuOption = new ArrayList<String>();
		new OpenWeatherMap(driver).getPrimaryHeaderMenu().forEach(w -> menuOption.add(w.getText()));

	}
}
