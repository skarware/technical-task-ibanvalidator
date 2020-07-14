package edu.task.technical.ibanvalidator;

import edu.task.technical.ibanvalidator.service.IBANValidationService;

public class Main {

    // Get an instance of IBANValidationService
    private static final IBANValidationService ibanValidationService = new IBANValidationService();

    public static void main(String[] args) {

        // Start IBAN validator program with arguments from command line
        ibanValidationService.start(args);

    }
}
