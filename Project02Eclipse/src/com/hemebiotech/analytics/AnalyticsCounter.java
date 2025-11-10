package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

public class AnalyticsCounter {
    public static void main(String[] args) {

        try {
            
            ISymptomReader reader = new ReadSymptomDataFromFile("symptoms.txt");
            List<String> symptoms = reader.getSymptoms();

            
            Map<String, Integer> symptomCounts = new TreeMap<>();

            for (String rawSymptom : symptoms) {
                String symptom = rawSymptom.trim().toLowerCase();

                symptomCounts.put(symptom, symptomCounts.getOrDefault(symptom, 0) + 1);
            }

            ISymptomWriter writer = new WriteSymptomDataToFile("result.out");
            writer.writeSymptoms(symptomCounts);

            System.out.println(" Résultat écrit dans result.out");

        } catch (IOException e) {
            System.err.println(" Erreur lors du traitement : " + e.getMessage());
        }
    }
}
