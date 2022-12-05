package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HeadHunter {
//            http://api.hh.ru/vacancies

        @Test
    public void vacancyTest(){
                String body = given()
                        .when()
                        .get("http://api.hh.ru/vacancies?test=QA automation")
                        .then()
                        .log().all()
                        .statusCode(200).extract().body().asString();

        }

}
