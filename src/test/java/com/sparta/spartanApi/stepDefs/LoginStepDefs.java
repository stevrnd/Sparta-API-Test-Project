package com.sparta.spartanApi.stepDefs;

import com.sparta.spartanApi.AppConfig;
import com.sparta.spartanApi.pojos.LoginRequest;
import com.sparta.spartanApi.utilities.LoginUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasLength;

public class LoginStepDefs {
    ContextStepDef context;
    private LoginRequest loginBody;

    public LoginStepDefs(ContextStepDef context) {
        this.context = context;
    }

    @Given("the request contains valid username and password in the body")
    public void theCredentialsUsedAreValid() {
        loginBody = new LoginRequest(AppConfig.getUsername(), AppConfig.getPassword());
    }

    @When("the request is sent")
    public void theLogInRequestIsMade() {
        context.response = LoginUtils.sendLoginRequest(loginBody);
    }

    @And("the response body should contain a valid authentication token")
    public void anAuthTokenIsReturned() {
        String token = context.response.jsonPath().getJsonObject("token");
        assertThat(token, hasLength(249));
    }

    @Given("the request contains an invalid username or password in the body")
    public void theCredentialsUsedAreInvalid() {
        loginBody = new LoginRequest(AppConfig.getUsername(), "password");
    }

}
