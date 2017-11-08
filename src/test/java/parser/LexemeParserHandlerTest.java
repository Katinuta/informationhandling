package parser;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.SymbolLeaf;
import by.teplouhova.infhandling.composite.impl.TypeSymbol;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;
import by.teplouhova.infhandling.parser.impl.LexemeParserHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LexemeParserHandlerTest {
    @DataProvider(name = "lexeme")
    public Object[][] createData() {
        return new Object[][]{
                {
                        "Hello - word.", new CompositionTextElement(Arrays.asList(
                        new CompositionTextElement(
                                Arrays.asList(new CompositionTextElement(Arrays.asList(
                                        new SymbolLeaf('H', TypeSymbol.LETTER),
                                        new SymbolLeaf('e', TypeSymbol.LETTER),
                                        new SymbolLeaf('l', TypeSymbol.LETTER),
                                        new SymbolLeaf('l', TypeSymbol.LETTER),
                                        new SymbolLeaf('o', TypeSymbol.LETTER)

                                ), TypeTextElement.WORD)), TypeTextElement.LEXEME),
                        new SymbolLeaf('-', TypeSymbol.PUNCTUATION_MARK),
                        new CompositionTextElement(
                                Arrays.asList(new CompositionTextElement(Arrays.asList(
                                        new SymbolLeaf('w', TypeSymbol.LETTER),
                                        new SymbolLeaf('o', TypeSymbol.LETTER),
                                        new SymbolLeaf('r', TypeSymbol.LETTER),
                                        new SymbolLeaf('d', TypeSymbol.LETTER)

                                ), TypeTextElement.WORD), new SymbolLeaf('.', TypeSymbol.PUNCTUATION_MARK)),
                                TypeTextElement.LEXEME)
                ), TypeTextElement.SENTENCE)
                },
                {
                        "It is a (5 +2++)/2!", new CompositionTextElement(
                        Arrays.asList(
                                new CompositionTextElement(
                                        Arrays.asList(new CompositionTextElement(Arrays.asList(
                                                new SymbolLeaf('I', TypeSymbol.LETTER),
                                                new SymbolLeaf('t', TypeSymbol.LETTER)

                                        ), TypeTextElement.WORD)), TypeTextElement.LEXEME),
                                new CompositionTextElement(
                                        Arrays.asList(new CompositionTextElement(Arrays.asList(
                                                new SymbolLeaf('i', TypeSymbol.LETTER),
                                                new SymbolLeaf('s', TypeSymbol.LETTER)
                                        ), TypeTextElement.WORD)), TypeTextElement.LEXEME),
                                new CompositionTextElement(Arrays.asList(
                                        new SymbolLeaf('a', TypeSymbol.LETTER)),
                                        TypeTextElement.LEXEME),
                                new CompositionTextElement(Arrays.asList(
                                        new CompositionTextElement(Arrays.asList(
                                                new SymbolLeaf('4', TypeSymbol.NUMBER),
                                                new SymbolLeaf('.',
                                                        TypeSymbol.PUNCTUATION_MARK),
                                                new SymbolLeaf('0', TypeSymbol.NUMBER)), TypeTextElement.WORD),
                                        new SymbolLeaf('!', TypeSymbol.PUNCTUATION_MARK))
                                        , TypeTextElement.LEXEME)
                        )
                        , TypeTextElement.SENTENCE)
                }
        };
    }

    @Test(dataProvider = "lexeme")
    public void lexemeHandleRequest(String sentence, Component expected) {
        Component actual = new LexemeParserHandler().handleRequest(sentence);
        assertEquals(actual, expected);
    }
}
