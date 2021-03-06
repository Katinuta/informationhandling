package test.by.teplouhova.infhandling.action;

import by.teplouhova.infhandling.action.TextAction;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ChangeLastFirstLexemeTest {
    @DataProvider(name = "sentence")
    public Object[][] createData() {
        return new Object[][]{
                {
                    "\tHello is word!\n","\tword! is Hello\n"

                },
                {
                    "\tThe String class is immutable.\n"+
                            "\tThe contents of String object cannot be modified after it has been created.\n",
                        "\timmutable. String class is The\n"+
        "\tcreated. contents of String object cannot be modified after it has been The\n"

                }
        };
    }

    @Test(dataProvider = "sentence")
    public void changeLexeme(String text,String expected){
        String actual=new TextAction().changeFirstLastLexeme(text);
       assertEquals(actual,expected) ;
    }
}
