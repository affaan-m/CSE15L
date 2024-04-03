# Lab Report 4 - Vim

## Introduction

In the realm of software development, efficiency and speed are paramount. This report documents the process of optimizing a common development workflow using command-line tools and Vim. The objective was to establish a baseline for completing a set of predefined tasks and then improve on that baseline through the use of efficient tooling and techniques.

## Baseline Measurement

The initial run involved a series of steps executed without the use of advanced tooling techniques, serving as our baseline measurement. This included tasks such as logging into a server, cloning a repository, running tests, and pushing code changes.

## Optimized Workflow Using Vim and Command-Line Tools

The subsequent runs incorporated various efficiency techniques using Vim and Bash history. This section details each step, the keys pressed, the commands executed, and their effects, alongside corresponding screenshots demonstrating the process.

### Step 4: Logging into ieng6

- **Keys pressed**: `<ssh><space>cs15lfa23se@ieng6.ucsd.edu<enter>`
- **Effect**: Successfully authenticated and accessed the ieng6 server.
- **Login Screenshot**:
<img width="549" alt="Screen Shot 2023-12-03 at 9 26 19 PM" src="https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/c511cff7-dc2e-4e41-99c5-6b4c59868280">

### Step 5: Cloning the Repository

- **Keys pressed**: `<git><space>clone<space>git@github.com:affaan-m/lab7.git<enter>`
- **Effect**: The lab7 repository was cloned into the working directory on the server.
- **Clone Repository Screenshot**:
<img width="539" alt="Screen Shot 2023-12-03 at 9 26 46 PM" src="https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/69239650-458e-41fc-ab2e-774d70768842">

### Step 6: Running Tests

- **Keys pressed**: `<cd><space>lab7<enter><sh><space>test.sh<enter>`
- **Effect**: Executed the test script within the lab7 directory, resulting in one failed test.
- **Run Tests Screenshot**:
<img width="606" alt="Screen Shot 2023-12-03 at 9 27 16 PM" src="https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/5952f7f7-38d4-4f33-b694-3ac328619493">

### Step 7: Editing Code in Vim

- **Keys pressed**: `<vim><space>ListExamples.java<enter><k><k><i>2<Right><Backspace><Esc>:wq<enter>`
- **Effect**: Opened the `ListExamples.java` file in Vim, navigated to the problematic code, corrected it, and saved the changes.
- **Vim Editing Screenshot**:

<img width="588" alt="Screen Shot 2023-12-03 at 9 30 07 PM" src="https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/43536719-1098-42d1-aaba-a38722d3d187">)
- **Reran Tests Screenshot**:
<img width="461" alt="Screen Shot 2023-12-03 at 9 30 24 PM" src="https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/3ca7ea66-b02d-43f5-b21c-8abb939df257">

### Step 8: Committing Changes

- **Keys pressed**: `<git><space>add<space>ListExamples.java<enter><git><space>commit<enter>`
- **Effect**: Staged the changes for commit and opened Vim to write a commit message.
- **Git Commit Screenshot**:
<img width="504" alt="Screen Shot 2023-12-03 at 9 32 31 PM" src="https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/15e868c6-fabf-4971-9cc4-259101b71b7f">

### Step 9: Pushing Changes

- **Keys pressed**: `<git><space>push<enter>`
- **Effect**: Pushed the local commits to the remote GitHub repository.
- **Git Push Screenshot**:
<img width="504" alt="Screen Shot 2023-12-03 at 9 43 39 PM" src="https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/fb06cf6a-67af-40f0-afbc-fa99d593b4f7">

## Time Improvement

The initial completion of these tasks took 14 minutes. After employing Vim and command-line shortcuts, the time was reduced to just 6 minutes, not including additional time taken for documentation and screenshots.

## Conclusion

This exercise has highlighted the significant impact that mastery of development tools can have on productivity. Vim, combined with command-line proficiency, can dramatically reduce the time required for routine development tasks, allowing for more focus on complex problem-solving and creative thinking. As seen in this lab, such skills are invaluable for any developer looking to optimize their workflow.
