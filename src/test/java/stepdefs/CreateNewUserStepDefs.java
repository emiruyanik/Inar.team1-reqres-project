package stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import pojo.CreateUser;
import pojo.GetListUsers;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateNewUserStepDefs extends BaseStep {
    private RequestSpecification request;
    ObjectMapper objectMapper = new ObjectMapper();
    String id;


    @Given("The user sends {string} and {string} credantials")
    public void theUserSendsAndCredantials(String name, String job) {
        CreateUser user = new CreateUser(name, job);
        try {
            String jsonBody = objectMapper.writeValueAsString(user);
            request = given().contentType("application/json").body(jsonBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @When("The user sends POST request to accessible create API endpoint")
    public void theUserSendsPOSTRequestToAccessibleAPIEndpoint() {
        response = request.when().post(createUserEndPoint);
    }

    @And("The user should see greater than zero id")
    public void theUserShouldSeeGreaterThanZeroId() {
        Assertions.assertThat(Integer.parseInt(response.jsonPath().getString("id")+"")).isGreaterThan(0);
        id = response.jsonPath().getString("id");
    }

    @And("The user should see created account in list of users")
    public void theUserShouldSeeCreatedAccountInListOfUsers() {
        request = given().basePath(createUserEndPoint);
        response = request.when().get();
//        List<GetListUsers> list = JsonPath.from(response.asString()).getList("data", GetListUsers.class);
//        System.out.println(list);

        List<Map<String, Object>> dataList = response.jsonPath().getList("data");
        boolean isMatchedID = dataList.stream().anyMatch(map -> map.get("id").equals(id));
        Assertions.assertThat(isMatchedID).isTrue();
    }
}
