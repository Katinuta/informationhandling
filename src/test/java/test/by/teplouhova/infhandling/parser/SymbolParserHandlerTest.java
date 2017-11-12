package test.by.teplouhova.infhandling.parser;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.SymbolLeaf;
import by.teplouhova.infhandling.composite.impl.TypeSymbol;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;
import by.teplouhova.infhandling.parser.impl.SymbolParserHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

public class SymbolParserHandlerTest {

    @DataProvider(name = "word")
    public Object[][] createData() {
        return new Object[][]{
                {"word-word",
                        new CompositionTextElement(
                                Arrays.asList(new SymbolLeaf('w', TypeSymbol.LETTER), new SymbolLeaf('o', TypeSymbol.LETTER),
                                        new SymbolLeaf('r', TypeSymbol.LETTER), new SymbolLeaf('d', TypeSymbol.LETTER),
                                        new SymbolLeaf('-', TypeSymbol.PUNCTUATION_MARK), new SymbolLeaf('w', TypeSymbol.LETTER),
                                        new SymbolLeaf('o', TypeSymbol.LETTER), new SymbolLeaf('r', TypeSymbol.LETTER),
                                        new SymbolLeaf('d', TypeSymbol.LETTER))
                                , TypeTextElement.WORD)},
                {"15.0",
                        new CompositionTextElement(
                                Arrays.asList(new SymbolLeaf('1', TypeSymbol.NUMBER), new SymbolLeaf('5', TypeSymbol.NUMBER),

                                        new SymbolLeaf('.', TypeSymbol.PUNCTUATION_MARK), new SymbolLeaf('0', TypeSymbol.NUMBER))
                                , TypeTextElement.WORD)}
        };

    }

    @Test(dataProvider = "word")
    public void handleRequestSymbols(String word, Component expected) {
        Component actual = new SymbolParserHandler().handleRequest(word);
        assertTrue(actual.equals(expected));
    }
}
