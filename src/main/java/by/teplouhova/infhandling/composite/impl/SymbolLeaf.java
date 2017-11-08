package by.teplouhova.infhandling.composite.impl;

import by.teplouhova.infhandling.composite.Component;

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

    public TypeSymbol getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SymbolLeaf that = (SymbolLeaf) o;

        if (symbol != null ? !symbol.equals(that.symbol) : that.symbol != null) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
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
