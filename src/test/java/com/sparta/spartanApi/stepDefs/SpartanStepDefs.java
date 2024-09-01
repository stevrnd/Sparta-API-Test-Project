package com.sparta.spartanApi.stepDefs;

import com.sparta.spartanApi.pojos.Spartan;
import com.sparta.spartanApi.pojos.SpartanDTO;
import com.sparta.spartanApi.utilities.SpartanUtils;
import com.sparta.spartanApi.utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.simple.JSONObject;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanStepDefs {
    ContextStepDef context;

    private String body;

    private static final String TEST_DATA_PATH = "src/test/resources/jsonResponses/";
    public SpartanStepDefs(ContextStepDef context) {
        this.context = context;
    }

    @When("get all spartans request is made")
    public void theGetSpartansRequestIsMade() {
        context.response = SpartanUtils.getAllSpartans(context.headers);
    }

    @And("the response body should be a JSON array containing Spartan objects")
    public void theResponseBodyShouldBeAJSONArrayContainingSpartanObjects() {
        SpartanDTO[] spartans = context.response.as(SpartanDTO[].class);
        assertThat("The spartans array should not be empty", spartans, arrayWithSize(greaterThan(0)));
    }

    @When("get specific spartan request is made for id {string}")
    public void getSpecificSpartanRequestIsMadeForId(String id) {
        context.response = SpartanUtils.getSpartan(context.headers, id);
    }


    @And("the response body should be a JSON object: {string}")
    public void theResponseBodyShouldBeAJSONObject(String fileName) {
        String path = TEST_DATA_PATH + fileName;
        String body = Utilities.getJsonFromFile(path);
        var expectedSpartan = Utilities.getPojoFromJson(body, SpartanDTO.class);
        var responseSpartan = context.response.as(SpartanDTO.class);
        assertThat(responseSpartan, equalTo(expectedSpartan));
    }

    @When("post spartan request is sent using {string}")
    public void postSpartanRequestIsSentUsing(String fileName) {
        String path = TEST_DATA_PATH + fileName;
        body = Utilities.getJsonFromFile(path);
        context.response = SpartanUtils.createSpartan(context.headers, body);
    }

    @And("the response body should contain an error message indicating the missing spartan field")
    public void theResponseBodyShouldContainAnErrorMessageIndicatingTheMissingSpartanField() {
        var errors = new JSONObject(context.response.jsonPath().getJsonObject("errors"));
        var spartanError = errors.get("spartan");
        assertThat(spartanError, is(List.of("The spartan field is required.")));
    }

    @And("the response body should contain an error message indicating conversion from string to integer")
    public void theResponseBodyShouldContainAnErrorMessageIndicatingConversionFromStringToInteger() {
        var errors = new JSONObject(context.response.jsonPath().getJsonObject("errors"));
        var spartanError = errors.get("id");
        assertThat(spartanError, is(List.of("Could not convert string to integer: one. Path 'id', line 2, position 13.")));
    }

    @When("a put request is made for {string} Spartan ID using: {string}")
    public void aPutRequestIsMadeForSpartanIDUsing(String id, String fileName) {
        String path = TEST_DATA_PATH + fileName;
        body = Utilities.getJsonFromFile(path);
        context.response = SpartanUtils.updateSpartan(context.headers, id, body);
    }

    @And("the response body should match the updated Spartan object with the new data")
    public void theResponseBodyShouldMatchTheUpdatedSpartanObjectWithTheNewData() {
        var requestSpartan = Utilities.getPojoFromJson(body, Spartan.class);
        var responseSpartan = context.response.as(SpartanDTO.class);
        assertThat(requestSpartan.equalsDTO(responseSpartan), is(true));
    }

    @And("the response body should contain an error message indicating the validation errors")
    public void theResponseBodyShouldContainAnErrorMessageIndicatingTheValidationErrors() {
        var errors = new JSONObject(context.response.jsonPath().getJsonObject("errors"));
        var firstnameErrors = errors.get("FirstName");
        assertThat(firstnameErrors, is(List.of("The field FirstName must be a string with a minimum length of 6 and a maximum length of 50.")));
    }

    @When("a delete request is made for a Spartan with ID {string}")
    public void aDeleteRequestIsMadeForASpartanWithID(String id) {
        context.response = SpartanUtils.deleteSpartan(context.headers, id);
    }
}
