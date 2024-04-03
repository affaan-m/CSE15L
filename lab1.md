<img width="515" alt="Screen Shot 2023-10-04 at 5 17 09 PM" src="https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/55a1a1f7-9315-4d36-95f8-0ea1262dad88">

#### Introduction to Basic Unix Commands: `cd`, `ls`, `cat`

1. **`cd`**: 
   - **Command**: `cd`
   - **Working Directory Before Execution**: `/home/user@sahara`
   - **Result**: Executing `cd` without any arguments doesn't change the directory as it defaults to the home directory. There's no error, but no change in the directory either.

2. **`ls`**: 
   - **Command**: `ls`
   - **Working Directory Before Execution**: `/home/user@sahara`
   - **Result**: Running `ls` displays 'lecture 1' as the top-level directory within the home directory. This command successfully lists directories without error.

3. **`cat`**: 
   - **Command**: `cat`
   - **Working Directory Before Execution**: `/home/user@sahara`
   - **Result**: Without arguments, `cat` awaits input. I used `Ctrl+C` to exit. This is an error case as `cat` requires arguments to function properly.

#### Navigating to Specific Directories

4. **`cd` with Path**: 
   - **Command**: `cd lecture 1`
   - **Working Directory Before Execution**: `/home/user@sahara`
   - **Result**: Using `cd` followed by the path to 'lecture 1' successfully changes the working directory. No error encountered.

5. **`ls` with Path**: 
   - **Command**: `ls lecture 1`
   - **Working Directory Before Execution**: `/home/user@sahara`
   - **Result**: Executing `ls` with the path to 'lecture 1' correctly lists 'messages' within 'lecture 1'. This command functions without error.

6. **`cat` with Directory Path**: 
   - **Command**: `cat lecture 1`
   - **Working Directory Before Execution**: `/home/user@sahara`
   - **Result**: Running `cat` with the path to 'lecture 1' generates an error, as `cat` cannot be used on directories.

#### Working with Text Files

7. **`cd` to Non-Directory**: 
   - **Command**: `cd en-ur.txt`
   - **Working Directory Before Execution**: `/home/user@sahara`
   - **Result**: Attempting to `cd` into 'en-ur.txt' results in an error because it's not a directory. `cd` is used for navigating directories, not files.

8. **`ls` with File Path**: 
   - **Command**: `ls en-ur.txt`
   - **Working Directory Before Execution**: `/home/user@sahara`
   - **Result**: Using `ls` on 'en-ur.txt' confirms the file's existence by listing it. No error here.

9. **`cat` with File Path**: 
   - **Command**: `cat en-ur.txt`
   - **Working Directory Before Execution**: `/home/user@sahara`
   - **Result**: Executing `cat` on 'en-ur.txt' successfully displays its content, "Hello World" in Urdu but in Latin script. There is no error in this operation.
