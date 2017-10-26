package by.teplouhova.infhandling;

import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;
import by.teplouhova.infhandling.parser.impl.ParagraphParserHandler;

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
