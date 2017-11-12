package test.by.teplouhova.infhandling.action;

import by.teplouhova.infhandling.action.TextAction;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class InsertValueInTextTest {
    @DataProvider(name="text")
    public Object[][] createData(){
        return new Object[][]{
                {
                    "The String class is immutable." +
                            " The contents of a ((3- i) + j++)*2 object cannot be modified after it has been created.",1,2,
                        "The String class is immutable." +
                                " The contents of a ((3- 1) + 2++)*2 object cannot be modified after it has been created."
                }
        };
    }

    @Test(dataProvider = "text")
    public void  insertValueTest(String text,int i, int j, String expected){
        String actual=new TextAction().insertValueInText(text,i,j);
        assertEquals(actual,expected);
    }
}
