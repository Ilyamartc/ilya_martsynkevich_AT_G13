package com.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class ExampleSteps {

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        System.out.println("Navigating to home page");
    }

    @When("I click on the login button")
    public void iClickOnTheLoginButton() {
        System.out.println("Clicking login button");
    }

    @Then("I should see the login form")
    public void iShouldSeeTheLoginForm() {
        System.out.println("Verifying login form is visible");
    }
}
