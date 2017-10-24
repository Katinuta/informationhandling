package by.teplouhova.infhandling.chainresponsobility;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.CompositionTextElement;
import by.teplouhova.infhandling.composite.SymbolLeaf;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParserHandler implements ParserHandler {
    private SymbolParserHandler parent;
    public static final String REGEXP_WORD = "[a-zA-Z]+\\-?[a-zA-Z]*";


    public WordParserHandler() {
        parent = new SymbolParserHandler();
    }

    public WordParserHandler(SymbolParserHandler parent) {
        this.parent = parent;
    }

    @Override
    public ArrayList<Component> handleRequest(String text) {
        CompositionTextElement lexeme = new CompositionTextElement(TypeTextElement.LEXEME);
        if (text.length() == 1) {
            Character ch = text.charAt(0);
            if (Character.isDigit(ch)) {
                lexeme.add(new SymbolLeaf(text, TypeTextElement.NUMBER));
            } else if (Character.isLetter(ch)) {
                lexeme.add(new SymbolLeaf(text, TypeTextElement.LETTER));

            }
        } else {

            if (text.startsWith("\"") || text.startsWith("\'") || text.startsWith("\\(")) {
                lexeme.add(new SymbolLeaf(String.valueOf(text.charAt(0)),
                        TypeTextElement.PUNCTUATION_MARK));
                text = text.substring(1);
            }

            Pattern pattern = Pattern.compile(REGEXP_WORD);
            Matcher matcher = pattern.matcher(text);

            if (matcher.find()) {
                String word = matcher.group();
                lexeme.add(new CompositionTextElement(parent.handleRequest(word), TypeTextElement.WORD));
                ArrayList<Component> punctuationList = new PunctuationHandler().getPunctuationMarks(text, word);

                if (punctuationList != null) {
                    punctuationList.stream().forEach(component -> lexeme.add(component));
                }

            }
        }
        return lexeme.getTextElements();
    }
}
