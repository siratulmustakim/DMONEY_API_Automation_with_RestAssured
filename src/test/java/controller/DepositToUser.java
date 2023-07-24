package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import setup.Setup;

import java.io.IOException;
import static io.restassured.RestAssured.given;


public class DepositToUser extends Setup {

    public DepositToUser() throws IOException {
        initConfig();
    }

    public JsonPath doDepositInvalid(String from_account, String to_account, int amount){
        UserModel userModel = new UserModel(from_account, to_account, amount);

        RestAssured.baseURI = prop.getProperty("baseUrl");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                        .body(userModel)
                        .when()
                        .post("/transaction/deposit")
                        .then()
                        .assertThat().statusCode(208).extract().response();


        // see the res body, to check if get any error
        System.out.println(res.getBody().asString());

        return res.jsonPath();

    }

    public JsonPath doDeposit(String from_account, String to_account, int amount){
        UserModel userModel = new UserModel(from_account, to_account, amount);

        RestAssured.baseURI = prop.getProperty("baseUrl");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                        .body(userModel)
                .when()
                        .post("/transaction/deposit")
                .then()
                        .assertThat().statusCode(201).extract().response();


        // see the res body, to check if get any error
        System.out.println(res.getBody().asString());

        return res.jsonPath();

    }

//    public static void main(String[] args) throws IOException {
//        DepositToUser depo = new DepositToUser();
//        depo.doDeposit( "SYSTEM","01980763410", 20);
//    }

}


















