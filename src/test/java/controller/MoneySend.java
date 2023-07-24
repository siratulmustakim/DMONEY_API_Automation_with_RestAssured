package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import setup.Setup;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class MoneySend extends Setup {

    public MoneySend() throws IOException {
        initConfig();
    }

    public JsonPath doNotSend(String from_account, String to_account, int amount){
        RestAssured.baseURI = prop.getProperty("baseUrl");

        UserModel userModel = new UserModel(from_account, to_account, amount);
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                        .body(userModel)
                .when()
                        .post("/transaction/sendmoney")
                .then()
                        .assertThat().statusCode(404).extract().response();

        System.out.println(res.getBody().asString());

        return res.jsonPath();
    }

    public JsonPath doSend(String from_account, String to_account, int amount){
        RestAssured.baseURI = prop.getProperty("baseUrl");

        UserModel userModel = new UserModel(from_account, to_account, amount);
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                        .body(userModel)
                        .when()
                        .post("/transaction/sendmoney")
                        .then()
                        .assertThat().statusCode(201).extract().response();

        System.out.println(res.getBody().asString());

        return res.jsonPath();
    }

}
