package com.test.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.utils.Locator;

public class OpenWeatherMap {

	private Locator locator;
	private WebDriver driver;

	// PAGE OBJECT CONSTRUCTOR
	public OpenWeatherMap(WebDriver driver) {
		locator = new Locator();
		this.driver = driver;
	}

	// PAGE OBJECT IDENTIFIERS
	public WebElement getLabel() {
		return this.driver.findElement(locator.getLocator());
	}

	// PAGE OBJECT IDENTIFIERS
	public List<WebElement> getPrimaryHeaderMenu() {
		return this.driver.findElements(locator.getLocator());
	}
}
