package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 	Implementation of the ISymptomReader interface.
 * This class reads symptoms from a text file, where each line represents one symptom.
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;
	
	/**
	 * Constructor that sets the path of the file to be read.
	 * @param filepath
	 */
	public ReadSymptomDataFromFile (String filepath) {
		this.filepath = filepath;
	}
	
	@Override
	public List<String> getSymptoms() {
		ArrayList<String> result = new ArrayList<String>();
		 // Check that the file path is not null
		if (filepath != null) {
			try {
				BufferedReader reader = new BufferedReader (new FileReader(filepath));
				String line = reader.readLine();

				// Read each line and add it to the result list
				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
				reader.close();
				// Handle potential IO exceptions
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
