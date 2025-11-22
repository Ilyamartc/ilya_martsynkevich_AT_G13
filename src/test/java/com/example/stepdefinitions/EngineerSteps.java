package com.example.stepdefinitions;

import com.example.people.AutomatedEngineer;
import com.example.people.ManualEngineer;
import com.example.people.Engineer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class EngineerSteps {

    private static final Logger logger = LogManager.getLogger(EngineerSteps.class);

    private String engineerName;
    private int engineerExperience;
    private Engineer currentEngineer;
    private AutomatedEngineer automatedEngineer;
    private ManualEngineer manualEngineer;
    private List<Engineer> engineerList = new ArrayList<>();

    @Given("the engineer management system is ready")
    public void theEngineerManagementSystemIsReady() {
        logger.info("Initializing engineer management system");
        engineerList.clear();
        logger.info("Engineer list cleared");
    }

    @Given("I have an engineer name {string}")
    public void iHaveAnEngineerName(String name) {
        logger.info("Setting engineer name: {}", name);
        this.engineerName = name;
    }

    @Given("I have experience of {int} years")
    public void iHaveExperienceOfYears(int experience) {
        logger.info("Setting engineer experience: {} years", experience);
        this.engineerExperience = experience;
    }

    @When("I create an automated engineer")
    public void iCreateAnAutomatedEngineer() {
        logger.info("Creating automated engineer: {} with {} years experience", engineerName, engineerExperience);
        currentEngineer = new AutomatedEngineer(engineerName, engineerExperience);
        automatedEngineer = (AutomatedEngineer) currentEngineer;
        logger.info("Automated engineer created successfully");
    }

    @When("I create a manual engineer")
    public void iCreateAManualEngineer() {
        logger.info("Creating manual engineer: {} with {} years experience", engineerName, engineerExperience);
        currentEngineer = new ManualEngineer(engineerName, engineerExperience);
        manualEngineer = (ManualEngineer) currentEngineer;
        logger.info("Manual engineer created successfully");
    }

    @Given("I create an automated engineer with name {string} and experience {int}")
    public void iCreateAnAutomatedEngineerWithNameAndExperience(String name, int experience) {
        logger.info("Creating automated engineer: {} with {} years experience", name, experience);
        automatedEngineer = new AutomatedEngineer(name, experience);
        currentEngineer = automatedEngineer;
        logger.info("Automated engineer created successfully");
    }

    @Given("I create a manual engineer with name {string} and experience {int}")
    public void iCreateAManualEngineerWithNameAndExperience(String name, int experience) {
        logger.info("Creating manual engineer: {} with {} years experience", name, experience);
        manualEngineer = new ManualEngineer(name, experience);
        if (currentEngineer == null) {
            currentEngineer = manualEngineer;
        }
        logger.info("Manual engineer created successfully");
    }

    @Then("the engineer type should be {string}")
    public void theEngineerTypeShouldBe(String expectedType) {
        logger.info("Verifying engineer type is: {}", expectedType);
        assertNotNull(currentEngineer, "Engineer should not be null");
        assertEquals(currentEngineer.getType(), expectedType);
        logger.info("Engineer type verification passed");
    }

    @Then("the engineer name should be {string}")
    public void theEngineerNameShouldBe(String expectedName) {
        logger.info("Verifying engineer name is: {}", expectedName);
        assertNotNull(currentEngineer, "Engineer should not be null");
        assertEquals(currentEngineer.getName(), expectedName);
        logger.info("Engineer name verification passed");
    }

    @Then("the engineer experience should be {int}")
    public void theEngineerExperienceShouldBe(int expectedExperience) {
        logger.info("Verifying engineer experience is: {} years", expectedExperience);
        assertNotNull(currentEngineer, "Engineer should not be null");
        assertEquals(currentEngineer.getExperience(), expectedExperience);
        logger.info("Engineer experience verification passed");
    }

    @Then("the engineer should not be null")
    public void theEngineerShouldNotBeNull() {
        logger.info("Verifying engineer is not null");
        assertNotNull(currentEngineer, "Engineer should not be null");
        logger.info("Engineer null check passed");
    }

    @Then("the automated engineer type should be {string}")
    public void theAutomatedEngineerTypeShouldBe(String expectedType) {
        logger.info("Verifying automated engineer type is: {}", expectedType);
        assertNotNull(automatedEngineer, "Automated engineer should not be null");
        assertEquals(automatedEngineer.getType(), expectedType);
        logger.info("Automated engineer type verification passed");
    }

    @Then("the manual engineer type should be {string}")
    public void theManualEngineerTypeShouldBe(String expectedType) {
        logger.info("Verifying manual engineer type is: {}", expectedType);
        assertNotNull(manualEngineer, "Manual engineer should not be null");
        assertEquals(manualEngineer.getType(), expectedType);
        logger.info("Manual engineer type verification passed");
    }

    @Then("the engineer types should be different")
    public void theEngineerTypesShouldBeDifferent() {
        logger.info("Verifying automated and manual engineer types are different");
        assertNotNull(automatedEngineer, "Automated engineer should not be null");
        assertNotNull(manualEngineer, "Manual engineer should not be null");
        assertNotEquals(automatedEngineer.getType(), manualEngineer.getType());
        logger.info("Engineer types verification passed");
    }

    @Given("I have the following engineers:")
    public void iHaveTheFollowingEngineers(io.cucumber.datatable.DataTable dataTable) {
        logger.info("Loading engineers from data table");
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        engineerList.clear();

        for (Map<String, String> row : rows) {
            String type = row.get("type");
            String name = row.get("name");
            int experience = Integer.parseInt(row.get("experience"));

            if ("Automated".equals(type)) {
                engineerList.add(new AutomatedEngineer(name, experience));
                logger.info("Added automated engineer: {} with {} years experience", name, experience);
            } else if ("Manual".equals(type)) {
                engineerList.add(new ManualEngineer(name, experience));
                logger.info("Added manual engineer: {} with {} years experience", name, experience);
            }
        }
        logger.info("Loaded {} engineers total", engineerList.size());
    }

    @Then("I should have {int} automated engineers")
    public void iShouldHaveAutomatedEngineers(int expectedCount) {
        logger.info("Verifying count of automated engineers is: {}", expectedCount);
        long count = engineerList.stream()
                .filter(e -> "Automated".equals(e.getType()))
                .count();
        assertEquals(count, expectedCount);
        logger.info("Automated engineers count verification passed");
    }

    @Then("I should have {int} manual engineers")
    public void iShouldHaveManualEngineers(int expectedCount) {
        logger.info("Verifying count of manual engineers is: {}", expectedCount);
        long count = engineerList.stream()
                .filter(e -> "Manual".equals(e.getType()))
                .count();
        assertEquals(count, expectedCount);
        logger.info("Manual engineers count verification passed");
    }

    @Then("all engineers should have valid types")
    public void allEngineersShouldHaveValidTypes() {
        logger.info("Verifying all engineers have valid types");
        for (Engineer engineer : engineerList) {
            String type = engineer.getType();
            assertTrue("Automated".equals(type) || "Manual".equals(type),
                    "Engineer type should be either Automated or Manual");
        }
        logger.info("All engineers have valid types");
    }
}
