package test.by.teplouhova.infhandling.reader;

import by.teplouhova.infhandling.reader.TextReader;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.testng.Assert.assertEquals;


/**
 * Created by SMIT_Creator on 12-Sep-17.
 */

@SuppressWarnings("SpellCheckingInspection")
public class TextReaderTest {
    private String expected;
    private TextReader reader;
    private File fileNew;
    private File fileEmpty;

    @BeforeClass()
    public void before() {
        fileNew = new File("data/wrongfile.txt");
        fileEmpty = new File("data/nodata.txt");
        try (FileWriter fileWriter = new FileWriter(fileNew)) {
            fileNew.createNewFile();
            fileEmpty.createNewFile();
            fileWriter.write("\tstring\n" + "string\n" + "string");

        } catch (IOException e) {
            throw new RuntimeException("File not found " + e);
        }
        expected = "\tstring\n" + "string\n" + "string\n";
        reader = new TextReader();
    }

    @Test
    public void readDataFromFileTest() {

        String actual = reader.textFromFileReader(fileNew.getPath());
        assertEquals(actual, expected);

    }

    @Test(expectedExceptions = RuntimeException.class)
    public void readDataFromFileException() {
        reader.textFromFileReader("data.txt");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void noDataInFileException() {
        reader.textFromFileReader(fileEmpty.getPath());
    }

    @AfterClass
    public void after() {
        fileNew.delete();
        fileEmpty.delete();
        reader = null;
    }

}
