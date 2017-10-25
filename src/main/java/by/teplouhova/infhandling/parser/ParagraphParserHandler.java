package by.teplouhova.infhandling.parser;

import by.teplouhova.infhandling.composite.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParserHandler implements ParserHandler {

    public static final String REGEXP_PARAGRAPH = "[A-Z]{1}.+\\p{Punct}+\\n";
    private ParserHandler parent;


    public ParagraphParserHandler() {
        parent = new SentenceParserHandler();
    }

    public ParagraphParserHandler(SentenceParserHandler parent) {
        this.parent = parent;
    }

    @Override
    public ArrayList<Component> handleRequest(String component) {
        Pattern patternParagraph = Pattern.compile(REGEXP_PARAGRAPH);
        CompositionTextElement text = new CompositionTextElement(TypeTextElement.TEXT);
        Matcher matcher = patternParagraph.matcher(component);

        while (matcher.find()) {
            String paragraph = matcher.group();
            text.add(new CompositionTextElement(parent.handleRequest(paragraph), TypeTextElement.PARAGRAPH));
            text.add(new SymbolLeaf('\n', TypeSymbol.SYMBOL_NEW_PARAGRAPH));

        }

        return text.getTextElements();
    }
}