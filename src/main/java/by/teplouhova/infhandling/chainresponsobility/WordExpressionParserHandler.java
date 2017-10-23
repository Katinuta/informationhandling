package by.teplouhova.infhandling.chainresponsobility;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.CompositionTextElement;
import by.teplouhova.infhandling.composite.SymbolLeaf;
import by.teplouhova.infhandling.interpreter.Client;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordExpressionParserHandler implements ParserHandler {
    private SymbolParserHandler parent;
    public static final String REGEXP_WORD="\\w+\\(?\\w*\\-*\\s*\\w*\\)?";
    public static final String REGEXP_EXPRESSION="[()\\d+\\+\\-*/]{1,}";

    @Override
    public ArrayList<Component> handleRequest(String text) {
        CompositionTextElement lexeme=new CompositionTextElement(TypeTextElement.LEXEME);
        text.trim();
        if(text.length()==1){
            Character ch= text.charAt(0);
            if(Character.isDigit(ch)){
                lexeme.add(new SymbolLeaf(text,TypeTextElement.NUMBER));
            }else
            if(Character.isLetter(ch)){
                lexeme.add(new SymbolLeaf(text,TypeTextElement.LETTER));

            }

        }else{

            if(Pattern.compile(REGEXP_EXPRESSION).matcher(text).find()){
                String expression=Pattern.compile(REGEXP_EXPRESSION).matcher(text).group();
                Double result=new Client().calculate(new ExpressionParserHandler().parseExpressionToPolishNotation(expression));
                lexeme.add(new SymbolLeaf(result.toString(),
                        TypeTextElement.NUMBER));
                if(text.length()!=expression.length()){
                    int index=expression.length();
                    do{
                        lexeme.add(new SymbolLeaf(String.valueOf(text.charAt(index)),
                                TypeTextElement.PUNCTUATION_MARK));
                        index++;
                    }
                    while(index!=text.length()-1);
                }
            }
            if(text.startsWith("\"")||text.startsWith("\'")||text.startsWith("\\(")){
                lexeme.add(new SymbolLeaf(String.valueOf(text.charAt(0)),
                        TypeTextElement.PUNCTUATION_MARK));
                text=text.substring(1);
            }
            String word=Pattern.compile(REGEXP_WORD).matcher(text).group();
            lexeme.add(new CompositionTextElement(parent.handleRequest(word),TypeTextElement.WORD));
            if(word.length()!=text.length()){
                int index=word.length();
                do{
                    lexeme.add(new SymbolLeaf(String.valueOf(text.charAt(index)),
                            TypeTextElement.PUNCTUATION_MARK));
                    index++;
                }
                while(index!=text.length()-1);
            }


        }

        return lexeme.getTextElements();
    }
}
