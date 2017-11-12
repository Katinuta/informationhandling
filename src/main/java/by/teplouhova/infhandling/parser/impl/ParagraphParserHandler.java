package by.teplouhova.infhandling.parser.impl;

import by.teplouhova.infhandling.composite.*;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.SymbolLeaf;
import by.teplouhova.infhandling.composite.impl.TypeSymbol;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;
import by.teplouhova.infhandling.constant.PatternConstant;
import by.teplouhova.infhandling.constant.SymbolConstant;
import by.teplouhova.infhandling.parser.ParserHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParserHandler implements ParserHandler {


    private ParserHandler parent;


    public ParagraphParserHandler() {
        parent = new SentenceParserHandler();
    }

    public ParagraphParserHandler(SentenceParserHandler parent) {
        this.parent = parent;
    }

    @Override
    public Component handleRequest(String component) {
        Pattern patternParagraph = Pattern.compile(PatternConstant.REGEXP_PARAGRAPH);
        CompositionTextElement text = new CompositionTextElement(TypeTextElement.TEXT);
        Matcher matcher = patternParagraph.matcher(component);

        while (matcher.find()) {
            String paragraph = matcher.group();
            text.add(new SymbolLeaf(SymbolConstant.TAB, TypeSymbol.SYMBOL_TAB));
            text.add(parent.handleRequest(paragraph));
            text.add(new SymbolLeaf(SymbolConstant.NEW_STRING, TypeSymbol.SYMBOL_NEW_STRING));
        }

        return text;
    }
}
