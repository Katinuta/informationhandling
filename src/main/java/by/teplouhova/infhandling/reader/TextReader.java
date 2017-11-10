package by.teplouhova.infhandling.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextReader {

    private static final Logger LOGGER = LogManager.getLogger();

    public String textFromFileReader(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String text = null;
        try {
            Stream<String> readerStream = Files.lines(Paths.get(fileName));
            readerStream.forEach(line -> stringBuilder.append(line).append("\n"));
            text = stringBuilder.toString();
            if (text.isEmpty()) {
                LOGGER.log(Level.FATAL, "File " + fileName + "is empty");
                throw new RuntimeException("File " + fileName + "is empty");
            }
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "File is not found " + fileName);
            throw new RuntimeException("File is not found " + fileName, e);
        }
        return text;
    }
}
