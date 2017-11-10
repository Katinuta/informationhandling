package parser;

import by.teplouhova.shape.reader.PyramidDataReader;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;


/**
 * Created by SMIT_Creator on 12-Sep-17.
 */

public class TextReaderTest {
    private List<String> expectedList;
    private PyramidDataReader dataReader;
    private File fileNew;
    private File fileEmpty;

    @BeforeClass()
    public void before() {
        fileNew = new File("data/wrongfile.txt");
        fileEmpty = new File("data/nodata.txt");
        try (FileWriter fileWriter = new FileWriter(fileNew)) {
            fileNew.createNewFile();
            fileEmpty.createNewFile();
            fileWriter.write("string\n" + "string\n" + "string");

        } catch (IOException e) {
            throw new RuntimeException("File not found " + e);
        }
        expectedList = Arrays.asList("string", "string", "string");
        dataReader = new PyramidDataReader();
    }

    @Test
    public void readDataFromFileTest() {

        List<String> actualList = dataReader.readDataFromFile(fileNew.getPath());
        assertEquals(actualList, expectedList);

    }

    @Test(expectedExceptions = RuntimeException.class)
    public void readDataFromFileException() {
        dataReader.readDataFromFile("data.txt");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void noDataInFileException() {
        dataReader.readDataFromFile(fileEmpty.getPath());
    }

    @AfterClass
    public void after() {
        fileNew.delete();
        fileEmpty.delete();
        dataReader = null;
    }

}
