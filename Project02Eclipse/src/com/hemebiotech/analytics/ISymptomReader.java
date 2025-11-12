package com.hemebiotech.analytics;

import java.util.List;

/**
 * Interface for reading symptoms from a data source.
 * @author Rebecca Guimaraes
 */
public interface ISymptomReader {

   /**
    * Reads symptoms from a data source.
    * @return a list of symptoms.
    */ 
    List<String> getSymptoms(); 
}
