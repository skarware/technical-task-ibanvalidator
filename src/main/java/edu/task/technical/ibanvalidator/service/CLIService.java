package edu.task.technical.ibanvalidator.service;

import java.util.Scanner;

public class CLIService {

    private static final String sepLine = "----------------------------------------------------------------------------";

    private void printMainMenu() {
        System.out.println(sepLine);
        System.out.println("A - Check IBAN validity from terminal input");
        System.out.println("B - Check IBAN validity in given text file");
        System.out.println("C - Start IBAN validation API on port 8080");
        System.out.println("E - Exit Program");
        System.out.println(sepLine);
        System.out.print("Enter option: ");
    }

    public String getUserInputString() {
        System.out.print("Please enter IBAN: ");
        return new Scanner(System.in).next();
    }

    public int printMainMenuAndGetUserOption() {

        // print User Menu Interface
        this.printMainMenu();

        // get user keypress and check Key Validity
        char key = getValidCharKeyInput("", 'a', 'b', 'c', 'e', 'A', 'B', 'C', 'E');

        // print user choice and return it OR exit program
        System.out.printf("Your option: %s\n", ((Character) key).toString().toUpperCase());

        return key;
    }

    private char getValidCharKeyInput(String inputQuestion, char... validKeyOptions) {
        char key = '-';
        do {
            System.out.print(inputQuestion);
            try {
                key = new Scanner(System.in).next().charAt(0);
            } catch (Exception e) {
                System.err.println("Error caught in getValidCharKeyInput() method");
            }
        } while (!this.isKeyInputValid(key, (char[]) validKeyOptions));
        return key;
    }

    public boolean isKeyInputValid(char key, char[] validKeyOptions) {
        for (int i = 0; i < validKeyOptions.length; i++) {
            if (key == validKeyOptions[i]) {
                return true;
            }
        }
        System.err.printf("%s - There is no such option, try again.\n", ((Character) key).toString());
        return false;
    }


}
