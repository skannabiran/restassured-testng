package com.gen.service.user;

import com.gen.common.UserAPIBaseConfig;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class TestDeleteRequests extends UserAPIBaseConfig {

    @DataProvider(name = "deleteUserRestAssured")
    public Iterator<Object[]> deleteRestUsers() {
        final List<Object[]> deleteData = new ArrayList<>();
        deleteData.add(new Object[]{2});
        return deleteData.iterator();
    }

    @Test(dataProvider = "deleteUserRestAssured")
    public void deleteRequestTests(final int userId) {
        given().when()
                .delete("/api/users/" + userId)
                .then()
                .assertThat()
                .statusCode(204);
    }
}