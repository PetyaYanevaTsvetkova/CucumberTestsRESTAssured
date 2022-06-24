package stepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

public class HealthSteps {
    private String healthUrl;
    private JsonPath jsonPath;

    @Given("get URL: {}")
    public void getURLHealth(String url) {
        healthUrl = url;
    }

    @Given("take Response")
    public Response takeResponseHealth() {
        RequestSpecification requestSpecification = RestAssured.given();
        return requestSpecification.get(healthUrl);
    }

    @When("get JsonPath")
    public void getJsonPath() {
        jsonPath = takeResponseHealth().jsonPath();
    }

    @Then("Assert alive is true")
    public void assertAliveIsTrue() {
        Assertions.assertTrue(jsonPath.getBoolean("alive"));
    }
}
