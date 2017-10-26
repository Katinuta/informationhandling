package by.teplouhova.infhandling.parser.impl;

import by.teplouhova.infhandling.composite.*;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.SymbolLeaf;
import by.teplouhova.infhandling.composite.impl.TypeSymbol;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;
import by.teplouhova.infhandling.parser.ParserHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParserHandler implements ParserHandler {

    public static final String REGEXP_LEXEME = "\\s*\\w+[\\p{Punct}|[\\+\\-]]{0,2}\\w*\\p{Punct}*\\s*";
  // public static final String REGEXP_LEXEME ="\\s*(\\w+\\-?[a-zA-Z]*)?(\\d\\+\\d)?\\p{Punct}*\\s*";
    private ParserHandler parent;

    public LexemeParserHandler() {

    }

    public LexemeParserHandler(WordParserHandler wordParserHandler) {
        this.parent = wordParserHandler;
    }

    @Override
    public Component handleRequest(String text) {
        CompositionTextElement sentence = new CompositionTextElement(TypeTextElement.SENTENCE);
        Pattern pattern = Pattern.compile(REGEXP_LEXEME);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {

            String lexeme = matcher.group();
            String symbol = lexeme.trim();

            if (symbol.length() == 1 && !Character.isLetter(symbol.charAt(0)) && !Character.isDigit(symbol.charAt(0))) {

                sentence.add(new SymbolLeaf(symbol.charAt(0), TypeSymbol.PUNCTUATION_MARK));
            } else if (lexeme.length() != 0) {
                if (Pattern.compile(ExpressionParserHandler.REGEXP_EXPRESSION).matcher(symbol).find()) {
                    parent = new ExpressionParserHandler();

                } else {
                    parent = new WordParserHandler();
                }
                sentence.add(parent.handleRequest(lexeme.trim()));

            }
        }
        return sentence;
    }
}
