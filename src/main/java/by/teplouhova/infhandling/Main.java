package by.teplouhova.infhandling;

import by.teplouhova.infhandling.parser.*;
import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.CompositionTextElement;
import by.teplouhova.infhandling.composite.SymbolLeaf;
import by.teplouhova.infhandling.composite.TypeTextElement;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){

               CompositionTextElement d =new CompositionTextElement(TypeTextElement.TEXT) ;
        d.add(new ParagraphParserHandler().handleRequest("\tD 6+5, hgdfghdjf. Ziduhfsid."+"\n"+ "\tGFJUFUYJFG fsd d gggf."+"\n"));
        System.out.print(d.toString());
//        Pattern pattern=Pattern.compile("[A-Z]{1}(\\s*\\w+\\s*)+[\\.\\!\\?]{0,3}");
//        Matcher matcher=pattern.matcher("D hgdfghdjf. Ziduhfsid."+"\n");
//
//        while(matcher.find()){
//            String sen=matcher.group();
//                System.out.println(sen);
//
//        }

    }
}
