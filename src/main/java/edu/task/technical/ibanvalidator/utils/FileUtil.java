package edu.task.technical.ibanvalidator.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    public static BufferedReader readFile(String filePath) {
        try {
            /*
            FileInputStream reads file by byte by byte (byte stream) and is suitable for any type of file except for text files.
            Problem will arise if the text file is using a Unicode encoding where character represented with two or more bytes.
            The byte stream will read those separately therefore manual byte stream to characters conversion necessary.
             */
//            return new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            /*
            FileReader reads file char by char and should ne the prime choice working wth text files, reading or writing,
            but a Charset encoding scheme must be set for character stream in order to work properly. Character Stream under
            the hood is Byte Stream that has been wrapped with logic to output characters from a file with specific encoding.
             */
            return new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8));

        } catch (IOException e) {
            System.err.println("ERROR: Caught IOException while trying to read FILE: + " + filePath + ", Error message: \n" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static boolean writeFile(String string, String fileName, boolean append) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, append)))) {
            printWriter.println(string);
            return true;
        } catch (IOException e) {
            System.err.println("ERROR: Caught IOException while trying to write into FILE: \n" + e.getMessage());
            return false;
        }
    }
}
