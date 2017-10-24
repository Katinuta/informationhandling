package by.teplouhova.infhandling.chainresponsobility;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.CompositionTextElement;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParserHandler implements ParserHandler {

    public final static String REGEXP_SENTENCE = ".+\\.";
    private LexemeParserHandler parent;

    public SentenceParserHandler() {
        parent = new LexemeParserHandler();
    }

    @Override
    public ArrayList<Component> handleRequest(String text) {
        Pattern patternSentence = Pattern.compile(REGEXP_SENTENCE);
        Matcher matcher = patternSentence.matcher(text);
        CompositionTextElement paragraph = new CompositionTextElement(TypeTextElement.PARAGRAPH);

        while (matcher.find()) {
            String sentence = matcher.group();
            paragraph.add(new CompositionTextElement(parent.handleRequest(sentence), TypeTextElement.SENTENCE));
        }

        return paragraph.getTextElements();
    }


}

