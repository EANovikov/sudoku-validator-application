# How to use

- Go to project root and execute 
> mvn clean install

Once it finished, the executable jar file should be created.

- Run the validation using validate.bat with an argument:
validate.bat <your_file>

There are some examples which demonstrating successful 
and failed validation:

> validate.bat src\main\resources\game.txt
> validate.bat src\main\resources\gameIncorrect.txt

## Requirements

Create a command line tool (running on jvm) for validating a standard 9x9 Sudoku puzzle:
Command line: validate.bat puzzleName.txt
File format: csv format each line representing a row e.g.:
>1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
>
The program should return 0 (VALID) or non-zero (INVALID) value with an error text on stdout (in case of an invalid solution or file).
There should be unit tests covering a range of error conditions and the project should be maven or gradle based.
It should be possible to unpack the code from a zip, generate test report, build it and use a batch file to call the code from a packaged jar.

## Features list:
1) cmd jvm program
2) run via validate.bat
3) validate sudoku puzzle 9x9
    - each line must contains unique numbers
    - each 3x3 square must contains unique numbers
4) successful validation: 
     - should return 0 (VALID) 
5) failed validation: 
     - should return non-zero (INVALID) value 
     - should contain an error text on stdout 
     - should check both solution and file    