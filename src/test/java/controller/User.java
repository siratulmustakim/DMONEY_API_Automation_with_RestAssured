package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
//import org.junit.jupiter.api.Test;
import org.json.simple.parser.ParseException;
import setup.Setup;
import utils.Utils;
//import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class User extends Setup {

    UserModel userModel;
    JsonPath jsonPath;

    public User() throws IOException {  // it works as PageFactory that we used in TestNG
        initConfig();
    }

    public JsonPath doNotLogin(String email, String password) throws ConfigurationException, FileNotFoundException {

        userModel = new UserModel(email, password);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res =
                given()
                        .contentType("application/json")
                        .body(userModel)
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(404).extract().response();

        // see the res body, to check if get any error
        System.out.println(res.getBody().asString());

        jsonPath = res.jsonPath();
        return res.jsonPath();
    }

    public JsonPath doLogin(String email, String password) throws ConfigurationException, FileNotFoundException {

       userModel = new UserModel(email, password);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res =
                given()
                    .contentType("application/json")
                    .body(userModel)
                .when()
                        .post("/user/login")
                .then()
                        .assertThat().statusCode(200).extract().response();

//        System.out.println(res.toString());

        jsonPath = res.jsonPath();
        String token = jsonPath.get("token");
//        System.out.println(token);

        Utils.saveEnvVar("token", token);

        return res.jsonPath();

    }

    public JsonPath createCustomer(String name, String email, String password ,String phone_number, String nid, String role) throws ConfigurationException, IOException, ParseException {
        userModel = new UserModel(name, email, password, phone_number, nid, role);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                        .body(userModel)
                .when()
                        .post("/user/create")
                .then()
                        .assertThat().statusCode(201).extract().response();

        Utils.saveInJSON(phone_number, role);

        return res.jsonPath();
    }
}
