package by.teplouhova.infhandling.interpreter;

public enum MathOperation {

    MINUS("-");
    private String value;
    private MathOperation(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
