package com.hemebiotech.analytics;

import java.io.IOException;
import java.io.FileWriter;
import java.util.Map;


/**
 * Implementation of the ISymptomWriter interface.
 * This class writes symptoms and their occurrence counts to a text file.
 */
public class WriteSymptomDataToFile implements ISymptomWriter {

    private final String filepath;

   /**
     * Constructor that sets the output file path.
     *
     * @param filepath the path where the symptom data will be written
     */

    public WriteSymptomDataToFile(String filepath) {
        this.filepath = filepath;
    }
// Write symptoms and their counts to the specified file
    @Override
    public void writeSymptoms(Map<String, Integer> symptoms) {

        // Check that the file path is valid
        if (filepath == null || filepath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
// Use try-with-resources to ensure the FileWriter is closed properly
        try (FileWriter writer = new FileWriter(filepath)) {

             // Write each symptom and its count to a new line
            for (Map.Entry<String, Integer> entry : symptoms.entrySet()) {
                writer.write(entry.getKey() + " : " + entry.getValue() + "\n");
            }
            System.out.println(" Output file successfully written: " + filepath);


        } catch  (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
