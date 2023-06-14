package com.Onito.io.CSV;

import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class CsvFile {
	
	
	public static CSVParser csvMovieParse() {
		
		//Create an Object CSVFormat.Builder class
		CSVFormat.Builder builder = CSVFormat.DEFAULT.builder();
		
		//Specify Header
		builder=builder.setHeader(new String[] {"tconst","titleType","primaryTitle","runtimeMinutes","genres"});
		
		//Skip the Header
		builder=builder.setSkipHeaderRecord(true);
		
		//get Object of CSVFormat using CSVFormat.Builder Object
		CSVFormat format=builder.build();
		CSVParser parser=null;
		try {
			parser=format.parse(new FileReader("movies.csv"));
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return parser;
	}
	
	public static CSVParser csvRatingParse() {
			
			//Create an Object CSVFormat.Builder class
			CSVFormat.Builder builder = CSVFormat.DEFAULT.builder();
			
			//Specify Header
			builder=builder.setHeader(new String[] {"tconst","averageRating","numVotes"});
			
			//Skip the Header
			builder=builder.setSkipHeaderRecord(true);
			
			//get Object of CSVFormat using CSVFormat.Builder Object
			CSVFormat format=builder.build();
			CSVParser parser=null;
			try {
				parser=format.parse(new FileReader("ratings.csv"));
				
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			return parser;
		}


}
