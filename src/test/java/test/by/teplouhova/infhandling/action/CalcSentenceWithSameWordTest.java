package test.by.teplouhova.infhandling.action;

import by.teplouhova.infhandling.action.TextAction;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalcSentenceWithSameWordTest {
    @DataProvider(name="sentence")
    public Object [][] createData(){
        return new Object[][]{
                {
                        "\tThe String class is immutable class.The String string.\n"+
                                "\tThe contents of String object cannot be modified after object it has been created.\n",3
                }
        };
    }

    @Test(dataProvider = "sentence")
    public void calculateSentenceWithSameWordsTrue(String text, int expected){
        int actual=new TextAction().calculateSentenceWithSameWords(text);
        assertEquals(actual,expected);
    }
}
