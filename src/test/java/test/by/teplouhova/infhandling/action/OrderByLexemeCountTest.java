package test.by.teplouhova.infhandling.action;

import by.teplouhova.infhandling.action.TextAction;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class OrderByLexemeCountTest {
    @DataProvider(name="lexemes")
    public Object [][] createData(){
        return new Object[][]{

                {
                        "\tThe String class is immutable.\n"+
                                "\tThe contents of String object cannot be modified after it has been created.\n"+
                                "\tThe String class.\n",
                        "The String class.\n"+ "The String class is immutable.\n"+
                                "The contents of String object cannot be modified after it has been created.\n"
                }
        };
    }

    @Test(dataProvider = "lexemes")
    public void orderByLexemeCountTest(String text, String expected){
        String actual=new TextAction().orderByLexemeCount(text);
        assertEquals(actual,expected);
    }

}
