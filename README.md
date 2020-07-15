# IBAN Validator

##### Java program wit API and Command Line Interface for IBAN validation.

## About the program

One of technical tasks I got while looking for my first software developer position. Made using <i><b>Java 11, Jetty, Servlet, commons-validator and Maven</b></i>.
 
Program validates IBAN by entering it into terminal or from given file, result saved in *.out file;\
Can be run as standalone WEB service for IBAN validation through API endpoint: `http://host:port/api/ibanvalidator?iban=CODEtoValidate`;\
Program uses commons-validator library to validate IBANs.

Made a decent effort to write clean OOP code to my `Date.now()` best understanding, like separation of concerns and encapsulation of internal workings of the class to hide details from outside while providing a simple interface to work with a class and there should be no to little pain adding new functionality.

## How to set up the program

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
## How to use
To run the program use java JRE with executable JAR file with no arguments or with one or two arguments: one for 'api' if run as standalone WEB service, second for its port. See below for more:
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
If option C chosen then program will start Http Server on port 8080 and return to Main Menu. To stop the server exit program with Main Menu option `E`:
```
Your option: C
2020-07-16 01:07:41.640:INFO::main: Logging initialized @32136ms to org.eclipse.jetty.util.log.StdErrLog
2020-07-16 01:07:41.707:INFO:oejs.Server:main: jetty-9.4.30.v20200611; built: 2020-06-11T12:34:51.929Z; git: 271836e4c1f4612f12b7bb13ef5a92a927634b0d; jvm 11.0.7+10-post-Ubuntu-2ubuntu218.04
2020-07-16 01:07:41.769:INFO:oejs.AbstractConnector:main: Started ServerConnector@13b6d03{HTTP/1.1, (http/1.1)}{0.0.0.0:8080}
2020-07-16 01:07:41.771:INFO:oejs.Server:main: Started @32268ms
----------------------------------------------------------------------------
A - Check IBAN validity from terminal input
B - Check IBAN validity in given text file
C - Start IBAN validation API on port 8080
E - Exit Program
----------------------------------------------------------------------------
Enter your option: e
Your option: E

        Program exiting,
                         see you...
2020-07-16 01:07:52.737:INFO:oejs.AbstractConnector:main: Stopped ServerConnector@13b6d03{HTTP/1.1, (http/1.1)}{0.0.0.0:8080}
```
To run the program as standalone API execute JAR with one or two arguments: an 'api' argument if run as standalone WEB service with port 8080, second argument for arbitrary service port:
```
 java -jar target/ibanvalidator-1.0-SNAPSHOT-jar-with-dependencies.jar api
```
```
 java -jar target/ibanvalidator-1.0-SNAPSHOT-jar-with-dependencies.jar api 8090
```
If all went well you should see Http server INFO printed to STDOUT:
```
2020-07-16 01:08:19.834:INFO::main: Logging initialized @153ms to org.eclipse.jetty.util.log.StdErrLog
2020-07-16 01:08:19.897:INFO:oejs.Server:main: jetty-9.4.30.v20200611; built: 2020-06-11T12:34:51.929Z; git: 271836e4c1f4612f12b7bb13ef5a92a927634b0d; jvm 11.0.7+10-post-Ubuntu-2ubuntu218.04
2020-07-16 01:08:19.954:INFO:oejs.AbstractConnector:main: Started ServerConnector@75828a0f{HTTP/1.1, (http/1.1)}{0.0.0.0:8090}
2020-07-16 01:08:19.957:INFO:oejs.Server:main: Started @283ms
```
## How to use API endpoint
<b>To interacting with API one can use <b><i>curl</i></b> tool as in following examples:</b>

Please note in examples below program uses default port: 8080 and runs on localhost machine.

Provide Valid IBAN:
```
curl --location --request GET 'http://localhost:8080/api/ibanvalidator?iban=LT647044001231465456'
```
Response from API: `{"LT647044001231465456": "true"}`

Provide Invalid IBAN:
```
curl --location --request GET 'http://localhost:8080/api/ibanvalidator?iban=LT227044077788877777'
```
Response from API: `{"LT227044077788877777": "false"}`




