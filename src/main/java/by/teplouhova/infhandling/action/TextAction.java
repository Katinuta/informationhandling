package by.teplouhova.infhandling.action;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAction {

    public static final String REGEXP_EXPRESSION_WITH_I_J = "[\\d\\+\\-\\*\\/\\(\\)\\sij]{3,}";

    public String insertValueToText(String text, int i, int j) {
        Pattern pattern = Pattern.compile(TextAction.REGEXP_EXPRESSION_WITH_I_J);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String fromText = matcher.group();
            String newText = fromText.replace("i", String.valueOf(i)).replace("j", String.valueOf(j));
            text = text.replace(fromText, newText);

        }
        return text;
    }

    public int calculateSentenceWithSameWords(CompositionTextElement component) {
        int countSentence = 0;
        if (component.getTypeTextElement().equals(TypeTextElement.TEXT)) {
            ArrayList<Component> listParagraphs = getListElements(component);
            ArrayList<Component> listSentence = new ArrayList<>();
            listParagraphs.stream().forEach(paragraph -> listSentence.addAll(getListElements((CompositionTextElement) paragraph)));
            for (Component sentence : listSentence) {
                if (isSentenceContainSameWords((CompositionTextElement) sentence)) {
                    countSentence++;
                }
            }


        }
        return countSentence;
    }

    public boolean isSentenceContainSameWords(CompositionTextElement sentence) {
        ArrayList<Component> listLexemes = getListElements(sentence);
        ArrayList<Component> listWords = new ArrayList<>();
        listLexemes.stream().forEach(lexeme -> listWords.addAll(getListElements((CompositionTextElement) lexeme)));
        ArrayList<String> words = new ArrayList<>();
        listWords.stream().forEach(word -> words.add(word.toString().trim().toLowerCase()));

        for (int index = 0; index < listWords.size() - 1; index++) {
            String word = listWords.get(index).toString().trim();
            int indexInput = words.lastIndexOf(word.toLowerCase());
            if (indexInput > index && indexInput != -1) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Component> getListElements(Component component) {
        ArrayList<Component> listElements = null;
        if (component.countComponent() > 1) {
            listElements = new ArrayList<>();
            Iterator<Component> componentIterator = ((CompositionTextElement) component).getIterator();

            while (componentIterator.hasNext()) {
                Component element = componentIterator.next();

                if (element.countComponent() > 1) {
                    listElements.add(element);
                }
            }
        }

        return listElements;
    }


    public String changeFirstLastLexeme(CompositionTextElement text) {

        Iterator<Component> paragraphIterator = text.getIterator();
        while (paragraphIterator.hasNext()) {
            Component component = paragraphIterator.next();
            if (component.countComponent() > 1) {
                CompositionTextElement paragraph = (CompositionTextElement) component;
                Iterator<Component> sentenceIterator = paragraph.getIterator();
                while (sentenceIterator.hasNext()) {

                    CompositionTextElement sentence = (CompositionTextElement) sentenceIterator.next();
                    Component firstLexeme = sentence.get(0);
                    Component lastLexeme = sentence.get(sentence.getSizeTextElement() - 1);
                    sentence.set(0, lastLexeme);
                    sentence.set(sentence.getSizeTextElement() - 1, firstLexeme);
                }
            }

        }
        return text.toString();
    }

    public String orderByLexemeCount(CompositionTextElement text) {

        String result = new String();
        ArrayList<CompositionTextElement> listSentences = new ArrayList<>();
        Iterator<Component> paragraphIterator = text.getIterator();
        while (paragraphIterator.hasNext()) {
            Component component = paragraphIterator.next();
            if (component.countComponent() > 1) {
                CompositionTextElement paragraph = (CompositionTextElement) component;
                Iterator<Component> sentenceIterator = paragraph.getIterator();
                while (sentenceIterator.hasNext()) {
                    listSentences.add((CompositionTextElement) sentenceIterator.next());
                }

            }
        }
        listSentences.sort(Comparator.comparing(CompositionTextElement::getSizeTextElement));
        for (CompositionTextElement sentence : listSentences) {
            result += sentence + "\n";
        }
        return result;
    }
}