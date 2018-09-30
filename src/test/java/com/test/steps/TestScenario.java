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

	private WebDriver driver;
	
	@Given("^User creates an instance of (.+) browser$")
	  public void givenUserCreatesAnInstanceOfBrowser(String browser) throws Throwable {
		  if(browser.equals("chrome"))
			  driver = new ChromeDriver();
	  }

	  @When("^User navigates to (.+) page$")
	  public void whenUserNavigatesToPage(String pageUrl) throws Throwable {
		  driver.navigate().to(pageUrl);
	  }

	  @Then("^I validate the Page Title to be (.+)$")
	  public void thenIValidateThePageTitleToBe(String pageTitle) throws Throwable {
		  String actualTitle = driver.getTitle();
		  System.out.println(actualTitle);
		  Assert.assertEquals(pageTitle, actualTitle);
	  }
	  
	  @Then("^I validate the Page Lable to be (.+)$")
	  public void thenIValidateThePageLableToBe(String pageLable) throws Throwable {
		  String actualLable = new OpenWeatherMap(driver).getLabel().getText();
		  System.out.println(actualLable);
		  Assert.assertEquals(pageLable, actualLable);
	  }
	  
	  @Then("^I validate the primary header menu options$")
	  public void thenIValidateThePrimaryHeaderMenuOptions() throws Throwable {
		  List<String> menuOption = new ArrayList<String>();
		  new OpenWeatherMap(driver).getPrimaryHeaderMenu().forEach(w->menuOption.add(w.getText()));
		  
	  }
}
