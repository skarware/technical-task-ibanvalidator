# IBAN Validator

##### Java program wit Command Line Interface for IBAN validation.

## About the app

One of technical tasks I got while looking for my first software developer position. Made using <i><b>Java 11, commons-validator and Maven</b></i>.
 
Program validates IBAN by entering it into terminal or from given file, result saved in *.out file;\
Can be run as standalone WEB service* for IBAN validation through API on port 8080;\
Program uses commons-validator library to validate IBANs.

_*Functionality not yet implemented._

Made a decent effort to write clean OOP code to my `Date.now()` best understanding, like separation of concerns and encapsulation of internal workings of the class to hide details from outside while providing a simple interface to work with a class and there should be no to little pain adding new functionality.

## How to set up

Open terminal and use git clone command to download the remote GitHub repository to your computer:
```
git clone https://github.com/skarware/technical-task-ibanvalidator.git
```
It will create a new folder with same name as GitHub repository `technical-task-ibanvalidator`. All the project files and git data will be cloned into it. After cloning complete change directories into that new folder:
```
cd technical-task-ibanvalidator
```
To compile the application into executable JAR run this command (uses maven wrapper):
```
./mvnw clean package
```
Or using your installed maven version:
```
mvn clean package
```
## How to run
To run the program use java JRE with executable JAR file with no arguments or with one argument 'api' if run as standalone WEB service:
```
java -jar target/ibanvalidator-1.0-SNAPSHOT-jar-with-dependencies.jar
```
If all went well you should see interactive User Interface printed to STDOUT:
```
skarware@citadel:~technical-task-ibanvalidator$ java -jar target/ibanvalidator-1.0-SNAPSHOT-jar-with-dependencies.jar
----------------------------------------------------------------------------
A - Check IBAN validity from terminal input
B - Check IBAN validity in given text file
C - Start IBAN validation API on port 8080
E - Exit Program
----------------------------------------------------------------------------
Enter your option:
```
If option A chosen, enter single IBAN into a terminal window, program will print the result into STDOUT:
```
Your option: A
Please enter IBAN: LT647044001231465456
'LT647044001231465456' is Valid IBAN;
```
```
Your option: A
Please enter IBAN: LT227044077788877777
'LT227044077788877777' is Not valid IBAN;
```
If option B chosen and IBAN input file provided, in example `IBANList.txt`, program will parse the file, print the result to STDOUT and Saves it into other file named: `IBANList.txt.out`:
```
Your option: B
Please provide File path: IBANList.txt
Reading IBAN codes from a FILE...       data successfully loaded.
AA051245445454552117989,false
LT647044001231465456,true
LT517044077788877777,true
LT227044077788877777,false
CC051245445454552117989,false
Writing IBAN codes into a FILE...       successfully written to a FILE.
```


