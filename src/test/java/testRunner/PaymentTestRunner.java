package testRunner;

import controller.MakePayment;
import io.restassured.path.json.JsonPath;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;


public class PaymentTestRunner {

//    Negative case
@Test(priority = 1, description = "Customer_2 makes payment of 100/- to merchant in wrong number")
    public void doNotPayment() throws IOException, ParseException {
        MakePayment payment = new MakePayment();

        String from_account = Utils.readFromJSON(1); // phn number of customer_2
        String to_account = "01686606906";  // wrong number

        JsonPath res = payment.doNotPayment(from_account, to_account, 100);

        String message = res.get("message");
        Assert.assertTrue(message.contains("A/C should be merchant type"));
    }

    @Test(priority = 2, description = "Customer_2 makes payment of 100/- to merchant")
    public void doPayment() throws IOException, ParseException {
        MakePayment payment = new MakePayment();

        String from_account = Utils.readFromJSON(1); // phn number of customer_2
        String to_account = "01686606905";

        JsonPath res = payment.doPayment(from_account, to_account, 100);

        String message = res.get("message");
        Assert.assertTrue(message.contains("Payment successful"));
    }
}
