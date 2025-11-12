package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Service class that reads, counts, sorts, and writes symptom data.
 */
public class AnalyticsCounter {

    private final ISymptomReader reader;
    private final ISymptomWriter writer;

    /**
     * Constructor that injects the reader and writer.
     *
     * @param reader the symptom data reader
     * @param writer the symptom data writer
     */
    public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * Retrieves the raw list of symptoms from the reader.
     *
     * @return a list of symptoms (possibly with duplicates)
     */
    public List<String> getSymptoms() {
        return reader.getSymptoms();
    }

    /**
     * Counts how many times each symptom occurs.
     *
     * @param symptoms the raw list of symptoms
     * @return a map with symptom names as keys and their occurrence counts as values
     */
    public Map<String, Integer> countSymptoms(List<String> symptoms) {
        Map<String, Integer> counts = new TreeMap<>();
        for (String rawSymptom : symptoms) {
            String symptom = rawSymptom.trim().toLowerCase();
            if (!symptom.isEmpty()) {
                counts.put(symptom, counts.getOrDefault(symptom, 0) + 1);
            }
        }
        return counts;
    }

    /**
     * Sorts the symptoms alphabetically.
     * Since TreeMap keeps keys sorted, no extra logic is needed.
     *
     * @param symptoms map of symptoms and counts
     * @return a new sorted map
     */
    public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
        return new TreeMap<>(symptoms); // Already sorted
    }

    /**
     * Writes the symptom data to the output using the writer instance.
     *
     * @param symptoms the map of sorted symptom data
     */
    public void writeSymptoms(Map<String, Integer> symptoms) {
        try {
            writer.writeSymptoms(symptoms);
            System.out.println(" Result written to output.");
        } catch (IOException e) {
            System.err.println(" Error writing output: " + e.getMessage());
        }
    }
}