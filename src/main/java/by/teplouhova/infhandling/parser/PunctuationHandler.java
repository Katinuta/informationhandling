package by.teplouhova.infhandling.parser;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.SymbolLeaf;
import by.teplouhova.infhandling.composite.TypeSymbol;
import by.teplouhova.infhandling.composite.TypeTextElement;

import java.util.ArrayList;

public class PunctuationHandler {

    public ArrayList<Component> getPunctuationMarks(String text, String coincidence){
        ArrayList<Component> punctuationList = null;
        if(text.length()!=coincidence.length()){
            punctuationList= new ArrayList<>();
            int index=coincidence.length();
            while(index!=text.length()){
                punctuationList.add(new SymbolLeaf(text.charAt(index),
                        TypeSymbol.PUNCTUATION_MARK));
                index++;
            }
        }
        return punctuationList;
    }
}
