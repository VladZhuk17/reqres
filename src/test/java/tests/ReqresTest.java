package tests;

import com.google.gson.Gson;
import io.restassured.response.Response;
import objects.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;

public class ReqresTest {

    @Test
    public void getListUsersTest() {
        String responseBody = given()
                .when().get("  https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(HTTP_OK)
                .extract().body().asString();
        UserList userList = new Gson().fromJson(responseBody, UserList.class);
        int actualSizeUserList = userList.getData().size();
        int expectedSizeUserList = 6;
        Assert.assertEquals(actualSizeUserList, expectedSizeUserList);
    }

    @Test
    public void getSingleUserTest() {
        Response response = given()
                .log().all()
                .when().get("  https://reqres.in/api/users/2")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_OK);
    }

    @Test
    public void getSingleUserNotFoundTest() {
        Response response = given()
                .log().all()
                .when().get("  https://reqres.in/api/users/23")
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_NOT_FOUND);
    }

    @Test
    public void getListOfResourceTest() {
        String responseBody = given()
                .when().get("  https://reqres.in/api/unknown")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().asString();
        ResourceList resourceList = new Gson().fromJson(responseBody, ResourceList.class);
        int actualYear = resourceList.getData().get(0).getYear();
        int expectedYear = 2000;
        Assert.assertEquals(actualYear, expectedYear);
    }

    @Test
    public void getListOfResourceNotFoundTest() {
        Response response = given()
                .log().all()
                .when().get("  https://reqres.in/api/unknown/23")
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_NOT_FOUND);
    }

    @Test
    public void postCreateUsersTest() {
        User user = User.builder()
                .name("morpheus")
                .job("leader")
                .build();
        Response response = given()
                .body(user)
                .when()
                .post("https://reqres.in/api/users").then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_CREATED);
    }

    @Test
    public void putUpdateUserTest() {
        User user = User.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        Response response = given()
                .body(user)
                .when()
                .put("https://reqres.in/api/users/2").then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_OK);
    }

    @Test
    public void patchUpdateUserTest() {
        User user = User.builder().name("morpheus").job("zion resident").build();
        Response response = given()
                .body(user)
                .when()
                .patch("https://reqres.in/api/users/2").then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_OK);
    }

    @Test
    public void deleteUserTest() {
        Response response = given()
                .log().all()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_NO_CONTENT);
    }

    @Test
    public void postRegisterSuccessfulTest() {
        Register register = Register.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();
        Response response = given()
                .header("Content-type", "application/json")
                .body(register)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_OK);
    }

    @Test
    public void postRegisterUnSuccessfulTest() {
        Register register = Register.builder().email("eve.holt@reqres.in").build();
        Response response = given()
                .body(register)
                .when().post("https://reqres.in/api/register")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_BAD_REQUEST);
    }

    @Test
    public void postLoginSuccessfulTest() {
        Login login = Login.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        Response response = given()
                .header("Content-type", "application/json")
                .body(login)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_OK);
    }

    @Test
    public void postLoginUnSuccessfulTest() {
        Login login = Login.builder()
                .email("sydney@fife")
                .build();
        Response response = given()
                .body(login)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_BAD_REQUEST);
    }

    @Test
    public void getDelayedResponseTest(){
        String body = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(HTTP_OK)
                .log().all()
                .extract().body().asString();
        UserList userList = new Gson().fromJson(body, UserList.class);
        String actualFirstName = userList.getData().get(2).getFirstName();
        String expectedFirstName = "Tobias";
        Assert.assertEquals(actualFirstName, expectedFirstName);
    }
}
