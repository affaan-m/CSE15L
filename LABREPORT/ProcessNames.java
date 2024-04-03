import java.io.*;
import java.util.*;

public class ProcessNames {
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            processFile(args[0], args[1]);
        } else {
            System.out.println("Please provide input and output file paths.");
        }
    }
    
    public static void processFile(String inputFilePath, String outputFilePath) throws IOException {
        Scanner in = new Scanner(new File(inputFilePath));
        List<String> names = new ArrayList<>();
        
        while (in.hasNext()) {
            String name = in.nextLine().trim();
            // Fixed Bug 1: Process all non-empty names
            if (!name.isEmpty()) {
                names.add(name);
            }
        }
        in.close();
        
        // Fixed Bug 2: Sort names case-insensitively
        Collections.sort(names, String.CASE_INSENSITIVE_ORDER);
        
        PrintWriter out = new PrintWriter(new FileWriter(outputFilePath));
        for (String name : names) {
            out.println(name);
        }
        out.close(); 
    }
}