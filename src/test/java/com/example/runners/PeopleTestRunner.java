package com.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = {
                "com.example.stepdefinitions" }, tags = "@people or @engineer", plugin = {
                                "pretty",
                                "html:target/cucumber-reports/cucumber.html",
                                "json:target/cucumber-reports/cucumber.json",
                                "junit:target/cucumber-reports/cucumber.xml"
                }, monochrome = true)
public class PeopleTestRunner extends AbstractTestNGCucumberTests {
}
