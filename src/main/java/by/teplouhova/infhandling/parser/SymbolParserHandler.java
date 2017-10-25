package by.teplouhova.infhandling.parser;

import by.teplouhova.infhandling.composite.*;
import jdk.nashorn.internal.codegen.types.Type;

import java.util.ArrayList;

public class SymbolParserHandler implements ParserHandler{
    @Override
    public ArrayList<Component> handleRequest(String text) {
        CompositionTextElement word=new CompositionTextElement(TypeTextElement.WORD);

        for(int index=0;index<text.length();index++) {
            char symbol=text.charAt(index);
            if(Character.isLetter(symbol)){
                word.add(new SymbolLeaf(symbol, TypeSymbol.LETTER));
            }else if(Character.isLetter(symbol)){
                word.add(new SymbolLeaf(symbol, TypeSymbol.NUMBER));
            }else{
                word.add(new SymbolLeaf(symbol,TypeSymbol.PUNCTUATION_MARK));
            }
        }

        return word.getTextElements();
    }
}
