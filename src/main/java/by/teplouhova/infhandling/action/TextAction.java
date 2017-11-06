package by.teplouhova.infhandling.action;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;

import java.util.ArrayList;
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
         //   System.out.println(text);

                 }
        return text;
    }

    public int calculateSentenceWithSameWords(CompositionTextElement component){
        int countSentence=0;
        if(component.getTypeTextElement().equals(TypeTextElement.TEXT)){
            ArrayList<Component> listParagraphs=getListElements(component);
            ArrayList<Component> listSentence=new ArrayList<>();
            listParagraphs.stream().forEach(paragraph->listSentence.addAll(getListElements((CompositionTextElement) paragraph)));
            for (Component sentence:listSentence ) {
                ArrayList<Component> listLexemes=getListElements((CompositionTextElement) sentence);
                ArrayList<Component> listWords=new ArrayList<>();
                        listLexemes.stream().forEach(lexeme-> listWords.addAll(getListElements((CompositionTextElement) lexeme)));
                System.out.println(listWords);
                ArrayList<String> words=new ArrayList<>();
                for (Component word:listWords
                     ) {
                    words.add(word.toString().trim());
                }
                System.out.println(words);
                for(int index=0;index<listWords.size()-1;index++){
                    String word=listWords.get(index).toString().trim();
                //    System.out.println(word.countComponent());
                    int indexInput=words.lastIndexOf(word);
                   // System.out.println(indexInput);
                    if(indexInput>index&&indexInput!=-1){
                        countSentence++;
                    }
                }
            }
            
           // System.out.println(listSentence);


        }
        return countSentence;
    }

    public boolean isSentenceContainSameWords(CompositionTextElement sentence){
       // System.out.println(sentence);
        Iterator<Component> lexemeIterator=sentence.getIterator();
        ArrayList<Component> listWordsInSentence=new ArrayList<>();
        while(lexemeIterator.hasNext()){
            CompositionTextElement lexeme=(CompositionTextElement) lexemeIterator.next();
            Iterator<Component>  wordIterator=lexeme.getIterator();
            while(wordIterator.hasNext()){
                listWordsInSentence.add(wordIterator.next());
            }
        }
        for(int index=0;index<listWordsInSentence.size();index++){

        }

        return false;
    }

    public ArrayList<Component> getListElements(CompositionTextElement component){
        Iterator<Component> componentIterator=component.getIterator();
        ArrayList<Component> listElements=new ArrayList<>();
        while (componentIterator.hasNext()){
            Component element=componentIterator.next();

            if(element.countComponent()>1){

                listElements.add(element);
            }
        }
        return  listElements;
    }
}
