package testRunner;

import com.github.javafaker.Faker;
import controller.User;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserTestRunner {

    User user;
    Faker faker;

    // Negative test case
    @Test(priority = 1, description = "Calling user login with wrong creds")
    public void doNotLogin() throws ConfigurationException, IOException {
        user = new User();
        JsonPath res = user.doNotLogin("wrong@roadtocareer.net", "1234");
        String message = res.get("message");
        Assert.assertTrue(message.equals("User not found"));
    }

    @Test(priority = 2, description = "Calling user login")
    public void doLogin() throws ConfigurationException, IOException {
        user = new User();
        JsonPath res = user.doLogin("admin1@roadtocareer.net", "1234");
        String message = res.get("message");
        Assert.assertTrue(message.equals("Login successfully"));
    }

    @Test(priority = 3, description = "Creating new customer_1")
    public void createNewCustomer1() throws IOException, ConfigurationException, ParseException {

        faker = new Faker();
        String name = faker.name().fullName();

        int number = Utils.generateRandomId(1000, 9999);
        String email = "test"+number+"@gmail.com";
        String phone_number = "01980"+number+"10";
        String nid = phone_number;

        user = new User();
        JsonPath res = user.createCustomer(name, email, "1234", phone_number, nid, "Customer");
        String message = res.get("message");

        Assert.assertTrue(message.equals("User created"));
    }


    @Test(priority = 4, description = "Creating new customer_2")
    public void createNewCustomer2() throws IOException, ConfigurationException, ParseException {

        faker = new Faker();
        String name = faker.name().fullName();

        int number = Utils.generateRandomId(1000, 9999);
        String email = "test"+number+"@gmail.com";
        String phone_number = "01980"+number+"10";
        String nid = phone_number;

        user = new User();
        JsonPath res = user.createCustomer(name, email, "1234", phone_number, nid, "Customer") ;
        String message = res.get("message");

        Assert.assertTrue(message.equals("User created"));
    }

    @Test(priority = 5, description = "Creating new agent")
    public void createNewAgent() throws IOException, ConfigurationException, ParseException {

        faker = new Faker();
        String name = faker.name().fullName();

        int number = Utils.generateRandomId(1000, 9999);
        String email = "test"+number+"@gmail.com";
        String phone_number = "01980"+number+"10";
        String nid = phone_number;

        user = new User();
        JsonPath res = user.createCustomer(name, email, "1234", phone_number, nid, "Agent") ;
        String message = res.get("message");

        Assert.assertTrue(message.equals("User created"));
    }
}
