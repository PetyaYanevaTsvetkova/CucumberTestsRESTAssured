package stepDefinitions;
import com.fasterxml.jackson.databind.ObjectMapper;
import helper.POJOs.EntriesRepoPOJO;
import helper.POJOs.EntryPOJO;
import helper.PropertiesHelper;
import helper.ResponseHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntriesSteps {
    private EntryPOJO entryPOJO;
    private PropertiesHelper properties = PropertiesHelper.getInstance();
    private Response response;
    private EntriesRepoPOJO entriesRepoPOJO;
    private String entitiesEndpoint;

    @Given("set endpoint for entries: {}")
    public void endpointCategories(String endpoint) {
        RestAssured.baseURI = properties.getProperty("url") + endpoint;
        System.out.println("Set RestAssured.baseURI to: " + RestAssured.baseURI);
        entitiesEndpoint = endpoint;
    }

    @Given("We make a get request to the entries endpoint")
    public void weMakeAGetRequestToTheEntriesEndpoint() {
        response = RestAssured.given().get();
    }

    @And("Verify the Status code for entries endpoint")
    public void verifyTheStatusCodeForEntriesEndpoint() {
        Assertions.assertEquals(200, response.getStatusCode(),
                "Status code is different from 200");
    }

    @Then("Verify that count and count of all entries are the same")
    public void verifyThatCountAndCountOfAllEntriesAreTheSame() {
        entriesRepoPOJO = response.getBody().as(EntriesRepoPOJO.class);
        Assertions.assertEquals(entriesRepoPOJO.getCount(), entriesRepoPOJO.getEntries().size(),
                "Expected count and entries size don't match");
    }

    @Then("Verify the entries count is equal to {int}")
    public void verifyTheEntriesCountIsEqualTo(int expectedCount) {
        entriesRepoPOJO = response.getBody().as(EntriesRepoPOJO.class);
        Assertions.assertEquals(expectedCount, entriesRepoPOJO.getCount(),
                String.format("Test fail: Count value %d is not as expected %d",
                        entriesRepoPOJO.getCount(), expectedCount));
    }

    @Then("Verify that the values for the fields are not empty, except for Auth field for all of the entries")
    public void verifyThatTheValuesForTheFieldsAreNotEmptyExceptForAuthFieldForAllOfTheEntries() {
        ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        entriesRepoPOJO = response.getBody().as(EntriesRepoPOJO.class);

        List<EntryPOJO> entries = entriesRepoPOJO.getEntries()
                .stream()
                .map(o -> objectMapper.convertValue(o, EntryPOJO.class))
                .collect(Collectors.toList());

        for (EntryPOJO entry : entries) {
            Assertions.assertFalse(entry.getAPI().isEmpty(), "Test Fail: the value for the field API is empty");
            Assertions.assertFalse(entry.getDescription().isEmpty(), "Test Fail: the value for the field API is empty");
            Assertions.assertFalse(entry.getHTTPS().toString().isEmpty(), "Test Fail: the value for the field API is empty");
            Assertions.assertFalse(entry.getCors().isEmpty(), "Test Fail: the value for the field API is empty");
            Assertions.assertFalse(entry.getLink().isEmpty(), "Test Fail: the value for the field API is empty");
            Assertions.assertFalse(entry.getCategory().isEmpty(), "Test Fail: the value for the field API is empty");
        }
    }

    @Then("Verify for all of the entries, that response contains {} for param {}")
    public void verifyForAllOfTheEntriesThatResponseContentsValueForParamParameter(String value, String parameter) {
        ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        Response responseWithParameter =
                ResponseHelper.responseWithParameter(Map.of(parameter, value));

        entriesRepoPOJO = responseWithParameter.as(EntriesRepoPOJO.class);
        List<EntryPOJO> entries = entriesRepoPOJO.getEntries()
                .stream()
                .map(o -> objectMapper.convertValue(o, EntryPOJO.class))
                .collect(Collectors.toList());


        for (EntryPOJO entry : entries) {
            switch (parameter) {
                case "title":
                    Assertions.assertTrue(entry.getAPI().toLowerCase().contains(value.toLowerCase()));
                    break;
                case "description":
                    Assertions.assertTrue(entry.getDescription().toLowerCase().contains(value.toLowerCase()));
                    break;
                case "auth":
                    Assertions.assertTrue(entry.getAuth().toLowerCase().contains(value.toLowerCase()));
                    break;
                case "cors":
                    Assertions.assertTrue(entry.getCors().toLowerCase().contains(value.toLowerCase()));
                    break;
                case "category":
                    Assertions.assertTrue(entry.getCategory().toLowerCase().contains(value.toLowerCase()));
                    break;
            }
        }
    }

    @When("Verify for all the entities, that the response contains {} for param {} and {} for param {}")
    public void verifyContainsValueForParamParameterAndValue
            (String value1, String parameter1, String value2, String parameter2) {
        ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        Response responseWithParameter =
                ResponseHelper.responseWithParameter(Map.of(parameter1, value1, parameter2, value2));

        entriesRepoPOJO = responseWithParameter.as(EntriesRepoPOJO.class);
        List<EntryPOJO> entries = entriesRepoPOJO.getEntries()
                .stream()
                .map(o -> objectMapper.convertValue(o, EntryPOJO.class))
                .collect(Collectors.toList());

        String getField1;
        String getField2;
        for (EntryPOJO entry : entries) {
           getField1 = getFieldByParameterName(entry, parameter1);
           getField2 = getFieldByParameterName(entry, parameter2);

            Boolean aBoolean1 = getField1.contains(value1.toLowerCase());
            Boolean aBoolean2 = getField2.contains(value2.toLowerCase());
            Assertions.assertTrue(aBoolean1 && aBoolean2,
                    String.format("Test fail: The entries, don't contains %s for param %s and %s for param %s",
                            value1, parameter1, value2, parameter2));
        }
    }

    private String getFieldByParameterName(EntryPOJO entry, String parameter1) {

        String getField = null;
        switch (parameter1) {
            case "title":
                getField = entry.getAPI().toLowerCase();
                break;
            case "description":
                getField = entry.getDescription().toLowerCase();
                break;
            case "auth":
                getField = entry.getAuth().toLowerCase();
                break;
            case "cors":
                getField = entry.getCors().toLowerCase();
                break;
            case "category":
                getField = entry.getCategory().toLowerCase();
                break;
        }
        return getField;
    }
}





