package by.teplouhova.infhandling.action;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAction {

    public static final String REGEXP_EXPRESSION = "[\\d\\+\\-\\*\\/\\(\\)\\sij]{3,}";

    public String insertValueToText(String text,int i, int j){
        Pattern pattern= Pattern.compile(TextAction.REGEXP_EXPRESSION);
        Matcher matcher=pattern.matcher(text);
        if(matcher.find()){
            String fromText=matcher.group();
            String newText=fromText.replace("i",String.valueOf(i)).replace("j",String.valueOf(j));
            text= text.replace(fromText,newText);
            System.out.println(text);

                 }
        return text;
    }

    public int calculateSentenceWithSameWords(CompositionTextElement component){
        int countSentence=0;
        if(component.getTypeTextElement().equals(TypeTextElement.TEXT)){
            Iterator<Component> paragraphIterator=component.getIterator();
            while(paragraphIterator.hasNext()){
                CompositionTextElement paragraph=(CompositionTextElement) paragraphIterator.next();
                Iterator<Component> sentenceIterator=paragraph.getIterator();
                while(sentenceIterator.hasNext()){
                    CompositionTextElement sentence=(CompositionTextElement) sentenceIterator.next();
                    Iterator<Component> lexemeIterator=sentence.getIterator();
                    while(lexemeIterator.hasNext()){
                        CompositionTextElement lexeme=(CompositionTextElement) lexemeIterator.next();
                        Iterator<Component>  wordIterator=lexeme.getIterator();
                        String word=null;
                        while(wordIterator.hasNext()){
                            
                        }
                    }
                }
            }
        }
        return countSentence;
    }
}
