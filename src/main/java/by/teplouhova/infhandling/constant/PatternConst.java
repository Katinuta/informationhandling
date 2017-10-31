package by.teplouhova.infhandling.constant;

public class PatternConst {
    public final static String REGEXP_SENTENCE = "[A-Z]{1}[\\w\\+\\-\\*\\/,\\(\\)\\s]+[\\.\\!\\?]{0,3}";
    public static final String REGEXP_PARAGRAPH = "\\s+[A-Z]{1}.+\\p{Punct}+\\n";
    public static final String REGEXP_LEXEME = "\\s*(([A-z]+\\-*[A-z]*)|([\\d\\+\\-\\*\\/\\(\\)\\s]{3,}))\\p{Punct}*\\s*";
    public static final String REGEXP_EXPRESSION = "[\\d\\+\\-\\*\\/\\(\\)\\s]{3,}";
}
