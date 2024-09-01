package com.sparta.spartanApi.stepDefs;

import com.sparta.spartanApi.AppConfig;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StepDefs {

    ContextStepDef context;

    public StepDefs(ContextStepDef context) {
        this.context = context;
    }

    @Before
    public void before() {
        context.headers = new HashMap<>();
    }
    @Given("the request is authorised")
    public void theRequestIsAuthorised() {
        context.headers.put("Authorization", "Bearer " + AppConfig.getToken());
    }

    @Given("the request is unauthorised")
    public void theRequestIsUnauthorised() {
        context.headers.remove("Authorization");
    }

    @Then("a {int} status code is given")
    public void aStatusCodeIsGiven(int statusCode) {
        assertThat(context.response.statusCode(), is(statusCode));
    }

    @And("a message is returned describing the invalid id {string}")
    public void aMessageIsReturnedDescribingTheInvalidId(String id) {
        var errors = new JSONObject(context.response.jsonPath().getJsonObject("errors"));
        var idErrors = errors.get("id");
        assertThat(idErrors, is(List.of("The value '" + id + "' is not valid.")));
    }
}
