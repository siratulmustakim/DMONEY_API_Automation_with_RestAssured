package testRunner;

import controller.MoneyWithdraw;
import io.restassured.path.json.JsonPath;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class WithdrawTestRunner {

//    Negative case
    @Test(priority = 1, description = "Customer_1 doing withdraw of 5000/-")
    public void doNotWithdraw() throws IOException, ParseException {
        MoneyWithdraw withdraw = new MoneyWithdraw();

        String to_account = Utils.readFromJSON(2); // phn number of agent
        String from_account = Utils.readFromJSON(0); // phn number of customer_1

        JsonPath res = withdraw.doNotWithdraw(from_account, to_account, 5000);

        String message = res.get("message");
        Assert.assertTrue(message.contains("Insufficient balance"));

    }

    @Test(priority = 2, description = "Customer_1 doing withdraw of 500/-")
    public void doWithdraw() throws IOException, ParseException {
        MoneyWithdraw withdraw = new MoneyWithdraw();

        String to_account = Utils.readFromJSON(2); // phn number of agent
        String from_account = Utils.readFromJSON(0); // phn number of customer_1

        JsonPath res = withdraw.doWithdraw(from_account, to_account, 500);

        String message = res.get("message");
        Assert.assertTrue(message.contains("Withdraw successful"));

    }
}
