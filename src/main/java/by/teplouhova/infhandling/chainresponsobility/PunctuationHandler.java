package by.teplouhova.infhandling.chainresponsobility;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.SymbolLeaf;

import java.util.ArrayList;

public class PunctuationHandler {

    public ArrayList<Component> getPunctuationMarks(String text, String coincidence){
        ArrayList<Component> punctuationList = null;
        if(text.length()!=coincidence.length()){
            punctuationList= new ArrayList<>();
            int index=coincidence.length();
            while(index!=text.length()){
                punctuationList.add(new SymbolLeaf(String.valueOf(text.charAt(index)),
                        TypeTextElement.PUNCTUATION_MARK));
                index++;
            }

        }
        return punctuationList;
    }
}
