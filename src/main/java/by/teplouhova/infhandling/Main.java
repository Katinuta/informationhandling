package by.teplouhova.infhandling;

import by.teplouhova.infhandling.chainresponsobility.ExpressionParserHandler;
import by.teplouhova.infhandling.chainresponsobility.ParagraphParserHandler;
import by.teplouhova.infhandling.interpreter.AbstractMathExpression;
import by.teplouhova.infhandling.interpreter.Client;
import by.teplouhova.infhandling.interpreter.Context;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){

//        String[] str="( - 5 + 1 / 2 * 2 * ( 2 + 5 * 2 - 1 ) ) * 1200".split(" ");
//        Client client=new Client();
//       // System.out.println(client.calculate(new ExpressionParserHandler().parseExpressionToPolishNotation(str)));
//        System.out.println(new ExpressionParserHandler().parseExpressionToArray("(5+ 3++)*2"));
        String input = "ThisIsATest";
//from  w  w w .  j  av a 2  s.c  o m
        // split into words
        String[] words = input.split("(?=[A-Z])");

        words[0] = capitalizeFirstLetter(words[0]);
        Arrays.asList(words).stream().forEach(str->System.out.println(str));
        // join
        StringBuilder builder = new StringBuilder();
        for (String s : words) {
            builder.append(s).append(" ");
        }

        System.out.println(builder.toString());

    }

    private static String capitalizeFirstLetter(String in) {
        return in.substring(0, 1).toUpperCase() + in.substring(1);
    }

}
