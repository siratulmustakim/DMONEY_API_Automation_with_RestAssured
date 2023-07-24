package testRunner;

import controller.CheckBalance;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class CheckBLTestRunner {
    @Test(priority = 1, description = "Checking balance of customer_2")
    public void doCheck() throws IOException, ParseException {
        CheckBalance check = new CheckBalance();

        String phone_number = Utils.readFromJSON(1); // phn number of customer_2
        check.doCheck(phone_number);
    }
}
