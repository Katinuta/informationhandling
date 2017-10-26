package by.teplouhova.infhandling.parser;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.CompositionTextElement;
import by.teplouhova.infhandling.composite.TypeTextElement;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParserHandler implements ParserHandler {

    public final static String REGEXP_SENTENCE = "[A-Z]{1}.+[\\.\\!\\?]{0,3}";
    private ParserHandler parent;

    public SentenceParserHandler() {
        parent = new LexemeParserHandler();
    }

    @Override
    public Component handleRequest(String text) {
        Pattern patternSentence = Pattern.compile(REGEXP_SENTENCE);
        Matcher matcher = patternSentence.matcher(text);
        CompositionTextElement paragraph = new CompositionTextElement(TypeTextElement.PARAGRAPH);

        while (matcher.find()) {
            String sentence = matcher.group();
         //  System.out.println(sentence);
            paragraph.add(parent.handleRequest(sentence));
        }

        return paragraph;
    }


}

