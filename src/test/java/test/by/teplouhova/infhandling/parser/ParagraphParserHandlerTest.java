package test.by.teplouhova.infhandling.parser;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.SymbolLeaf;
import by.teplouhova.infhandling.composite.impl.TypeSymbol;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;
import by.teplouhova.infhandling.parser.impl.ParagraphParserHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class ParagraphParserHandlerTest {
    @DataProvider(name="paragraphs")
    public Object[][] createData(){
        return new Object[][]{
                {
                        "\tHello word. Hello word.\n", new CompositionTextElement(Arrays.asList(
                                new SymbolLeaf('\t', TypeSymbol.SYMBOL_TAB),
                            new CompositionTextElement(Arrays.asList(
                                    new CompositionTextElement(Arrays.asList(
                                            new CompositionTextElement(
                                                    Arrays.asList(new CompositionTextElement(Arrays.asList(
                                                            new SymbolLeaf('H', TypeSymbol.LETTER),
                                                            new SymbolLeaf('e', TypeSymbol.LETTER),
                                                            new SymbolLeaf('l', TypeSymbol.LETTER),
                                                            new SymbolLeaf('l', TypeSymbol.LETTER),
                                                            new SymbolLeaf('o', TypeSymbol.LETTER)

                                                    ), TypeTextElement.WORD)), TypeTextElement.LEXEME),

                                            new CompositionTextElement(
                                                    Arrays.asList(new CompositionTextElement(Arrays.asList(
                                                            new SymbolLeaf('w', TypeSymbol.LETTER),
                                                            new SymbolLeaf('o', TypeSymbol.LETTER),
                                                            new SymbolLeaf('r', TypeSymbol.LETTER),
                                                            new SymbolLeaf('d', TypeSymbol.LETTER)

                                                    ), TypeTextElement.WORD), new SymbolLeaf('.', TypeSymbol.PUNCTUATION_MARK)),
                                                    TypeTextElement.LEXEME)
                                    ), TypeTextElement.SENTENCE),
                                    new CompositionTextElement(Arrays.asList(
                                            new CompositionTextElement(
                                                    Arrays.asList(new CompositionTextElement(Arrays.asList(
                                                            new SymbolLeaf('H', TypeSymbol.LETTER),
                                                            new SymbolLeaf('e', TypeSymbol.LETTER),
                                                            new SymbolLeaf('l', TypeSymbol.LETTER),
                                                            new SymbolLeaf('l', TypeSymbol.LETTER),
                                                            new SymbolLeaf('o', TypeSymbol.LETTER)

                                                    ), TypeTextElement.WORD)), TypeTextElement.LEXEME),

                                            new CompositionTextElement(
                                                    Arrays.asList(new CompositionTextElement(Arrays.asList(
                                                            new SymbolLeaf('w', TypeSymbol.LETTER),
                                                            new SymbolLeaf('o', TypeSymbol.LETTER),
                                                            new SymbolLeaf('r', TypeSymbol.LETTER),
                                                            new SymbolLeaf('d', TypeSymbol.LETTER)

                                                    ), TypeTextElement.WORD), new SymbolLeaf('.', TypeSymbol.PUNCTUATION_MARK)),
                                                    TypeTextElement.LEXEME)
                                    ), TypeTextElement.SENTENCE)
                            ), TypeTextElement.PARAGRAPH),
                        new SymbolLeaf('\n', TypeSymbol.SYMBOL_NEW_STRING)

                    ), TypeTextElement.TEXT)
                }
        };
    }

    @Test(dataProvider = "paragraphs")
    public void paragraphHandlerRequestTest(String text, Component expected){
       Component actual=  new ParagraphParserHandler().handleRequest(text);
       assertEquals(actual,expected);
    }
}
