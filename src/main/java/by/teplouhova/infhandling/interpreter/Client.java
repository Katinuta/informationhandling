package by.teplouhova.infhandling.interpreter;

import by.teplouhova.infhandling.chainresponsobility.ExpressionParserHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class Client {
    private Context context;

    public Client() {
        context = new Context();
    }

    public Double calculate(String expression) {
        //System.out.println(expression);
        Arrays.asList(expression.split(" ")).stream().forEach(token->{
            AbstractMathExpression action;
            switch (token) {
                case "+": {
                    action = (Context c) -> {
                        c.push(c.pop()+c.pop());
                    };
                    break;
                }
                case "-": {
                    action = (Context c) -> {
                        double d1=c.pop();
                        double d2;
                        if(c.peek()==null){
                            d2 = 0;
                        }else{
                            d2=c.pop();
                        }
                        c.push(d2 - d1);
                    };
                    break;
                }
                case "*": {
                    action = (Context c) -> {
                        c.push(c.pop() * c.pop());
                    };
                    break;
                }
                case "/": {
                    action = (Context c) -> {
                        double d1=c.pop();
                        double d2=c.pop();
                        c.push(d2 / d1);
                    };
                    break;
                }
                default:{
                    action=(Context c)->{
                        c.push(Double.valueOf(token));
                    };
                }
            }
            action.interpret(context);
        });

        return context.pop();
    }

}
