package adapters;

import com.google.gson.Gson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static utils.StringConstant.*;

public abstract class BaseAdapter {

    protected Gson convector = new Gson();

    protected Response get(String url) {              //возвращаем Response, чтоб потом можно извлечь тело ответа, так и статус код
        return
                given()
                .when().get(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response post(String url, String body) {
        return
                given().header(CONTENT_TYPE, JSON)
                .body(body)
                .when()
                .post(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();

    }

    protected Response put(String url, String body) {
        return
                given()
                .body(body)
                .when()
                .put(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response patch(String url, String body) {
        return
                given()
                .body(body)
                .when()
                .patch(BASE_URL + url)
                .then().log()
                .all().extract()
                .response();
    }

    protected Response delete(String url) {
        return
                given()
                .when()
                .delete(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }
}
