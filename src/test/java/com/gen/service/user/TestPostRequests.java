package com.gen.service.user;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gen.config.UserAPIBaseConfig;
import com.gen.data.user.PostData;
import com.gen.data.user.UserData;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestPostRequests extends UserAPIBaseConfig {

    Logger log = LogManager.getLogger(TestPostRequests.class);

    @DataProvider(name = "postData")
    public Iterator<Object[]> postData() {
        final List<Object[]> postData = new ArrayList<>();
        postData.add(new Object[]{"Rahul", "QA"});
        postData.add(new Object[]{"Jane", "Sr.Dev"});
        postData.add(new Object[]{"Albert", "Dev"});
        postData.add(new Object[]{"Johnny", "Project Manager"});
        return postData.iterator();
    }

    @Test(dataProvider = "postData")
    public void testPostRequests(final String name, final String job) {
        final PostData postData = new PostData(name, job);
        final String response = given().contentType(ContentType.JSON)
                .body(postData)
                .when()
                .post("/api/users")
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .assertThat()
                .body("name", equalTo(name))
                .and()
                .assertThat()
                .body("job", equalTo(job))
                .and()
                .assertThat()
                .body("id", notNullValue())
                .and()
                .extract()
                .response()
                .body()
                .asString();

        this.log.info(response);
    }

    @Test
    public void testPostRequestsUsingBuilderPattern() {
        UserData userData = userDataBuilder();
        given().body(userData)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("name", equalTo(userData.getName()))
                .body("job", equalTo(userData.getJob()));

    }

    private UserData userDataBuilder() {
        Faker faker = Faker.instance();
        return UserData.builder()
                .name(faker.name()
                        .firstName())
                .job(faker.company()
                        .profession())
                .build();
    }

}