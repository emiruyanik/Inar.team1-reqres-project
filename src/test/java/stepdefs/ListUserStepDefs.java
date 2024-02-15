package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class ListUserStepDefs extends BaseStep {
    private RequestSpecification request;
    SoftAssertions softAssertions = new SoftAssertions();

    @Given("The API endpoint is available")
    public void theAPIEndpointIsAvailable() {
        request = given().basePath(listUserEndPoint);
    }

    @When("The user send GET request to API endpoint")
    public void theUserSendGETRequestToAPIEndpoint() {
        response = request.when().get();
    }



    @And("List of users is as in the following:")
    public void listOfUsersIsAsInTheFollowing(DataTable dataTable) {
        List<Map<String, String>> usersList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> user : usersList) {
            int id = Integer.parseInt(user.get("id"));
            String expectedEmail = user.get("email");
            String actualEmail = response.jsonPath().getString("data.find {user->user.id==" + id + "}.email");

            String expectedFirstName = user.get("first_name");
            String actualFirstName = response.jsonPath().getString("data.find {user->user.id==" + id + "}.first_name");

            String expectedLastName = user.get("last_name");
            String actualLastname = response.jsonPath().getString("data.find {user->user.id==" + id + "}.last_name");

            softAssertions.assertThat(expectedEmail).isEqualTo(actualEmail);
            softAssertions.assertThat(expectedFirstName).isEqualTo(actualFirstName);
            softAssertions.assertThat(expectedLastName).isEqualTo(actualLastname);

            softAssertions.assertAll();
        }
    }
}
