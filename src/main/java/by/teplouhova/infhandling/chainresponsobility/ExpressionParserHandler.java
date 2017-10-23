package by.teplouhova.infhandling.chainresponsobility;

import java.util.*;
import java.util.stream.Collectors;

public class ExpressionParserHandler {

    public String parseExpressionToPolishNotation(String expression){
        List<String> operatorsAndNumbers=parseExpressionToArray(expression);
        HashMap<String,Integer> prededence= new HashMap<>();
        prededence.put("(",0);
        prededence.put("+",2);

        prededence.put("-",2);

        prededence.put("*",3);
        prededence.put("/",3);
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
                continue;
            }
//            if("++".equals(token)||"--".equals(token)){
//                polishNotation.add("1");
//                stackOperation.push(token.substring(1));
//            }

        }

        while(!stackOperation.isEmpty()){
            polishNotation.add(stackOperation.pop());
        }
        StringBuffer d= new StringBuffer();
        for (String f:polishNotation ) {
            d.append(f).append(" ");
        }

        return d.toString();
    }

    public static boolean  isNumber(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public List<String> parseExpressionToArray(String expression){
        List<String> operatorsAndNumbers=new ArrayList<>();
        //System.out.println(expression);
        for (int index=0;index<expression.length();index++){
            String symbol=String.valueOf(expression.charAt(index));
            if("+".equals(symbol)||"-".equals(symbol)||isNumber(symbol)){
                String lastSymbol=operatorsAndNumbers.get(operatorsAndNumbers.size()-1);
                if(lastSymbol.equals(symbol)||isNumber(lastSymbol)&&isNumber(symbol)){
                    operatorsAndNumbers.set(operatorsAndNumbers.size()-1,lastSymbol+symbol);
                    continue;
                }
            }
            operatorsAndNumbers.add(String.valueOf(symbol));
        }
        while(operatorsAndNumbers.contains("++")||operatorsAndNumbers.contains("--")){
            int index;
            if(operatorsAndNumbers.contains("++")){
                index=operatorsAndNumbers.indexOf("++");
                if(isNumber(operatorsAndNumbers.get(index-1))){
                    operatorsAndNumbers.set(index,"+");
                    operatorsAndNumbers.add(index+1,"1");
                }else{
                    operatorsAndNumbers.remove("++");
                    operatorsAndNumbers.add(index+1,"+");
                    operatorsAndNumbers.add(index+2,"1");
                }
            }
            if(operatorsAndNumbers.contains("--")){
                index=operatorsAndNumbers.indexOf("--");
                if(isNumber(operatorsAndNumbers.get(index-1))){
                    operatorsAndNumbers.set(index,"-");
                    operatorsAndNumbers.add(index+1,"1");
                }else{
                    operatorsAndNumbers.remove("--");
                    operatorsAndNumbers.add(index+1,"-");
                    operatorsAndNumbers.add(index+2,"1");
                }
            }
        }


        operatorsAndNumbers=operatorsAndNumbers.stream().filter(s->!s.equals(" ")).collect(Collectors.toList());
        return operatorsAndNumbers;
    }
}
