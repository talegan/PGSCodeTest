package com.test.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.But;

public class Scenario1 {
  @Given("^I want to write a step with precondition$")
  public void given() throws Throwable {
	  System.out.println("Inside Step1");
  }

  @When("^you are in When annotation$")
  public void when() throws Throwable {
  }

  @Then("^you are in Then annotation$")
  public void then() throws Throwable {
  }

  @But("^you are in But annotation$")
  public void but() throws Throwable {
  }

}
