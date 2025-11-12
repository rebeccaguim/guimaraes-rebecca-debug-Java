package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

/**
 * Main class for counting symptoms from a file and writing the results to an output file.
 * @author Rebecca Guimaraes
 */

public class AnalyticsCounter {
    public static void main(String[] args) {

        try {
            
        // Read symptoms from input file
            ISymptomReader reader = new ReadSymptomDataFromFile("symptoms.txt");
            List<String> symptoms = reader.getSymptoms();

            // Count occurrences of each symptom
            Map<String, Integer> symptomCounts = new TreeMap<>();

            // Normalize and count symptoms
            for (String rawSymptom : symptoms) {
                String symptom = rawSymptom.trim().toLowerCase();

                symptomCounts.put(symptom, symptomCounts.getOrDefault(symptom, 0) + 1);
            }

            // Write the counted symptoms to output file
            ISymptomWriter writer = new WriteSymptomDataToFile("result.out");
            writer.writeSymptoms(symptomCounts);

            System.out.println("Results successfully written to result.out");

            // Handle potential IO exceptions
        } catch (IOException e) {
            System.err.println(" Error during processing:  " + e.getMessage());
        }
    }
}
