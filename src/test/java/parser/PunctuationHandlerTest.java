package parser;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.impl.SymbolLeaf;
import by.teplouhova.infhandling.composite.impl.TypeSymbol;
import by.teplouhova.infhandling.parser.PunctuationHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sun.awt.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class PunctuationHandlerTest {

    @DataProvider(name="marks")
    public Object[][]createData(){
        return new Object[][]{
                {
                    "word,","word", Arrays.asList(new SymbolLeaf(',', TypeSymbol.PUNCTUATION_MARK))
                },
                {
                        "word...","word", Arrays.asList(new SymbolLeaf('.', TypeSymbol.PUNCTUATION_MARK),
                        new SymbolLeaf('.', TypeSymbol.PUNCTUATION_MARK),
                        new SymbolLeaf('.', TypeSymbol.PUNCTUATION_MARK)
                        )
                },
        };
    }


    @Test(dataProvider = "marks")
    public void getPunctuationMarksTest(String text, String coincidence, List<Component> expected){
        ArrayList<Component> actual=new PunctuationHandler().getPunctuationMarks(text,coincidence);
        assertEquals(actual,expected);
    }
}
