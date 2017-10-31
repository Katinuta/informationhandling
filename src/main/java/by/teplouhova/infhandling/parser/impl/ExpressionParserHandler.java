package by.teplouhova.infhandling.parser.impl;

import by.teplouhova.infhandling.composite.*;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;
import by.teplouhova.infhandling.constant.MathOperationConst;
import by.teplouhova.infhandling.constant.PatternConst;
import by.teplouhova.infhandling.constant.SymbolConstant;
import by.teplouhova.infhandling.interpreter.impl.Client;
import by.teplouhova.infhandling.parser.ParserHandler;
import by.teplouhova.infhandling.parser.PunctuationHandler;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExpressionParserHandler implements ParserHandler {


    private HashMap<String, Integer> prededence;
    private ParserHandler parent;


    public ExpressionParserHandler() {
        parent=new SymbolParserHandler();
        prededence = new HashMap<>();
        prededence.put(SymbolConstant.OPEN_BRACKET, 0);//"("
        prededence.put(MathOperationConst.OPERATION_PLUS, 2);//"+"

        prededence.put(MathOperationConst.OPERATION_MINUS, 2);//"-"

        prededence.put(MathOperationConst.OPERATION_MULTI, 3);//"*"
        prededence.put(MathOperationConst.OPERATION_DIVIDE, 3);//"/"
    }

    public String parseExpressionToPolishNotation(String expression) {
        List<String> operatorsAndNumbers = parseExpressionToArray(expression);

        Queue<String> polishNotation = new LinkedList<>();
        ArrayDeque<String> stackOperation = new ArrayDeque<>();
        for (String token : operatorsAndNumbers) {
            if (isNumber(token)) {
                polishNotation.add(token);
                continue;
            }
            if (SymbolConstant.OPEN_BRACKET.equals(token)) {
                stackOperation.push(token);
                continue;
            }
            if (SymbolConstant.CLOSE_BRACKET.equals(token)) {
                while (!SymbolConstant.OPEN_BRACKET.equals(stackOperation.peek())) {
                    polishNotation.add(stackOperation.pop());
                }
                stackOperation.pop();
                continue;
            }
            if (prededence.containsKey(token)) {

                while (!stackOperation.isEmpty() && prededence.get(stackOperation.peek()) >= prededence.get(token)) {

                    polishNotation.add(stackOperation.pop());
                }
                stackOperation.push(token);
            }

        }

        while (!stackOperation.isEmpty()) {
            polishNotation.add(stackOperation.pop());
        }
        StringBuilder stringPolishNotation = new StringBuilder();
        for (String f : polishNotation) {
            stringPolishNotation.append(f).append(" ");
        }

        return stringPolishNotation.toString();
    }

    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public List<String> parseExpressionToArray(String expression) {
        List<String> operatorsAndNumbers = new ArrayList<>();

        for (int index = 0; index < expression.length(); index++) {
            String symbol = String.valueOf(expression.charAt(index));
            if (MathOperationConst.OPERATION_PLUS.equals(symbol) ||
                    MathOperationConst.OPERATION_MINUS.equals(symbol) || isNumber(symbol) && index >= 1) {
                String lastSymbol = operatorsAndNumbers.get(operatorsAndNumbers.size() - 1);
                if (lastSymbol.equals(symbol) || isNumber(lastSymbol) && isNumber(symbol)) {
                    operatorsAndNumbers.set(operatorsAndNumbers.size() - 1, lastSymbol + symbol);
                    continue;
                }
            }
            operatorsAndNumbers.add(String.valueOf(symbol));
        }

        while (operatorsAndNumbers.contains(MathOperationConst.OPERATION_DOUBLE_PLUS) ||
                operatorsAndNumbers.contains(MathOperationConst.OPERATION_DOUBLE_MINUS)) {
            String operation =
                    operatorsAndNumbers.contains(MathOperationConst.OPERATION_DOUBLE_PLUS)? MathOperationConst.OPERATION_DOUBLE_PLUS :
                            MathOperationConst.OPERATION_DOUBLE_MINUS;
            int index = operatorsAndNumbers.indexOf(operation);
            if (isNumber(operatorsAndNumbers.get(index - 1))) {
                operatorsAndNumbers.set(index, operation.substring(1));
                operatorsAndNumbers.add(index + 1, "1");
            } else {
                operatorsAndNumbers.remove(operation);
                operatorsAndNumbers.add(index + 1, operation.substring(1));
                operatorsAndNumbers.add(index + 2, "1");
            }
        }

        operatorsAndNumbers = operatorsAndNumbers.stream().filter(s -> !s.equals(" ")).collect(Collectors.toList());
        return operatorsAndNumbers;
    }

    @Override
    public Component handleRequest(String text) {
        CompositionTextElement lexeme = new CompositionTextElement(TypeTextElement.LEXEME);
        Pattern pattern = Pattern.compile(PatternConst.REGEXP_EXPRESSION);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {

            String expression = matcher.group();
            Double result = new Client().calculate(parseExpressionToPolishNotation(expression));
            lexeme.add(parent.handleRequest(result.toString()));
         //   System.out.println( result);
            ArrayList<Component> punctuationList = new PunctuationHandler().getPunctuationMarks(text, expression);
            if (punctuationList != null) {
                punctuationList.stream().forEach(component -> lexeme.add(component));
            }

        }
        return lexeme;
    }
}
