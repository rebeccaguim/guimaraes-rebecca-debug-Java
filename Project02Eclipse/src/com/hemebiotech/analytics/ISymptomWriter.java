package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.Map;

/**
 * Interface for writing symptoms to a data destination.
 * Each entry in the map represents a symptom (key) and its corresponding occurrence count (value).
 */

public interface ISymptomWriter {
   /**
    * Writes the symptoms and their counts to a data destination.
    * @param symptomsCount
    * @throws IOException
    */
   void writeSymptoms(Map<String, Integer> symptomsCount) throws IOException; 
}
