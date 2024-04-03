# Lab Report 5: Putting it All Together
### Affaan Mustafa - CSE15LW24 - Prof. Joe Politz

---
### Part 1: Debugging Scenario
**Title:** Help Needed: AssertionErrors When Running JUnit Tests for ProcessNames

**Body:**

Hello everyone,

I'm currently tackling a lab assignment where the goal is to process names from a file, adjust them to a specific case, and then write the modified names into another file. I've written some JUnit tests to confirm that my code works correctly, but when I run the tests, I get `AssertionError`s for my test cases. Here is the terminal output when I execute my tests:

![JUnit Test Failures](https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/865f0263-3261-4de0-834e-e6157722f951)

It seems like there's a logical error with the way my program is processing the names, especially with case sensitivity and possibly with the file writing as well. Can someone help me pinpoint the exact problem?

Thank you for your help!

**Response from a TA**

Hello,

It seems the tests are failing due to `AssertionError`, which means the output from your program doesn't match the expected output in your test cases. Let's first confirm if the content of `outputTest.txt` matches the expected arrays of strings in your test methods.

Could you also provide the content of your input files and the expected content for `outputTest.txt` as per your tests? This will help us understand what the output should be and why the assertions might be failing.

Thanks!

**Follow-up Post by the Student**

Hi,

Thank you for the prompt response! Here is the content of my input and output files and the expected results based on the test cases:

- Content of `inputAllUpperCase.txt`:
  ```
  ADAM
  JOHN
  BREANA
  JOSHUA
  RITA
  ```
  - Expected Output (testAllUpperCase): `["ADAM", "BREANA", "JOHN", "JOSHUA", "RITA"]`
  - Actual Output: Mismatching, as the actual output contains names that are not present in the input file【22†source】.

- Content of `inputMixedCase.txt`:
  ```
  Alice
  bob
  Charlie
  david
  Eve
  Frank
  affaan
  Bianca
  ```
  - Expected Output (testMixedCase): `["Alice", "bob", "Charlie", "david", "Eve", "Frank"]`
  - Actual Output: The output seems to include only uppercase names and is sorted in a case-sensitive way, not considering lowercase names【23†source】.

Here is the excerpt from my `ProcessNames.java` where I suspect the logical errors are:
```java
// Bug 1: Only names starting with an uppercase letter are processed
if (!name.isEmpty() && Character.isUpperCase(name.charAt(0))) {
    names.add(name);
}

// Bug 2: Case-sensitive sorting which can cause unexpected order with mixed case names
Collections.sort(names);
```
【25†source】

And here is the `ProcessNamesTest.java` for the tests:
```java
// The test for all uppercase input seems to fail due to a mismatch in expected output
assertTrue(checkFileContent(basePath + "outputTest.txt", new String[]{"ADAM", "BREANA", "JOHN", "JOSHUA", "RITA"}));

// This test for mixed case also fails, potentially due to ignoring lowercase names and case-sensitive sorting
assertTrue(checkFileContent("outputTest.txt", new String[]{"Alice", "bob", "Charlie", "david", "Eve", "Frank"}));
```
【26†source】

Also, here's the `process.sh` bash script I use to compile and run the tests:
```bash
#!/bin/bash

# Compile the test class
javac -cp .:junit-4.13.2.jar ProcessNamesTest.java
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
else
    echo "Compilation successful."
fi

# Run the tests
java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore ProcessNamesTest
if [ $? -ne 0 ]; then
    echo "Tests failed."
    exit 1
else
    echo "All tests passed successfully."
fi
```
【24†source】

From this, it looks like I need to modify my `ProcessNames.java` to process all names regardless of case and implement a case-insensitive sort. Additionally, I should make sure the assertions in my tests accurately reflect the desired output considering the content of the input files.

Best,  
A Student Seeking Clarity

**Response from a TA**

Hi

,

Thank you for providing the additional information. Based on what you've shared, I can see two main issues in your `ProcessNames.java` code that need to be addressed:

1. **Case Sensitivity in Name Processing:** Currently, your program only processes names that start with an uppercase letter. To include names that start with a lowercase letter, you'll need to remove the `Character.isUpperCase(name.charAt(0))` check.

2. **Case-Sensitive Sorting:** Your program sorts the names in a case-sensitive manner, which is not desired based on your test cases. To sort the names case-insensitively, you can use a `Comparator` that ignores case.

Here's how you could modify the `ProcessNames.java`:

**Original Excerpt:**
```java
// Bug 1: Only names starting with an uppercase letter are processed
if (!name.isEmpty() && Character.isUpperCase(name.charAt(0))) {
    names.add(name);
}

// Bug 2: Case-sensitive sorting which can cause unexpected order with mixed case names
Collections.sort(names);
```

**Updated `ProcessNames.java`:**
```java
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
```

For your test cases, make sure the expected results match what your `processFile` method is supposed to output. If the method is intended to include all names regardless of the case and sort them case-insensitively, then your expected results in the test cases should reflect this.

**Updated `ProcessNamesTest.java`:**
```java
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
```

In the `testMixedCase` method, I've updated the `expectedOutput` to include all names in a case-insensitive sorted order. Also, notice that I have used `equalsIgnoreCase` in `checkFileContent` to ensure we check the contents correctly.

