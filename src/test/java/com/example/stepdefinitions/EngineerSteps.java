package com.example.stepdefinitions;

import com.example.people.AutomatedEngineer;
import com.example.people.ManualEngineer;
import com.example.people.Engineer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class EngineerSteps {
    
    private String engineerName;
    private int engineerExperience;
    private Engineer currentEngineer;
    private AutomatedEngineer automatedEngineer;
    private ManualEngineer manualEngineer;
    private List<Engineer> engineerList = new ArrayList<>();

    @Given("the engineer management system is ready")
    public void theEngineerManagementSystemIsReady() {
        // System initialization
        engineerList.clear();
    }

    @Given("I have an engineer name {string}")
    public void iHaveAnEngineerName(String name) {
        this.engineerName = name;
    }

    @Given("I have experience of {int} years")
    public void iHaveExperienceOfYears(int experience) {
        this.engineerExperience = experience;
    }

    @When("I create an automated engineer")
    public void iCreateAnAutomatedEngineer() {
        currentEngineer = new AutomatedEngineer(engineerName, engineerExperience);
        automatedEngineer = (AutomatedEngineer) currentEngineer;
    }

    @When("I create a manual engineer")
    public void iCreateAManualEngineer() {
        currentEngineer = new ManualEngineer(engineerName, engineerExperience);
        manualEngineer = (ManualEngineer) currentEngineer;
    }

    @Given("I create an automated engineer with name {string} and experience {int}")
    public void iCreateAnAutomatedEngineerWithNameAndExperience(String name, int experience) {
        automatedEngineer = new AutomatedEngineer(name, experience);
        currentEngineer = automatedEngineer;
    }

    @Given("I create a manual engineer with name {string} and experience {int}")
    public void iCreateAManualEngineerWithNameAndExperience(String name, int experience) {
        manualEngineer = new ManualEngineer(name, experience);
        if (currentEngineer == null) {
            currentEngineer = manualEngineer;
        }
    }

    @Then("the engineer type should be {string}")
    public void theEngineerTypeShouldBe(String expectedType) {
        assertNotNull(currentEngineer, "Engineer should not be null");
        assertEquals(currentEngineer.getType(), expectedType);
    }

    @Then("the engineer name should be {string}")
    public void theEngineerNameShouldBe(String expectedName) {
        assertNotNull(currentEngineer, "Engineer should not be null");
        assertEquals(currentEngineer.getName(), expectedName);
    }

    @Then("the engineer experience should be {int}")
    public void theEngineerExperienceShouldBe(int expectedExperience) {
        assertNotNull(currentEngineer, "Engineer should not be null");
        assertEquals(currentEngineer.getExperience(), expectedExperience);
    }

    @Then("the engineer should not be null")
    public void theEngineerShouldNotBeNull() {
        assertNotNull(currentEngineer, "Engineer should not be null");
    }

    @Then("the automated engineer type should be {string}")
    public void theAutomatedEngineerTypeShouldBe(String expectedType) {
        assertNotNull(automatedEngineer, "Automated engineer should not be null");
        assertEquals(automatedEngineer.getType(), expectedType);
    }

    @Then("the manual engineer type should be {string}")
    public void theManualEngineerTypeShouldBe(String expectedType) {
        assertNotNull(manualEngineer, "Manual engineer should not be null");
        assertEquals(manualEngineer.getType(), expectedType);
    }

    @Then("the engineer types should be different")
    public void theEngineerTypesShouldBeDifferent() {
        assertNotNull(automatedEngineer, "Automated engineer should not be null");
        assertNotNull(manualEngineer, "Manual engineer should not be null");
        assertNotEquals(automatedEngineer.getType(), manualEngineer.getType());
    }

    @Given("I have the following engineers:")
    public void iHaveTheFollowingEngineers(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        engineerList.clear();
        
        for (Map<String, String> row : rows) {
            String type = row.get("type");
            String name = row.get("name");
            int experience = Integer.parseInt(row.get("experience"));
            
            if ("Automated".equals(type)) {
                engineerList.add(new AutomatedEngineer(name, experience));
            } else if ("Manual".equals(type)) {
                engineerList.add(new ManualEngineer(name, experience));
            }
        }
    }

    @Then("I should have {int} automated engineers")
    public void iShouldHaveAutomatedEngineers(int expectedCount) {
        long count = engineerList.stream()
                .filter(e -> "Automated".equals(e.getType()))
                .count();
        assertEquals(count, expectedCount);
    }

    @Then("I should have {int} manual engineers")
    public void iShouldHaveManualEngineers(int expectedCount) {
        long count = engineerList.stream()
                .filter(e -> "Manual".equals(e.getType()))
                .count();
        assertEquals(count, expectedCount);
    }

    @Then("all engineers should have valid types")
    public void allEngineersShouldHaveValidTypes() {
        for (Engineer engineer : engineerList) {
            String type = engineer.getType();
            assertTrue("Automated".equals(type) || "Manual".equals(type),
                    "Engineer type should be either Automated or Manual");
        }
    }
}
