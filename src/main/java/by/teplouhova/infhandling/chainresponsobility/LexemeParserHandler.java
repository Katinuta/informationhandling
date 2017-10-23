package by.teplouhova.infhandling.chainresponsobility;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.CompositionTextElement;
import by.teplouhova.infhandling.composite.SymbolLeaf;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParserHandler implements ParserHandler {

    private WordExpressionParserHandler parent;
    public static final String REGEXP_LEXEME="\\s*\\w*[\\p{Punct}{0,3}|[\\+]{0,2}]\\w*\\s*";

    public LexemeParserHandler() {
       parent=new WordExpressionParserHandler();
    }

    public LexemeParserHandler(WordExpressionParserHandler wordParserHandler) {
        this.parent = wordParserHandler;
    }

    @Override
    public ArrayList<Component> handleRequest(String text) {
        CompositionTextElement sentence=new CompositionTextElement(TypeTextElement.SENTENCE);
        Pattern pattern=Pattern.compile(REGEXP_LEXEME);
        Matcher matcher=pattern.matcher(text);
        String lexeme;
      //System.out.println(text);
        while(matcher.find()){

            lexeme=matcher.group();
            String symbol=lexeme.trim();

            if(symbol.length()==1&&!Character.isLetter(symbol.charAt(0))&&!Character.isDigit(symbol.charAt(0))){

                sentence.add(new SymbolLeaf(symbol,TypeTextElement.PUNCTUATION_MARK));
            }else if(lexeme.length()!=0){
             sentence.add(new CompositionTextElement(parent.handleRequest(lexeme),TypeTextElement.LEXEME));

//                sentence.add(new SymbolLeaf(lexeme,TypeTextElement.LEXEME));
            }
        }
        return sentence.getTextElements();
    }
}
