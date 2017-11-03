package by.teplouhova.infhandling.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAction {

    public static final String REGEXP_EXPRESSION = "[\\d\\+\\-\\*\\/\\(\\)\\sij]{3,}";

    public String insertValueToText(String text,int i, int j){
        Pattern pattern= Pattern.compile(TextAction.REGEXP_EXPRESSION);
        Matcher matcher=pattern.matcher(text);
        if(matcher.find()){
            String fromText=matcher.group();
            String newText=fromText.replace("i",String.valueOf(i)).replace("j",String.valueOf(j));
            text= text.replace(fromText,newText);
            System.out.println(text);

                 }
        return text;
    }
}
