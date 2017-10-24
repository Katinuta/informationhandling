package by.teplouhova.infhandling.chainresponsobility;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.CompositionTextElement;
import by.teplouhova.infhandling.composite.SymbolLeaf;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParserHandler implements ParserHandler {

    private SentenceParserHandler parent;
    public static final String REGEXP_PARAGRAPH = "[A-Z]{1}.+\\n";

    public ParagraphParserHandler() {
        parent = new SentenceParserHandler();
    }

    public ParagraphParserHandler(SentenceParserHandler perent) {
        this.parent = perent;
    }

    @Override
    public ArrayList<Component> handleRequest(String component) {
        Pattern patternParagraph = Pattern.compile(REGEXP_PARAGRAPH);
        String paragraph;
        CompositionTextElement text = new CompositionTextElement(TypeTextElement.TEXT);
        Matcher matcher = patternParagraph.matcher(component);
        while (matcher.find()) {
            paragraph = matcher.group();

            text.add(new CompositionTextElement(parent.handleRequest(paragraph), TypeTextElement.PARAGRAPH));
            text.add(new SymbolLeaf("\n", TypeTextElement.SYMBOL_NEW_PARAGRAPH));

        }
        return text.getTextElements();
    }
}
