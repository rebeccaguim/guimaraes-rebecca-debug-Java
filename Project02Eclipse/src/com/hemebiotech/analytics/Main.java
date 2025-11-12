package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;

/**
 * Main class to run the symptom analytics application.
 */
public class Main {

    public static void main(String[] args) {
        //Initialize the reader and writer with file paths
        ISymptomReader reader = new ReadSymptomDataFromFile("symptoms.txt");
        ISymptomWriter writer = new WriteSymptomDataToFile("result.out");


         // Create the counter service by injecting the reader and writer
        AnalyticsCounter counter = new AnalyticsCounter(reader, writer);

        // Step 1: read all raw symptoms from file
        List<String> symptoms = counter.getSymptoms();

        // Step 2: count occurrences of each symptom
        Map<String, Integer> counted = counter.countSymptoms(symptoms);

        // Step 3: sort symptoms alphabetically
        Map<String, Integer> sorted = counter.sortSymptoms(counted);
        counter.writeSymptoms(sorted);
    }
}