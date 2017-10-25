package by.teplouhova.infhandling.composite;

public class SymbolLeaf implements Component {
    private Character symbol;
    private TypeSymbol type;

    public SymbolLeaf(Character symbol) {
        this.symbol = symbol;

    }
    public SymbolLeaf(Character symbol,TypeSymbol type) {
        this.symbol = symbol;
        this.type=type;
    }


    @Override
    public int countComponent() {
        return 1;
    }

    @Override
    public String toString() {
        return symbol.toString();
    }
}
