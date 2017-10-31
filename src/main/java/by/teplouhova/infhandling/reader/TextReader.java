package by.teplouhova.infhandling.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextReader {

    public String TextReader(String fileName){
        StringBuilder stringBuilder=new StringBuilder();
        String text=null;
        try {
            Stream<String> readerStream= Files.lines(Paths.get(fileName));
           readerStream.forEach(line->stringBuilder.append(line));
           text=stringBuilder.toString();
           if(text.isEmpty()){

               throw new RuntimeException("");
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
