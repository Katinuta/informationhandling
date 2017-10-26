package by.teplouhova.infhandling.parser;

import by.teplouhova.infhandling.composite.*;
import by.teplouhova.infhandling.constant.SymbolConstant;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParserHandler implements ParserHandler {

    public static final String REGEXP_PARAGRAPH = "\\s+[A-Z]{1}.+\\p{Punct}+\\n";
    private ParserHandler parent;


    public ParagraphParserHandler() {
        parent = new SentenceParserHandler();
    }

    public ParagraphParserHandler(SentenceParserHandler parent) {
        this.parent = parent;
    }

    @Override
    public Component handleRequest(String component) {
        Pattern patternParagraph = Pattern.compile(REGEXP_PARAGRAPH);
        CompositionTextElement text = new CompositionTextElement(TypeTextElement.TEXT);
        Matcher matcher = patternParagraph.matcher(component);

        while (matcher.find()) {
            String paragraph = matcher.group();
          //  System.out.println(paragraph);
            text.add(new SymbolLeaf(SymbolConstant.TAB, TypeSymbol.SYMBOL_TAB));
            text.add(parent.handleRequest(paragraph));
            text.add(new SymbolLeaf(SymbolConstant.NEW_STRING, TypeSymbol.SYMBOL_NEW_STRING));

        }

        return text;
    }
}
