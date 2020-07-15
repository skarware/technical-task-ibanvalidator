package edu.task.technical.ibanvalidator.service;

import edu.task.technical.ibanvalidator.Model.IBAN;
import org.apache.commons.validator.routines.IBANValidator;

import java.util.ArrayList;
import java.util.List;

public class IBANValidationService {

    // Initialize IBAN codes list
    private final List<IBAN> ibanCodeList = new ArrayList<>();

    // Get a singleton instance of the IBAN validator using the default formats
    private final IBANValidator ibanValidator = IBANValidator.getInstance();

    // Get dependencies
    private final CLIService cliService = new CLIService();
    private final FileService fileService = new FileService();

    // Prints passed IBAN code and its validation to STDOUT
    private void printCodeAndValidation(String ibanCode) {
        String codeAndValidity = "'" + ibanCode + "'" + " is " + (ibanValidator.isValid(ibanCode) ? "Valid" : "Not valid") + " IBAN;\n";
        System.out.println(codeAndValidity);
    }

    // Procedure to start IBANValidationService
    public void start(String[] args) {
        // Check if arguments from command line passed has an API argument
        if (args.length > 0) {
            if (args[0].toLowerCase().equals("api")) {
                // if so then run as WEB service and expose API endpoints
                // TODO: implement servlet to provide REST API service
                System.err.println("Sorry, API functionality has not been yet implemented");
            } else {
                System.err.println("Unknown option, try 'api'");
            }
        } else {
            // Else run command line interface version
            this.start();
        }
    }

    // Overloaded procedure to start IBANValidationService, shortcut to run as CLI version
    public void start() {
        this.initiateCLIMainMenu();
    }

    // Procedure to exit a program
    private void exitProgram() {
        System.out.println("\n\tProgram exiting,\n\t\t\t see you...");

        // exit the application
        System.exit(0);
    }

    // Initiate CLI Main Menu for UI
    private void initiateCLIMainMenu() {
        // Print main menu and get user option
        int userOption = cliService.printMainMenuAndGetUserOption();

        // useOption returns 3 different ways to flow for a program
        if (userOption == 97 || userOption == 65) { // if key == 'a' or A
            // Let user to input IBAN then check it and print its validation to STDOUT
            String ibanCode = cliService.getUserInputString("Please enter IBAN: ");
            this.printCodeAndValidation(ibanCode);

        } else if (userOption == 98 || userOption == 66) { // if key == 'b' or B
            // Read IBANs from file, validate and save the result to other file
            this.readIBANsFileValidateSaveResult(this.ibanCodeList);

        } else if (userOption == 99 || userOption == 67) { // if key == 'c' or C
            // TODO: invoke servlet API
            System.out.println("Sorry, API functionality has not been yet implemented");

        } else if (userOption == 101 || userOption == 69) { // if key == 'e' or E
            // Exit the program
            this.exitProgram();
        }

        // Go back to Start point
        this.start();
    }

    private void readIBANsFileValidateSaveResult(List<IBAN> ibanCodeList) {
        // Let user to input path to the file with IBANs
        String filePath = cliService.getUserInputString("Please provide File path: ");

        // Set filePath inside fileService
        this.fileService.setFilePath(filePath);

        // Parse the file for IBANs List
        boolean readFileSuccessfully = this.fileService.readIBANCodesFromFile(ibanCodeList);

        // If file parsed successfully only then:
        if (readFileSuccessfully) {
            // Iterate through List
            ibanCodeList.forEach((el) -> {
                // Validate IBANs
                el.isValid(this.ibanValidator.isValid(el.getCode()));

                // Print the result to STDOUT
                System.out.println(el.toString());
            });

            // Save result to other file with .out extension
            this.fileService.writeIBANCodesValidityIntoFile(ibanCodeList);
        }
    }

}
