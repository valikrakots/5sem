package Controller;

import Controller.Exception.FileReaderException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class FileReader {
    public static String read(String filePath) throws FileReaderException {
        File f;
        try {
            f = new File(filePath);
        } catch (NullPointerException e) {
            throw new FileReaderException("Error in FileReader: " + e.getMessage(), e);
        }
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new FileReaderException("Error in FileReader: " + e.getMessage(), e);
        }
        StringBuilder builder = new StringBuilder();
        while (s.hasNextLine()) {
            String st = s.nextLine();
            builder.append(" ").append(st.replaceAll("\\s+", " "));
        }

        if (builder.equals(null)) {
            throw new FileReaderException("File is empty!");
        }

        return builder.toString();
    }
}