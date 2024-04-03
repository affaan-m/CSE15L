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
