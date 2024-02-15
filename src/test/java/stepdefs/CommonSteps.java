package stepdefs;

import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;

public class CommonSteps extends BaseStep{
    @Then("The response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Assertions.assertThat(response.statusCode()).as("Wrong status code").isEqualTo(statusCode);

    }
}
