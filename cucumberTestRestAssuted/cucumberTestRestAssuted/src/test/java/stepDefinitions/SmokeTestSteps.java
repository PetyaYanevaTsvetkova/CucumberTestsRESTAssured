package stepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;

public class SmokeTestSteps {
  //  private String endpoint1;
    private String baseURL;
    private int currentResponseStatus;

    @Given("get base URL:{}")
    public void setBaseURL(String url) {
        baseURL = url;
    }

    @Given("endpoint is: {}")
    public void endpointIsEndpoint(String endpoint) {
        RestAssured.baseURI = baseURL + endpoint;
    }

    @When("get Response Status")
    public void getResponseStatus() {
        currentResponseStatus = RestAssured
                .given().log().all()
                .when()
                .get()
                .getStatusCode();
    }

    @Then("Verify Status code is {int}")
    public void verifyStatusCodeIs(int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, currentResponseStatus,
                String.format("Test fail: Response Status Code is %d", currentResponseStatus));
    }
}

