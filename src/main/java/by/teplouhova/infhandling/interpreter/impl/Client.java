package by.teplouhova.infhandling.interpreter.impl;

import by.teplouhova.infhandling.constant.MathOperationConstant;
import by.teplouhova.infhandling.interpreter.AbstractMathExpression;
import by.teplouhova.infhandling.interpreter.Context;

import java.util.Arrays;

public class Client {
    private Context context;

    public Client() {
        context = new Context();
    }

    public Double calculate(String expression) {
        Arrays.asList(expression.split(" ")).forEach(token -> {
            AbstractMathExpression action;
            switch (token) {
                case MathOperationConstant.OPERATION_PLUS: {
                    action = (c) -> c.push(c.pop() + c.pop());
                    break;
                }
                case MathOperationConstant.OPERATION_MINUS: {
                    action = (c) -> {
                        double d1 = c.pop();
                        double d2;
                        if (c.peek() == null) {
                            d2 = 0;
                        } else {
                            d2 = c.pop();
                        }
                        c.push(d2 - d1);
                    };
                    break;
                }
                case MathOperationConstant.OPERATION_MULTI: {
                    action = (c) -> c.push(c.pop() * c.pop());
                    break;
                }
                case MathOperationConstant.OPERATION_DIVIDE: {
                    action = (Context c) -> {
                        double d1 = c.pop();
                        double d2 = c.pop();
                        c.push(d2 / d1);
                    };
                    break;
                }
                default: {
                    action = (c) -> c.push(Double.valueOf(token));

                }
            }
            action.interpret(context);
        });

        return context.pop();
    }

}
