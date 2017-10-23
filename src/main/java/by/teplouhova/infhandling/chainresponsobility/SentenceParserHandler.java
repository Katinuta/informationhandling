package by.teplouhova.infhandling.chainresponsobility;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.CompositionTextElement;
import by.teplouhova.infhandling.composite.SymbolLeaf;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParserHandler implements ParserHandler {


    private LexemeParserHandler parent;
    public final static String REGEXP_SENTENCE=".+\\.";

    public SentenceParserHandler() {
       parent=new LexemeParserHandler();
    }

    @Override
    public ArrayList<Component> handleRequest(String text) {
        Pattern patternSentence=Pattern.compile(REGEXP_SENTENCE);
        String sentence;
        Matcher matcher=patternSentence.matcher(text);
        CompositionTextElement paragraph= new CompositionTextElement(TypeTextElement.PARAGRAPH);
        while(matcher.find()){
            sentence=matcher.group();
         // System.out.println(sentence);
                           paragraph.add(new CompositionTextElement(parent.handleRequest(sentence),TypeTextElement.SENTENCE));
            }
        return paragraph.getTextElements();
        }


    }

