package by.teplouhova.infhandling.parser.impl;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;
import by.teplouhova.infhandling.constant.PatternConst;
import by.teplouhova.infhandling.parser.ParserHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParserHandler implements ParserHandler {


    private ParserHandler parent;

    public SentenceParserHandler() {
        parent = new LexemeParserHandler();
    }

    @Override
    public Component handleRequest(String text) {
        Pattern patternSentence = Pattern.compile(PatternConst.REGEXP_SENTENCE);
        Matcher matcher = patternSentence.matcher(text);
        CompositionTextElement paragraph = new CompositionTextElement(TypeTextElement.PARAGRAPH);

        while (matcher.find()) {
            String sentence = matcher.group();
        //  System.out.println(sentence);
            paragraph.add(parent.handleRequest(sentence));
        }
     //   System.out.println(paragraph.getTextElements());
        return paragraph;
    }


}

