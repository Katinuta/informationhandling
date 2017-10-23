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
     //\(?\w*\-*\s*\w*\)?
    public static final String REGEXP_WORD="[a-zA-Z]+";
    public static final String REGEXP_EXPRESSION="\\d+\\+\\d+";


    public WordExpressionParserHandler() {
        parent=new SymbolParserHandler();
    }

    public WordExpressionParserHandler(SymbolParserHandler parent) {
        this.parent = parent;
    }

    @Override
    public ArrayList<Component> handleRequest(String text) {
        CompositionTextElement lexeme=new CompositionTextElement(TypeTextElement.LEXEME);
        text=text.trim();
        if(text.length()==1){
            Character ch= text.charAt(0);
            if(Character.isDigit(ch)){
                lexeme.add(new SymbolLeaf(text,TypeTextElement.NUMBER));
            }else
            if(Character.isLetter(ch)){
                lexeme.add(new SymbolLeaf(text,TypeTextElement.LETTER));

            }
        }else{

            Pattern pattern =Pattern.compile(REGEXP_EXPRESSION);
            Matcher matcher=pattern.matcher(text);
            if(matcher.find()){

                String expression=matcher.group();
                System.out.println(expression);
                Double result=new Client().calculate(new ExpressionParserHandler().parseExpressionToPolishNotation(expression));
                lexeme.add(new SymbolLeaf(result.toString(),
                        TypeTextElement.NUMBER));
                if(text.length()!=expression.length()){
                    int index=expression.length();
                    while(index!=text.length()-1){
                        lexeme.add(new SymbolLeaf(String.valueOf(text.charAt(index)),
                                TypeTextElement.PUNCTUATION_MARK));
                        index++;
                    }

                }
            }
            if(text.startsWith("\"")||text.startsWith("\'")||text.startsWith("\\(")){
                lexeme.add(new SymbolLeaf(String.valueOf(text.charAt(0)),
                        TypeTextElement.PUNCTUATION_MARK));
                text=text.substring(1);
            }
            String word=null;
            Pattern pattern1 =Pattern.compile(REGEXP_WORD);
            Matcher matcher1=pattern1.matcher(text);
            if(matcher1.find()){
                System.out.println(text);
               word=matcher.group();
                lexeme.add(new CompositionTextElement(parent.handleRequest(word),TypeTextElement.WORD));
                if(word.length()!=text.length()){
                    int index=word.length();

                    while(index!=text.length()){
                        lexeme.add(new SymbolLeaf(String.valueOf(text.charAt(index)),
                                TypeTextElement.PUNCTUATION_MARK));
                        index++;
                    }

                }

            }
        }
        return lexeme.getTextElements();
    }
}
