package by.teplouhova.infhandling.constant;

public class PatternConst {
    public final static String REGEXP_SENTENCE = "[A-Z]{1}.+[\\.\\!\\?]{0,3}";
    public static final String REGEXP_PARAGRAPH = "\\s+[A-Z]{1}.+\\p{Punct}+\\n";
}
