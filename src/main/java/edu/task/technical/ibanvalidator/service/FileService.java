package edu.task.technical.ibanvalidator.service;

import edu.task.technical.ibanvalidator.Model.IBAN;
import edu.task.technical.ibanvalidator.utils.FileUtil;

import java.io.*;
import java.util.List;

public class FileService {

    private String filePath = "./IBANList.txt";

    private String INPUT_FILE = filePath;
    private String OUTPUT_FILE = filePath + ".out";

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Read IBAN codes from a file and add to ibanCodeList
     */
    public boolean readIBANCodesFromFile(List<IBAN> ibanCodeList) {
        System.out.print("Reading IBAN codes from a FILE...");
        if (readFile(ibanCodeList)) {
            // If data loading from a file without errors inform the user and return true
            if (ibanCodeList.size() > 0) {
                System.out.println("\tdata successfully loaded.");
            } else {
                System.out.println("\tdata FILE is empty.");
            }
            return true;
        } else {
            // if data loading from a file failed inform the user and return false
            System.err.println("\tERROR: Failed to load data from a file.");
            return false;
        }
    }

    /**
     * Read string lines from a file and add to ibanCodeList
     */
    private boolean readFile(List<IBAN> ibanCodeList) {
        // Parse buffer returned from a readFile() method in FileUtil class
        try (BufferedReader bufferedReader = FileUtil.readFile(INPUT_FILE)) {
            String Line = null;
            // define IBAN obj outside for block to avoid new instantiation on every iteration
            IBAN iban;
            do {
                if (bufferedReader != null) {
                    Line = bufferedReader.readLine();
                } else {
                    return false;
                }
                if (Line != null) {
                    // Create IBAN obj from Line
                    iban = stringLineToIBANBeanMaker(Line);

                    // If iban not null add to codes array else fatal error happen exit method with return false
                    if (iban == null) {
                        return false;
                    }

                    // Add new IBAN object into ibanCodeList
                    ibanCodeList.add(iban);
                }
            } while (Line != null);

            // if this point reached return true as no fatal errors occur
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR: Caught IOException while trying to read and currencyRates data from a file: \n" + e.getMessage());
            return false;
        }
    }

    /**
     * Create IBAN object from String line
     */
    private IBAN stringLineToIBANBeanMaker(String Line) {
        // Shall not be null!
        if (Line == null) return null;
        if (Line.equals("")) return null;
        if (Line.equals("null")) return null;

        // Create and return new IBAN obj parsed from file line
        return new IBAN(Line);
    }

}
