package stepdefs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigManager;

public abstract class BaseStep {

    static protected Response response;
    protected String listUserEndPoint;
    protected String createUserEndPoint;
    protected String registerEndPoint;

    public BaseStep() {
        RestAssured.baseURI = ConfigManager.getProperty("base.URI");
        listUserEndPoint = ConfigManager.getProperty("list.users.endpoint");
        createUserEndPoint = ConfigManager.getProperty("create.user.endpoint");
        registerEndPoint = ConfigManager.getProperty("register.user.endpoint");
    }
}
