package by.teplouhova.infhandling.chainresponsobility;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.CompositionTextElement;
import by.teplouhova.infhandling.composite.SymbolLeaf;
import by.teplouhova.infhandling.interpreter.Client;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExpressionParserHandler implements ParserHandler{

    public static final String REGEXP_EXPRESSION="\\d+\\+\\d+";
    private HashMap<String,Integer> prededence;


    public ExpressionParserHandler() {
        prededence= new HashMap<>();
        prededence.put("(",0);
        prededence.put("+",2);

        prededence.put("-",2);

        prededence.put("*",3);
        prededence.put("/",3);
    }

    public String parseExpressionToPolishNotation(String expression){
        List<String> operatorsAndNumbers=parseExpressionToArray(expression);

        Queue<String> polishNotation=new LinkedList<>();
        ArrayDeque<String> stackOperation=new ArrayDeque<>();
        for (String token:operatorsAndNumbers ) {
            if(isNumber(token)){
                polishNotation.add(token);
                continue;
            }
            if("(".equals(token)){
                stackOperation.push(token);
                continue;
            }
            if(")".equals(token)){
                while(!"(".equals(stackOperation.peek())){
                    polishNotation.add(stackOperation.pop());
                }
                stackOperation.pop();
                continue;
            }
            if(prededence.containsKey(token)){

                while(!stackOperation.isEmpty()&&prededence.get(stackOperation.peek())>=prededence.get(token)){

                    polishNotation.add(stackOperation.pop());
                }
                stackOperation.push(token);
            }

        }

        while(!stackOperation.isEmpty()){
            polishNotation.add(stackOperation.pop());
        }
        StringBuffer stringPolishNotation= new StringBuffer();
        for (String f:polishNotation ) {
            stringPolishNotation.append(f).append(" ");
        }

        return stringPolishNotation.toString();
    }

    private static boolean  isNumber(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public List<String> parseExpressionToArray(String expression){
        List<String> operatorsAndNumbers=new ArrayList<>();

        for (int index=0;index<expression.length();index++){
            String symbol=String.valueOf(expression.charAt(index));
            if("+".equals(symbol)||"-".equals(symbol)||isNumber(symbol)&&index>=1){
                String lastSymbol=operatorsAndNumbers.get(operatorsAndNumbers.size()-1);
                if(lastSymbol.equals(symbol)||isNumber(lastSymbol)&&isNumber(symbol)){
                    operatorsAndNumbers.set(operatorsAndNumbers.size()-1,lastSymbol+symbol);
                    continue;
                }
            }
            operatorsAndNumbers.add(String.valueOf(symbol));
        }

        while(operatorsAndNumbers.contains("++")||operatorsAndNumbers.contains("--")){

            String operation= operatorsAndNumbers.contains("++")?"++":"--";
            int index=operatorsAndNumbers.indexOf(operation);
            if(isNumber(operatorsAndNumbers.get(index-1))){
                    operatorsAndNumbers.set(index,operation.substring(1));
                    operatorsAndNumbers.add(index+1,"1");
            }else{
                    operatorsAndNumbers.remove(operation);
                    operatorsAndNumbers.add(index+1,operation.substring(1));
                    operatorsAndNumbers.add(index+2,"1");
            }
        }

        operatorsAndNumbers=operatorsAndNumbers.stream().filter(s->!s.equals(" ")).collect(Collectors.toList());

        return operatorsAndNumbers;
    }

    @Override
    public ArrayList<Component> handleRequest(String text) {
        CompositionTextElement lexeme=new CompositionTextElement(TypeTextElement.LEXEME);
        Pattern pattern =Pattern.compile(REGEXP_EXPRESSION);
        Matcher matcher=pattern.matcher(text);
        if(matcher.find()){

            String expression=matcher.group();
            Double result=new Client().calculate(parseExpressionToPolishNotation(expression));
            lexeme.add(new SymbolLeaf(result.toString(),
                    TypeTextElement.NUMBER));

            if(text.length()!=expression.length()){
                int index=expression.length();
                while(index!=text.length()){
                    lexeme.add(new SymbolLeaf(String.valueOf(text.charAt(index)),
                            TypeTextElement.PUNCTUATION_MARK));
                    index++;
                }

            }
        }
        return lexeme.getTextElements();
    }
}
