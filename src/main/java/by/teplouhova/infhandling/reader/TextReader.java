package by.teplouhova.infhandling.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextReader {

    private final static Logger LOGGER= LogManager.getLogger();

    public String TextReader(String fileName){
        StringBuilder stringBuilder=new StringBuilder();
        String text=null;
        try {
            Stream<String> readerStream= Files.lines(Paths.get(fileName));
           readerStream.forEach(line->stringBuilder.append(line));
           text=stringBuilder.toString();
           if(text.isEmpty()){
               LOGGER.log(Level.FATAL,"File is empty");
               throw new RuntimeException("File is empty");
           }
        } catch (IOException e) {
            LOGGER.log(Level.FATAL,"File is not found");
            throw new RuntimeException("File is not found");
        }
        return text;
    }
}
