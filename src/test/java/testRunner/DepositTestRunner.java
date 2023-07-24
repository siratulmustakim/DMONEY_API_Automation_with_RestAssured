package testRunner;

import controller.DepositToUser;
import io.restassured.path.json.JsonPath;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class DepositTestRunner {

    @Test(priority = 1, description = "System doing deposit of 2000/- to agent")
    public void depositToAgent() throws IOException, ParseException {
        DepositToUser deposit = new DepositToUser();

        String to_account = Utils.readFromJSON(2); // phn number of agent
        JsonPath res = deposit.doDeposit( "SYSTEM",to_account, 2000);

        String message = res.get("message");
        Assert.assertTrue(message.contains("Deposit successful"));
    }


    // Negative case
    @Test(priority = 2, description = "Agent doing deposit of 4000/- to customer_1")
    public void depositToCustomerInvalid() throws IOException, ParseException {
        DepositToUser deposit = new DepositToUser();

        String to_account = Utils.readFromJSON(0); // phn number of customer_1
        String from_account = Utils.readFromJSON(2); // phn number of agent
        JsonPath res = deposit.doDepositInvalid( from_account,to_account, 4000);

        String message = res.get("message");
        Assert.assertTrue(message.contains("Insufficient balance"));
    }

    @Test(priority = 3, description = "Agent doing deposit of 1500/- to customer_1")
    public void depositToCustomer() throws IOException, ParseException {
        DepositToUser deposit = new DepositToUser();

        String to_account = Utils.readFromJSON(0); // phn number of customer_1
        String from_account = Utils.readFromJSON(2); // phn number of agent
        JsonPath res = deposit.doDeposit( from_account,to_account, 1500);

        String message = res.get("message");
        Assert.assertTrue(message.contains("Deposit successful"));
    }
}
