package controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.UserModel;
import setup.Setup;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CheckBalance extends Setup {
    public CheckBalance() throws IOException {
        initConfig();
    }
    public void doCheck(String phone_number){
        RestAssured.baseURI = prop.getProperty("baseUrl");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                .when()
                        .get("/transaction/balance/"+phone_number)
                .then()
                        .assertThat().statusCode(200).extract().response();

        System.out.println(res.getBody().asString());

//        return res.jsonPath();
    }

//    public static void main(String[] args) throws IOException {
//        CheckBalance ck = new CheckBalance();
//        ck.doCheck("01980659810");
//    }
}
