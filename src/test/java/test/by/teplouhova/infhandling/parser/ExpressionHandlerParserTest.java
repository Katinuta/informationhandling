package test.by.teplouhova.infhandling.parser;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.SymbolLeaf;
import by.teplouhova.infhandling.composite.impl.TypeSymbol;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;
import by.teplouhova.infhandling.parser.impl.ExpressionParserHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

public class ExpressionHandlerParserTest {

    private ExpressionParserHandler parserHandler;

    @BeforeClass
    public void before() {
        parserHandler = new ExpressionParserHandler();
    }

    @DataProvider(name = "expression")
    public Object[][] createData() {
        return new Object[][]{
                {
                        "(5+ 3++)*2", new CompositionTextElement(Arrays.asList(
                        new CompositionTextElement(Arrays.asList(
                                new SymbolLeaf('1', TypeSymbol.NUMBER),
                                new SymbolLeaf('8', TypeSymbol.NUMBER),
                                new SymbolLeaf('.', TypeSymbol.PUNCTUATION_MARK),
                                new SymbolLeaf('0', TypeSymbol.NUMBER)), TypeTextElement.WORD))
                        , TypeTextElement.LEXEME
                )
                },
                {
                        "( 9/ --3)+ 2--", new CompositionTextElement(Arrays.asList(
                        new CompositionTextElement(Arrays.asList(new SymbolLeaf('3', TypeSymbol.NUMBER),
                                new SymbolLeaf('.', TypeSymbol.PUNCTUATION_MARK),
                                new SymbolLeaf('0', TypeSymbol.NUMBER)), TypeTextElement.WORD))
                        , TypeTextElement.LEXEME
                )
                },
                {
                        "((3- 1) + 5++)*2.", new CompositionTextElement(Arrays.asList(
                        new CompositionTextElement(Arrays.asList(
                                new SymbolLeaf('1', TypeSymbol.NUMBER),
                                new SymbolLeaf('6', TypeSymbol.NUMBER),
                                new SymbolLeaf('.', TypeSymbol.PUNCTUATION_MARK),
                                new SymbolLeaf('0', TypeSymbol.NUMBER)), TypeTextElement.WORD),
                        new SymbolLeaf('.', TypeSymbol.PUNCTUATION_MARK))
                        , TypeTextElement.LEXEME)
                }
        };
    }

    @Test(dataProvider = "expression")
    public void parseExpressionToArrayTest(String expression, Component expected) {
        Component actual = parserHandler.handleRequest(expression);
        assertTrue(actual.equals(expected));
    }


    @AfterClass
    public void after() {
        parserHandler = null;
    }
}
