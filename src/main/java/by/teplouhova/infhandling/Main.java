package by.teplouhova.infhandling;

import by.teplouhova.infhandling.action.TextAction;
import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;
import by.teplouhova.infhandling.parser.impl.ExpressionParserHandler;
import by.teplouhova.infhandling.parser.impl.ParagraphParserHandler;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){

             //   CompositionTextElement d =new CompositionTextElement(TypeTextElement.TEXT) ;
       //d.add(new ParagraphParserHandler().handleRequest("\tD ((3- 1) + 5++)*2, main fsjd sdfhj main. Ziduhfsid."+"\n"+ "\tGFJUFUYJFG fsd d gggf."+"\n"));
        CompositionTextElement d= (CompositionTextElement) new ParagraphParserHandler().
                handleRequest("\tD ((3- 1) + 5++)*2, main fsjd sdfhj main. Ziduhfsid."+"\n"+ "\tGFJUFUYJFG fsd d gggf."+"\n");

//System.out.print(d.toString());
        //System.out.print(d.toString());
//        Pattern pattern= Pattern.compile(ExpressionParserHandler.REGEXP_EXPRESSION);
//        Matcher matcher=pattern.matcher("( (3- 1) + 5++)");
//        if(matcher.find()){
//            System.out.println(true);
//        }
       System.out.println(new TextAction().calculateSentenceWithSameWords(d));
        int i=0;
     // d.getIterator().forEachRemaining(component -> System.out.println(":  "+ component) );

//        while(iterator.hasNext()){
//            iterator.
//            i++;
//            System.out.println(i +":  "+ iterator.next());
//        }
    }
}
