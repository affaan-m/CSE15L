
# Lab Report 3 - Bugs and Commands (Week 5)

## Part 1 - Bugs

In this section, we will examine a bug from the previous lab and provide the necessary tests, symptoms, and code corrections.

### Failure-Inducing Input

#### `reverseInPlace` Method Test

```java
@Test
public void testReverseInPlaceFailure() {
    int[] input = {1, 2, 3, 4};
    ArrayExamples.reverseInPlace(input);
    assertArrayEquals(new int[]{4, 3, 2, 1}, input);
}
```

#### `reversed` Method Test

```java
@Test
public void testReversedFailure() {
    int[] input = {1, 2, 3, 4};
    int[] result = ArrayExamples.reversed(input);
    assertArrayEquals(new int[]{4, 3, 2, 1}, result);
}
```

### Non-Failure-Inducing Input

Given the nature of the bugs, most inputs will result in a failure. However, an array of length 1 will not:

#### `reverseInPlace` Method Test with Non-Failure Input

```java
@Test 
public void testReverseInPlace() {
  int[] input1 = { 3 };
  ArrayExamples.reverseInPlace(input1);
  assertArrayEquals(new int[]{ 3 }, input1);
}
```

#### `reversed` Method Test with Non-Failure Input

```java
@Test
public void testReversed() {
  int[] input1 = { };
  assertArrayEquals(new int[]{ }, ArrayExamples.reversed(input1));
}
```

### Symptom

#### JUnit Test Output

![JUnit Test Output](https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/b7f0034f-0019-4294-9e42-59d6371c274a)

### The Bug and Fix

#### `reverseInPlace` Method

##### Before Fix

```java
// Buggy implementation of reverseInPlace
static void reverseInPlace(int[] arr) {
  for(int i = 0; i < arr.length; i += 1) {
    arr[i] = arr[arr.length - i - 1];
  }
}
```

##### After Fix

```java
// Corrected implementation of reverseInPlace
static void reverseInPlace(int[] arr) {
  for(int i = 0; i < arr.length / 2; i += 1) {
    int temp = arr[i];
    arr[i] = arr[arr.length - i - 1];
    arr[arr.length - i - 1] = temp;
  }
}
```

#### `reversed` Method

##### Before Fix

```java
// Buggy implementation of reversed
static int[] reversed(int[] arr) {
  int[] newArray = new int[arr.length];
  for(int i = 0; i < arr.length; i += 1) {
    arr[i] = newArray[arr.length - i - 1];
  }
  return arr;
}
```

##### After Fix

```java
// Corrected implementation of reversed
static int[] reversed(int[] arr) {
  int[] newArray = new int[arr.length];
  for(int i = 0; i < arr.length; i += 1) {
    newArray[i] = arr[arr.length - i - 1];
  }
  return newArray;
}
```

### Explanation of the Fix

The `reverseInPlace` method's fix ensures that the swapping of elements is done correctly by only iterating up to the middle of the array and using a temporary variable to hold the value during the swap

## Part 2 - Researching Commands

In this section, we will explore the `grep` command, which is used to search for a specified pattern within files. Here are four interesting command-line options for `grep`, along with examples and explanations of their usefulness.

### Option 1: `-i` (Ignore case)

The `-i` option makes `grep` case-insensitive, allowing it to match the pattern regardless of case.

```bash
grep -i 'pattern' ./technical/file.txt
```

This command searches for 'pattern' in `file.txt`, ignoring case distinctions.

### Option 2: `-r` (Recursive)

The `-r` option allows `grep` to search recursively through directories.

```bash
grep -r 'pattern' ./technical/
```

This command recursively searches for 'pattern' in all files within the `./technical` directory.

### Option 3: `-v` (Invert match)

The `-v` option inverts the match, so `grep` will return lines that do not match the pattern.

```bash
grep -v 'pattern' ./technical/file.txt
```

This command will display all lines from `file.txt` that do not contain 'pattern'.

### Option 4: `-l` (Files with matches)

The `-l` option will only output the names of files with matching lines, once for each file.

```bash
grep -l 'pattern' ./technical/*
```

This command lists the names of files in `./technical` that contain 'pattern'.

### Examples of Using Options

For each option, I will provide two examples using files and directories from `./technical`.

#### `-i` Examples

```bash
# Example 1
grep -i 'function' ./technical/code.js

# Example 2
grep -i 'error' ./technical/logs/error_log
```

#### `-r` Examples

```bash
# Example 1
grep -r 'TODO' ./technical/

# Example 2
grep -r 'FIXME' ./technical/src/
```

#### `-v` Examples

```bash
# Example 1
grep -v 'passed' ./technical/results/output.log

# Example 2
grep -v 'deprecated' ./technical/docs/README.md
```

#### `-l` Examples

```bash
# Example 1
grep -l 'urgent' ./technical/emails/*

# Example 2
grep -l 'confidential' ./technical/reports/*
```

Each of these options enhances the functionality of `grep` by providing different ways to search for patterns, which can be extremely useful for text processing in scripts, searching codebases, or analyzing logs.

*Note: The examples provided above assume the existence of a directory named `./technical` with various files. Replace the file paths with actual paths from your environment when trying out these commands.*

### Sources

The information about `grep` options was found through the `man` pages on a Unix-based system and verified with online resources such as the GNU Grep Manual.

### Use of AI Assistant

In the preparation of this lab report, I utilized an AI assistant, ChatGPT, to aid in several tasks. Below is a detailed account of my interactions with the assistant and how its contributions were integrated into the report.

#### Interactions with ChatGPT

- **Prompt 1**: I requested ChatGPT to analyze a provided piece of buggy Java code and identify the issues within it. I also asked for an explanation of the bugs and how they could be fixed.
- **Output**: ChatGPT identified the bugs in the `reverseInPlace` and `reversed` methods and suggested fixes for both. It also provided me with the correct JUnit test cases for each method.
- **Modifications**: I integrated ChatGPT's suggested code fixes into my report. I then executed the provided JUnit tests to obtain the test output, which I used to demonstrate the bugs in the report.

- **Prompt 2**: I sought ChatGPT's assistance in formatting the lab report content in Markdown, including headers and code blocks suitable for GitHub.
- **Output**: ChatGPT provided me with the Markdown-formatted text for the entire lab report.
- **Modifications**: I copied the Markdown content into my `lab3.md` file on GitHub, adding a screenshot of the JUnit output to the "Symptom" section as instructed by ChatGPT.

- **Prompt 3**: I encountered issues with the JUnit test output. I described the problem to ChatGPT, and it clarified the expected behavior of the tests.
- **Output**: ChatGPT explained why the tests were failing and verified that the `testReversedNonFailure` should indeed pass with the buggy implementation.
- **Modifications**: I updated the test method according to ChatGPT's guidance to reflect the correct behavior and ensure it passed with the buggy code.
