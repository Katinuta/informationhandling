import by.teplouhova.infhandling.chainresponsobility.ExpressionParserHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ExpressionHandlerParserTest {

    private ExpressionParserHandler parserHandler;

    @BeforeClass
    public void before() {
        parserHandler = new ExpressionParserHandler();
    }

    @DataProvider(name="expression")
    public Object[][] createData(){
        return new Object [][]{
                {
                     "(5+ 3++)*2", Arrays.asList("(","5","+","3","+","1",")","*","2")
                },
                {
                    "( 5/ --3)+ 2--", Arrays.asList("(","5","/","3","-","1",")","+","2","-","1")
                }
        };
    }

    @DataProvider(name="polishNotation")
    public Object[][] createDataPolishNotation(){
        return new Object[][]{
                {
                        "( 5/ --3)+ 2--","5 3 / 1 - 2 + 1 - "
                },
        };
    }

    @Test(dataProvider = "expression")
    public void parseExpressionToArrayTest(String expression,List<String> expected) {
        List<String> actual=parserHandler.parseExpressionToArray(expression);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "polishNotation")
    public void parseExpressionToPolishNotation(String expression,String expected){
        String actual=parserHandler.parseExpressionToPolishNotation(expression);
        assertEquals(actual, expected);
    }

    @AfterClass
    public void after() {
        parserHandler = null;
    }
}
