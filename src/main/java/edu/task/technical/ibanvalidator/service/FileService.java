package edu.task.technical.ibanvalidator.service;

import edu.task.technical.ibanvalidator.Model.IBAN;
import edu.task.technical.ibanvalidator.utils.FileUtil;

import java.io.*;
import java.util.List;

public class FileService {

    private String INPUT_FILEPATH;
    private String OUTPUT_FILEPATH;

    // Initialize input and output file paths
    public void setFilePath(String filePath) {
        this.INPUT_FILEPATH = filePath;
        this.OUTPUT_FILEPATH = filePath + ".out";
    }

    // Read IBAN codes from a file and add to ibanCodeList
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

    // Read string lines from a file and add to ibanCodeList
    private boolean readFile(List<IBAN> ibanCodeList) {
        // Parse buffer returned from a readFile() method in FileUtil class
        try (BufferedReader bufferedReader = FileUtil.readFile(INPUT_FILEPATH)) {
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
            System.err.println("ERROR: Caught IOException while trying to read data from a file: \n" + e.getMessage());
            return false;
        }
    }

    // Create IBAN object from String line
    private IBAN stringLineToIBANBeanMaker(String Line) {
        // Shall not be null!
        if (Line == null) return null;
        if (Line.equals("")) return null;
        if (Line.equals("null")) return null;

        // Create and return new IBAN obj parsed from file line
        return new IBAN(Line);
    }

    // Write IBAN codes and validity into a file
    public boolean writeIBANCodesValidityIntoFile(List<IBAN> ibanCodeList) {
        System.out.print("Writing IBAN codes into a FILE...\t");

        // Get IBAN List as String
        String ibanAsString = this.ibanListToString(ibanCodeList);

        // Create a File for output
        File outputFile = new File(OUTPUT_FILEPATH);

        // Write to a file and check if it was successful
        if (this.saveStringToFile(ibanAsString.toString(), outputFile)) {
            System.out.println("successfully written to a FILE.");
            return true;
        }
        // Return false if save operations failed
        return false;
    }

    // Save String with IBAN codes and validity lines into the file
    private boolean saveStringToFile(String string, File file) {
        // Set if append to a file
        boolean append = false;

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    if (FileUtil.writeFile(string, file, append)) {
                        // return true if String data written to file successfully
                        return true;
                    } else {
                        System.err.println("ERROR: Failed to save data into a \"" + string + "\" FILE");
                        return false;
                    }
                } else {
                    System.err.println("ERROR: Failed to create and save data into a \"" + string + "\" FILE");
                    return false;
                }
            } catch (IOException e) {
                System.err.println("ERROR: Caught IOException while trying to create and save data into a \"" + string + "\" FILE: \n" + e.getMessage());
                return false;
            }
        } else {
            if (FileUtil.writeFile(string, file, append)) {
                // return true if String data written to file successfully
                return true;
            } else {
                System.err.println("ERROR: Failed to save data into a \"" + string + "\" FILE");
                return false;
            }
        }
    }

    // Convert List<IBAN> into formatted String with multiple lines
    private String ibanListToString(List<IBAN> ibanCodeList) {
        StringBuilder stringBuilder = new StringBuilder();

        // Iterate through the list and write each iban and its validity to a file using custom toString method from IBAN model
        ibanCodeList.forEach((iban) -> {
            if (iban != null) {
                stringBuilder.append(iban.toString()).append("\n");
            }
        });
        // Return stringBuilder only if there is something to return
        if (stringBuilder.length() > 0) {
            // At the end chop off one new line too much because of .append("\n")
            return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
        } else {
            // Else return empty string to avoid out of bound exception
            return "";
        }
    }

}
