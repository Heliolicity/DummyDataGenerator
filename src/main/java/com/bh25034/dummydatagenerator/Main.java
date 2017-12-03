package com.bh25034.dummydatagenerator;

import java.util.List;

import com.bh25034.dummydatagenerator.entities.Field;
import com.bh25034.dummydatagenerator.filehandling.Reader;
import com.bh25034.dummydatagenerator.filehandling.Writer;
import com.bh25034.dummydatagenerator.parser.Parsing;
import com.bh25034.dummydatagenerator.parser.impl.ParsingImpl;
import com.bh25034.dummydatagenerator.preparer.Preparing;
import com.bh25034.dummydatagenerator.preparer.impl.PreparingImpl;
import com.bh25034.dummydatagenerator.processor.Processing;
import com.bh25034.dummydatagenerator.processor.impl.ProcessingImpl;
import com.bh25034.dummydatagenerator.randomer.Randomise;
import com.bh25034.dummydatagenerator.randomer.impl.RandomiseImpl;

public class Main {

	private static String fileIn;
	private static String fileOut;
	private static String tableName;
	private static int rows;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		man();
		
		try {
			
			validateArguments(args[0], args[1], args[2], args[3]);
		
		} 
		
		catch (ArrayIndexOutOfBoundsException aioobe) {
			
			pl("Please specify four arguments");
			System.exit(0);
			
		}
		
		Reader reader = new Reader(fileIn);
		reader.read();
		
		if (reader.isReadFile()) {
		
			List<String> lines = reader.getLines();
			Parsing parser = new ParsingImpl();
			Randomise randomer = new RandomiseImpl();
			Processing processor = new ProcessingImpl(lines, rows, parser, randomer);
			processor.parseDescription();
			processor.generateDummyData();
			List<List<Field>> fieldsWithData = processor.getDataMatrix();
			Preparing preparer = new PreparingImpl(fieldsWithData, tableName, ","); 
			preparer.prepareSQLScripts();
			List<String> output = preparer.getOutput();
			Writer writer = new Writer(fileOut, output);
			writer.write();
			
			if (writer.isWroteFile()) pl("Job Done");
			else pl("Could not write to file");
			
		}
		
		else {
			
			pl("Could not read from file");
			
		}
		
	}
	
	private static void man() {
		
		pl("########### Dummy Data Generator ################################################################################################");
		pl("# Description: Application for generating mock/dummy data for rational database tables\t\t\t\t\t\t#");
		pl("# Usage: \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t#");
		pl("# java com.bh25034.dummydatagenerator.Main <fileIn> <fileOut> <tableName> <rows>\t\t\t\t\t\t#");
		pl("#   -- <fileIn>:\tThe path to the file containing the SQL DESC statement of the table you wish to generate data for.\t#");
		pl("#   -- <fileOut>:\tThe path where you would like the file containing the mock data (as INSERT statements) to be saved to.\t#");
		pl("#   -- <tableName>:\tThe name of the table\t\t\t\t\t\t\t\t\t\t\t#");
		pl("#   -- <rows>:\t\tThe number of rows of dummy data you wish to generate.\t\t\t\t\t\t\t#");
		pl();
		
	}
	
	private static void validateArguments(String a1, String a2, String a3, String a4) {
		
		boolean retval = false;
		int errCount = 0;
		
		String arg1 = a1;
		String arg2 = a2;
		String arg3 = a3;
		String arg4 = a4;
		
		if ((arg1 == null) || (arg1.trim().equals(""))) {
		
			pl("The path to the input file could not be found.");
			pl("Please run the programme again and provide a valid input path.");
			System.exit(0);
			
		}
		
		else fileIn = arg1;
		
		if ((arg2 == null) || (arg2.trim().equals(""))) {
			
			pl("The path for the output file could not be found.");
			pl("Please run the programme again and provide a valid output path.");
			System.exit(0);
			
			
		}
		
		else fileOut = arg2;
		
		if ((arg3 == null) || (arg3.trim().equals(""))) {
		
			pl("The table name was not specified");
			pl("Please run the programme again and provide a valid table name.");
			System.exit(0);

		}
		
		else tableName = arg3;
		
		if ((arg4 != null) || (! arg4.trim().equals(""))) {
			
			try {
				
				rows = new Integer(arg4).intValue();
			
			}
			
			catch (NumberFormatException nfe) {
				
				pl("The number of rows specified is not numeric.");
				pl("Please run the application again with a numeric value.");
				System.exit(0);
				
			}
			
		}
		
		else {
			
			pl("The number of rows were not specified");
			pl("Please run the programme again and provide a valid number of rows.");
			System.exit(0);
			
		}
		
	}

	private static void p(String s) { System.out.print(s); }
	
	private static void pl() { System.out.println(); }
	
	private static void pl(String s) { System.out.println(s); }


}
