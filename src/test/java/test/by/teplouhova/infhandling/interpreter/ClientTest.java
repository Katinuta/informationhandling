package test.by.teplouhova.infhandling.interpreter;

import by.teplouhova.infhandling.interpreter.impl.Client;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ClientTest {
    @DataProvider(name = "polishNotation")
    public Object[][] createData() {
        return new Object[][]{
                {
                        "5 5 / 1 - 2 + 1 - ", 1.0
                },
                {
                        "5 5 / 1 - 2 * 1 - ", -1.0
                }
        };
    }

    @Test(dataProvider = "polishNotation")

    public void calculateTest(String expression, double expected) {
        double actual = new Client().calculate(expression);
        assertTrue(actual == expected);
    }
}
