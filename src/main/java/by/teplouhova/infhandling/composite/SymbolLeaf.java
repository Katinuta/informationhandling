package by.teplouhova.infhandling.composite;

import by.teplouhova.infhandling.chainresponsobility.TypeTextElement;

public class SymbolLeaf implements Component {
    private String symbol;
    private TypeTextElement typeTextElement;

    public SymbolLeaf(String symbol,TypeTextElement typeTextElement) {
        this.symbol = symbol;
        this.typeTextElement=typeTextElement;
    }


    @Override
    public int countComponent() {
        return 1;
    }

    @Override
    public String toString() {
        return "SymbolLeaf{" +
                "symbol='" + symbol + '\'' +
                '}';
    }
}
