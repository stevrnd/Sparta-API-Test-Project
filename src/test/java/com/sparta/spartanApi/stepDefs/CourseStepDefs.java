package com.sparta.spartanApi.stepDefs;

import com.sparta.spartanApi.pojos.CourseDTO;
import com.sparta.spartanApi.utilities.CourseUtils;
import com.sparta.spartanApi.utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CourseStepDefs {
    ContextStepDef context;

    private static final String TEST_DATA_PATH = "src/test/resources/jsonResponses/";
    public CourseStepDefs(ContextStepDef context) {
        this.context = context;
    }

    @When("the get courses request is made")
    public void theGetCoursesRequestIsMade() {
        context.response = CourseUtils.getAllCourses(context.headers);
    }

    @And("the response body should be a JSON array containing all courses")
    public void aListOfCourseObjectsIsReturned() {
        CourseDTO[] courses = context.response.as(CourseDTO[].class);
        assertThat("The courses array should not be empty", courses, arrayWithSize(greaterThan(0)));
    }

    @When("the get course request is made to id {string}")
    public void theGetCourseRequestIsMadeToId(String id) {
        context.response = CourseUtils.getCourse(context.headers, id);
    }

    @And("a CourseDTO object is returned matching {string}")
    public void aCourseDTOObjectIsReturnedMatchingTheSpecifiedCourse(String fileName) {
        String path = TEST_DATA_PATH + fileName;
        String body = Utilities.getJsonFromFile(path);
        var expectedCourse = Utilities.getPojoFromJson(body, CourseDTO.class);
        var responseCourse = context.response.as(CourseDTO.class);
        assertThat(responseCourse, equalTo(expectedCourse));
    }
}
