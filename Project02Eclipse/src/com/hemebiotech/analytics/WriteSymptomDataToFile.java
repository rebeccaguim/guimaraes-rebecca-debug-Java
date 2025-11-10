package com.hemebiotech.analytics;

import java.io.IOException;
import java.io.FileWriter;
import java.util.Map;

/**
 * Writes symptoms and their occurrence count to a file.
 */
public class WriteSymptomDataToFile implements ISymptomWriter {

    private final String filepath;

    /**
     * Constructor that takes the file path where data will be written.
     * 
     * @param filepath Path to the output file
     */
    public WriteSymptomDataToFile(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void writeSymptoms(Map<String, Integer> symptoms) {
        if (filepath == null || filepath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }

        try (FileWriter writer = new FileWriter(filepath)) {
            for (Map.Entry<String, Integer> entry : symptoms.entrySet()) {
                writer.write(entry.getKey() + " : " + entry.getValue() + "\n");
            }
            System.out.println("✔️ Fichier de sortie écrit avec succès : " + filepath);
        } catch (IOException e) {
            System.err.println("❌ Erreur d'écriture dans le fichier : " + e.getMessage());
        }
    }
}
