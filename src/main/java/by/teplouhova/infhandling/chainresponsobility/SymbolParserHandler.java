package by.teplouhova.infhandling.chainresponsobility;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.CompositionTextElement;
import by.teplouhova.infhandling.composite.SymbolLeaf;

import java.util.ArrayList;

public class SymbolParserHandler implements ParserHandler{
    @Override
    public ArrayList<Component> handleRequest(String text) {
        CompositionTextElement word=new CompositionTextElement(TypeTextElement.WORD);

        for(int index=0;index<text.length();index++) {
            char symbol=text.charAt(index);
            if(Character.isLetter(symbol)){
                word.add(new SymbolLeaf(String.valueOf(symbol),TypeTextElement.LETTER));
            }else{
                word.add(new SymbolLeaf(String.valueOf(symbol),TypeTextElement.PUNCTUATION_MARK));
            }
        }

        return word.getTextElements();
    }
}
