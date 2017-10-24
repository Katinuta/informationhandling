package by.teplouhova.infhandling;

import by.teplouhova.infhandling.chainresponsobility.*;
import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.CompositionTextElement;
import by.teplouhova.infhandling.composite.SymbolLeaf;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        CompositionTextElement component=new CompositionTextElement(
        new ParagraphParserHandler().handleRequest("D ghvfdihg hgdfghdjf. Ziduhfsid+\\n+ GFJUFUYJFG fsd d gggf."), TypeTextElement.TEXT);
        ArrayList<Component> arrayList=new ArrayList<>();
       arrayList.add( new SymbolLeaf("s"));
        arrayList.add(new SymbolLeaf("t"));
        arrayList.add(new SymbolLeaf("r"));
        arrayList.add(new SymbolLeaf("i"));
        arrayList.add(new SymbolLeaf("n"));
        arrayList.add(new SymbolLeaf("g"));
    // System.out.println( new ParagraphParserHandler().handleRequest("D ghvfdihg hgdfghdjf. Ziduhfsid."+"\n"+ "GFJUFUYJFG fsd d gggf."+"\n"));
      //  System.out.println( new SentenceParserHandler().handleRequest("D ghvf - dihg hgdfghdjf."));
       Component d=new CompositionTextElement( new ParagraphParserHandler().handleRequest("D 6+5, hgdfghdjf. Ziduhfsid."+"\n"+ "GFJUFUYJFG fsd d gggf."+"\n"),TypeTextElement.TEXT) ;
        System.out.print(d.toString());
    }
}
