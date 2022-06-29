package stepDefinitions;

import helper.POJOs.CategoryPOJO;
import helper.PropertiesHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class CategoriesSteps {
    private CategoryPOJO categoryPOJO;
    private PropertiesHelper properties = PropertiesHelper.getInstance();
    private Response response;

    @Given("set endpoint for categories: {}")
    public void endpointCategories(String endpoint) {
        RestAssured.baseURI = properties.getProperty("url") + endpoint;
        System.out.println("Set RestAssured.baseURI to: " + RestAssured.baseURI);
    }

    @Given("We make a get request to the category endpoint")
    public void perform_get_request() {
        response = RestAssured.given().get();
        categoryPOJO = response.as(CategoryPOJO.class);
        System.out.println("Received Category response is: " + categoryPOJO);
    }

    @When("Verify Status code for categories endpoint is {int}")
    public void verifyStatusCode(Integer expectedStatusCode) {
        System.out.println("Verifying expected status code: " + expectedStatusCode);
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode(),
                "Status Code is not as expected");
    }

    @Then("Verify that the CategoryPOJO's field for count and count of all categories list are the same")
    public void verifyTheCountOfAllCategories() {
        System.out.println("Verifying counts match...");
        Assertions.assertEquals(categoryPOJO.getCount(), categoryPOJO.getCategories().size(),
                "Expected count and categories size don't match");
    }

    @And("Verify the categories count is equal to {int}")
    public void verifyCount(Integer expectedCount) {
        System.out.println("Verifying expected count is: " + expectedCount);
        Assertions.assertEquals(expectedCount, categoryPOJO.getCount(),
                "Count value is not as expected");
    }

    @Then("Verify that the response contains {}")
    public void compareTheContentOfCategories(String category) {
        List<String> categories = categoryPOJO.getCategories();
        Assertions.assertTrue
                (categories.contains(category),category + " category is missing!");
    }
}
