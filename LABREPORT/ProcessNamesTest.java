import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;

public class ProcessNamesTest {
    
    // Base path for the files
    private static final String basePath = "/Users/affoon/Documents/GitHub/LABREPORT/";
    
    @Test
    public void testAllUpperCase() throws IOException {
        String inputPath = basePath + "inputAllUpperCase.txt";
        String outputPath = basePath + "outputTest.txt";
        ProcessNames.processFile(inputPath, outputPath);
        String[] expectedOutput = {"ADAM", "BREANA", "JOHN", "JOSHUA", "RITA"};
        assertTrue(checkFileContent(outputPath, expectedOutput));
    }

    @Test
    public void testMixedCase() throws IOException {
        String inputPath = basePath + "inputMixedCase.txt";
        String outputPath = basePath + "outputTest.txt";
        ProcessNames.processFile(inputPath, outputPath);
        String[] expectedOutput = {"affaan", "Alice", "Bianca", "bob", "Charlie", "david", "Eve", "Frank"};
        assertTrue(checkFileContent(outputPath, expectedOutput));
    }

    private boolean checkFileContent(String filePath, String[] expectedLines) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            if (i >= expectedLines.length || !line.equalsIgnoreCase(expectedLines[i])) {
                reader.close();
                return false;
            }
            i++;
        }
        reader.close();
        return i == expectedLines.length;
    }
}