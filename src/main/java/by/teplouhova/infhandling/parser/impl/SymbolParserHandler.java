package by.teplouhova.infhandling.parser.impl;

import by.teplouhova.infhandling.composite.*;
import by.teplouhova.infhandling.composite.impl.CompositionTextElement;
import by.teplouhova.infhandling.composite.impl.SymbolLeaf;
import by.teplouhova.infhandling.composite.impl.TypeSymbol;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;
import by.teplouhova.infhandling.parser.ParserHandler;

public class SymbolParserHandler implements ParserHandler {
    @Override
    public Component handleRequest(String text) {
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

        return word;
    }
}
