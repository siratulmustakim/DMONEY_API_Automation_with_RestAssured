package testRunner;

import controller.MoneySend;
import controller.MoneyWithdraw;
import io.restassured.path.json.JsonPath;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class MoneySendTestRunner {

    //     Negative case
    @Test(priority = 1, description = "Customer_1 sending 500/- to invalid number")
    public void doNotSendMoney() throws IOException, ParseException {
        MoneySend send = new MoneySend();

        String from_account = Utils.readFromJSON(0); // phn number of customer_1
        String to_account = Utils.readFromJSON(1)+"1"; // phn number of customer_2

        JsonPath res = send.doNotSend(from_account, to_account, 500);

        String message = res.get("message");
        Assert.assertTrue(message.contains("Account does not exist"));
    }


    @Test(priority = 2, description = "Customer_1 sending 500/- to customer_2")
    public void doSendMoney() throws IOException, ParseException {
        MoneySend send = new MoneySend();

        String from_account = Utils.readFromJSON(0); // phn number of customer_1
        String to_account = Utils.readFromJSON(1); // phn number of customer_2

        JsonPath res = send.doSend(from_account, to_account, 500);

        String message = res.get("message");
        Assert.assertTrue(message.contains("Send money successful"));
    }
}
