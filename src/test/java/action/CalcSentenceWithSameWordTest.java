package action;

import by.teplouhova.infhandling.action.TextAction;
import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.SymbolLeaf;
import by.teplouhova.infhandling.composite.impl.TypeSymbol;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
