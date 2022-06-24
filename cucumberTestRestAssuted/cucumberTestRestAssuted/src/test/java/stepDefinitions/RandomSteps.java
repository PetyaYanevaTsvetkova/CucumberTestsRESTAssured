package stepDefinitions;
import helper.PropertiesHelper;
import helper.ResponseHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import java.util.List;
import java.util.Map;

public class RandomSteps {
    private PropertiesHelper properties = PropertiesHelper.getInstance();
    private String randomEndpoint;
    private JsonPath jsonPathRandom;
    private int countOfRandomFromList;
    private int countFromResponseValue;
    private Response response;

    @Given("set endpoint for random: {}")
    public void endpointCategories(String endpoint) {
        RestAssured.baseURI = properties.getProperty("url");
        randomEndpoint = endpoint;
        System.out.println("Set RestAssured.baseURI to: " + RestAssured.baseURI);
    }

    @Given("We make a get request to the random endpoint")
    public void weMakeAGetRequestToTheRandomEndpoint() {
        response = RestAssured.given().get(randomEndpoint);
        jsonPathRandom = response.jsonPath();
    }

    @When("Verify the Status code for random endpoint")
    public void verifyTheStatusCodeForRandomEndpoint() {
        Assertions.assertEquals(200, response.getStatusCode(),
                "Not successful HTTP request");
    }

    @When("Get the count of all random in List and the count value from the response")
    public void getTheCountOfAllRandomInListAndTheCountValueFromTheResponse() {
        List<Object> entries = jsonPathRandom.getList("entries");
        countOfRandomFromList = entries.size();
        countFromResponseValue = jsonPathRandom.getInt("count");
    }

    @Then("Verify that count and count of all random are the same")
    public void verifyThatCountAndCountOfAllRandomAreTheSame() {
        Assertions.assertEquals(countFromResponseValue, countOfRandomFromList,
                "Test fail: the actual count is different from the expected");
    }

    @And("Verify the random entries count is equal to {int}")
    public void verifyTheRandomEntriesCountIsEqualTo(int expectedCount) {
        Assertions.assertEquals(expectedCount, countOfRandomFromList,
                "Test fail: the actual count is different from the expected");
    }

    @Then("Verify that the values for the {} are not empty, except for Auth field")
    public void verifyThatTheValuesForTheFieldsAreNotEmptyExceptForAuthField(String fieldName) {
        Assertions.assertFalse(jsonPathRandom.getString(String.format("entries[0].%s", fieldName)).isEmpty(),
                String.format("Test fail: The value of %s is empty", fieldName));
    }

    @When("Get random entry with the following parameters: {} and {}")
    public void getRandomEntryWithTheFollowingParametersParameterAndValue(String parameter, String value) {
        Response responseWithParameter = ResponseHelper
                .responseWithParameter(Map.of(parameter, value), randomEndpoint);
        jsonPathRandom = responseWithParameter.jsonPath();
        countFromResponseValue = jsonPathRandom.getInt("count");
        countOfRandomFromList = jsonPathRandom.getList("entries").size();
    }

    @Then("Verify that count and count of all random are the same {int}")
    public void verifyThatCountAndCountOfAllRandomAreTheSameCount(Integer givenCount) {
        Assertions.assertEquals(givenCount, countFromResponseValue);
    }

    @Then("Verify that response contents {} for param {}")
    public void verifyThatResponseContentsValueForParamParameter(String value, String parameter) {
        Response responseWithParameter = ResponseHelper
                .responseWithParameter(Map.of(parameter, value), randomEndpoint);
        jsonPathRandom = responseWithParameter.jsonPath();
        String jsonVar = properties.getProperty(parameter);
        String jsonString = jsonPathRandom.getString((String.format("entries[0].%s", jsonVar)));
        Assertions.assertTrue(jsonString.toLowerCase().contains(value.toLowerCase()),
                String.format("Test fail: The value of the parameter %s not contains %s", parameter, value));
    }

    @When("Verify for entry, that response contains {} for param {} and {} for param {}")
    public void verifyForEntryThatResponseContainsValueForParamParameterAndValueForParamParameter
            (String value1, String parameter1, String value2, String parameter2) {

        Response responseWithParameter = ResponseHelper.responseWithParameter(Map.of(parameter1, value1, parameter2, value2), randomEndpoint);
        jsonPathRandom = responseWithParameter.jsonPath();

        String jsonVar1 = properties.getProperty(parameter1);
        String forPar1 = jsonPathRandom.getString(String.format("entries[0].%s", jsonVar1));

        String jsonVar2 = properties.getProperty(parameter2);
        String forPar2 = jsonPathRandom.getString(String.format("entries[0].%s", jsonVar1));

        Boolean aBoolean1 = forPar1.toLowerCase().contains(value1.toLowerCase());
        Boolean aBoolean2 = forPar2.toLowerCase().contains(value2.toLowerCase());

        Assertions.assertTrue(aBoolean1 && aBoolean2,
                String.format("Test fail: The entry don't contains %s for param %s and %s for param %s",
                        value1, parameter1, value2, parameter2));
    }
}

 