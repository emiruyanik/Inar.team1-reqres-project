package stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import pojo.UserRegistration;

import static io.restassured.RestAssured.given;

public class RegistrationStepDefs extends BaseStep {
    private RequestSpecification request;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Given("The user has valid credantials as {string} and {string}")
    public void theUserHasValidCredantialsAsAnd(String email, String password) {
        UserRegistration user = new UserRegistration(email, password);
        try {
            String jsonBody = objectMapper.writeValueAsString(user);
            request = given().contentType("application/json").body(jsonBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @When("The user sends POST request to accessible register end point")
    public void theUserSendsPOSTRequestToAccessibleRegisterEndPoint() {
        response = request.when().post(registerEndPoint);
    }

    @And("The token shouldn't be null")
    public void theTokenShouldnTBeNull() {
        String token=response.jsonPath().getString("token");
        System.out.println(token);
        Assertions.assertThat(token).isNotEmpty();
    }


}
