// A simple file reader that reads chat transcripts from the chathistory folder

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ChatHistoryReader {

    /**
     * Reads the contents of a text file and returns them as an array of strings.
     *
     * @param filePath the path to the text file.
     * @return an array of strings, each representing a line in the file.
     * @throws IOException if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read.
     */
    public String[] readFileAsArray(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        return lines.toArray(new String[0]);
    }

    public static void main(String[] args) {
        ChatHistoryReader reader = new ChatHistoryReader();
        try {
            String[] contents = reader.readFileAsArray(args[0]);
            for (String line : contents) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