Try these modifications, and if everything is correct, your tests should now pass.

Best,  
TA 

**Follow-up Post by the Student**

Hello,

I followed the instructions provided and made the suggested code changes to both `ProcessNames.java` and `ProcessNamesTest.java`. After running the bash script to compile and test my updated code, I'm thrilled to report that all tests have passed successfully!

Here's the terminal output confirming the successful test execution:

![All tests passed successfully](https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/d2d93294-75d3-4eec-bc16-6501ea435431)

This has been an invaluable learning experience, not only in debugging and writing unit tests but also in understanding the importance of considering case sensitivity during string manipulation and sorting.

Thank you so much for the help!

Best,  
A Grateful Student

# Lab Report 5: Debugging Process Recap

---

As we reached the culmination of our debugging efforts, we reflected on the journey. Here's a comprehensive overview of the debugging process, starting with the file structure, progressing through the code within the files, detailing the commands executed, and finally, pinpointing the exact code changes that addressed the bug.

## File & Directory Structure

The project was organized as follows:

```plaintext
LABREPORT/
├── src/
│   └── ProcessNames.java
├── test/
│   └── ProcessNamesTest.java
├── data/
│   ├── inputAllUpperCase.txt
│   └── inputMixedCase.txt
└── process.sh
```

The `outputTest.txt` file was generated post-processing.

## Contents of Files Before the Bug Fix

### ProcessNames.java (Pre-Fix)

```java
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
            if (!name.isEmpty() && Character.isUpperCase(name.charAt(0))) {
                names.add(name);
            }
        }
        in.close();

        Collections.sort(names);

        PrintWriter out = new PrintWriter(new FileWriter(outputFilePath));
        for (String name : names) {
            out.println(name);
        }
        out.close();
    }
}
```

### ProcessNamesTest.java (Pre-Fix)

```java
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;

public class ProcessNamesTest {

    @Test
    public void testAllUpperCase() throws IOException {
        ProcessNames.processFile("inputAllUpperCase.txt", "outputTest.txt");
        assertTrue(checkFileContent("outputTest.txt", new String[]{"Alice", "Charlie", "Eve", "Frank"}));
    }

    @Test
    public void testMixedCase() throws IOException {
        ProcessNames.processFile("inputMixedCase.txt", "outputTest.txt");
        assertTrue(checkFileContent("outputTest.txt", new String[]{"Alice", "bob", "Charlie", "david", "Eve", "Frank"}));
    }

    private boolean checkFileContent(String filePath, String[] expectedLines) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            if (i >= expectedLines.length || !line.equals(expectedLines[i])) {
                reader.close();
                return false;
            }
            i++;
        }
        reader.close();
        return i == expectedLines.length;
    }
}
```

### Input Files

**inputAllUpperCase.txt**

```plaintext
ADAM
JOHN
BREANA
JOSHUA
RITA
```

**inputMixedCase.txt**

```plaintext
Alice
bob
Charlie
david
Eve
Frank
affaan
Bianca
```

### Process Script (process.sh)

```bash
#!/bin/bash

# Compile the test class
javac -cp .:junit-4.13.2.jar ProcessNamesTest.java
# Check for compilation success
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
fi

# Run the tests
java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore ProcessNamesTest
# Check test outcomes
if [ $? -ne 0 ]; then
    echo "Tests failed."
    exit 1
fi

echo "All tests passed successfully."
```

## Commands Executed to Trigger the Bug

The bug manifested when the bash script was executed with:

```shell
bash process.sh
```

## Resolving the Bug

Edits to rectify the issues included:

1. **In ProcessNames.java:**
   - Removed `Character.isUpperCase(name.charAt(0))` to account for names irrespective of case.
   - Implemented `Collections.sort(names, String.CASE_INSENSITIVE_ORDER)` for case-insensitive sorting.

2. **In ProcessNamesTest.java:**
   - Corrected the expected output arrays to reflect the inclusive and case-insensitive logic.
   - Tweaked `checkFileContent` to compare strings case-insensitively using `equalsIgnoreCase`.

Post-modification, the tests executed successfully, marking the conclusion of a methodical and instructive debugging session.

### Part 2 – Reflection

During the latter part of this quarter, I was able to take the skills honed in CSE15L and apply them to an entirely different domain—economic research. Working as a research assistant at the San Diego Supercomputer Center, I found myself immersed in the high-performance computing environment. Here, the terminal wasn't just a tool; it was my gateway to leverage the power of cluster computing.

I used bash scripts extensively, not for academic exercises, but to handle real-world data processing tasks. The ability to write optimized scripts for sbatch submission has been a game-changer, allowing me to process large datasets with efficiency that would be unattainable on a standard computer. My workflow often involved using vim to edit these scripts, a skill that at first seemed arcane, but now allows me to navigate and modify files with a speed I couldn't have imagined before.

The simple commands like cat, sorting data, and output redirection, which we practiced in labs, turned out to be the building blocks for complex data processing tasks. This experience has not only reinforced my technical knowledge but has also provided a clear illustration of how these skills transfer across disciplines, empowering me to contribute meaningfully to research that extends far beyond my initial academic setting.
