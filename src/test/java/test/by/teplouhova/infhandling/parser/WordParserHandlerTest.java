package test.by.teplouhova.infhandling.parser;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.SymbolLeaf;
import by.teplouhova.infhandling.composite.impl.TypeSymbol;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;
import by.teplouhova.infhandling.parser.impl.WordParserHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

public class WordParserHandlerTest {
    @DataProvider(name = "word")
    public Object[][] createData() {
        return new Object[][]{
                {
                        "D",
                        new CompositionTextElement(Arrays.asList(new SymbolLeaf('D', TypeSymbol.LETTER)),
                                TypeTextElement.LEXEME)
                },
                {
                        "5", new CompositionTextElement(Arrays.asList(new SymbolLeaf('5', TypeSymbol.NUMBER)),
                        TypeTextElement.LEXEME)
                },
                {
                        "\"word\"", new CompositionTextElement(Arrays.asList(
                        new SymbolLeaf('\"', TypeSymbol.PUNCTUATION_MARK),
                        new CompositionTextElement(Arrays.asList(
                                new SymbolLeaf('w', TypeSymbol.LETTER),
                                new SymbolLeaf('o', TypeSymbol.LETTER),
                                new SymbolLeaf('r', TypeSymbol.LETTER),
                                new SymbolLeaf('d', TypeSymbol.LETTER)),
                                TypeTextElement.WORD),
                        new SymbolLeaf('\"', TypeSymbol.PUNCTUATION_MARK))
                        , TypeTextElement.LEXEME)
                },
                {
                        "word,", new CompositionTextElement(Arrays.asList(
                        new CompositionTextElement(Arrays.asList(
                                new SymbolLeaf('w', TypeSymbol.LETTER),
                                new SymbolLeaf('o', TypeSymbol.LETTER),
                                new SymbolLeaf('r', TypeSymbol.LETTER),
                                new SymbolLeaf('d', TypeSymbol.LETTER)),
                                TypeTextElement.WORD),
                        new SymbolLeaf(',', TypeSymbol.PUNCTUATION_MARK))
                        , TypeTextElement.LEXEME)
                }
        };
    }

    @Test(dataProvider = "word")
    public void wordHandleRequestTest(String text, Component expected) {
        Component actual = new WordParserHandler().handleRequest(text);
        assertTrue(actual.equals(expected));

    }
}
