package by.teplouhova.infhandling.interpreter;

import java.util.ArrayDeque;

public class Context {
    private ArrayDeque<Double> contextValue;

    public Context() {
        contextValue=new ArrayDeque<>();
    }

    public Double pop(){
        return contextValue.pop();
    }

    public void push(Double number){
        contextValue.push(number);
    }

    public Double peek(){
        return contextValue.peek();
    }

    @Override
    public String toString() {
        return "Context{" +
                "contextValue=" + contextValue +
                '}';
    }
}
