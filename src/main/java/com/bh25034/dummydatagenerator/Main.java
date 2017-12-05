package com.bh25034.dummydatagenerator;

import java.util.List;
import java.io.File;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
    private static boolean exportSQL;
    private static String dateFormat;
     
    public static void main(String[] args) {
        // TODO Auto-generated method stub
 
        man();
        
        if (args.length < 6) {
        
        	pl("Please specify six arguments");
            System.exit(0);
        	
        }
        
        else {
        
	        try {
	            
	            validateArguments(args[0], args[1], args[2], args[3], args[4], args[5]);
	         
	        } 
	         
	        catch (ArrayIndexOutOfBoundsException aioobe) {
	             
	            pl("Please specify six arguments");
	            System.exit(0);
	             
	        }
        
        }
        
        Reader reader = new Reader(fileIn);
        pl("# Getting lines from file: " + fileIn + "\t-- " + new DateTime(System.currentTimeMillis()));
        reader.read();
         
        if (reader.isReadFile()) {
         
        	List<String> lines = reader.getLines();
        	pl("# Read " + lines.size() + " lines from input file" + "\t-- " + new DateTime(System.currentTimeMillis()));
            Parsing parser = new ParsingImpl();
            Randomise randomer = new RandomiseImpl();
            Processing processor = new ProcessingImpl(lines, rows, parser, randomer);
            pl("# Parsing data to determine table structure for table: " + tableName + "\t-- " + new DateTime(System.currentTimeMillis()));
            processor.parseDescription();
            pl("# Parsing complete" + "\t-- " + new DateTime(System.currentTimeMillis()));
            pl("# Generating dummy data now" + "\t-- " + new DateTime(System.currentTimeMillis()));
            processor.generateDummyData();
            pl("# Dataset generated" + "\t-- " + new DateTime(System.currentTimeMillis()));
            List<List<Field>> fieldsWithData = processor.getDataMatrix();
            Preparing preparer = new PreparingImpl(fieldsWithData, tableName, ",", exportSQL, dateFormat);
            
            if (exportSQL) {
            
	            pl("# Preparing SQL scripts" + "\t-- " + new DateTime(System.currentTimeMillis()));
	            preparer.prepareSQLScripts();
	            pl("# SQL scripts prepared" + "\t-- " + new DateTime(System.currentTimeMillis()));
	            
            }
            
            else {
            	
            	pl("# Preparing delimited output" + "\t-- " + new DateTime(System.currentTimeMillis()));
            	preparer.prepareDelimitedExport();
            	pl("# Delimited output prepared" + "\t-- " + new DateTime(System.currentTimeMillis()));
            	
            }
            
            List<String> output = preparer.getOutput();
            Writer writer = new Writer(fileOut, output);
            pl("# Writing to file: " + fileOut + "\t-- " + new DateTime(System.currentTimeMillis()));
            writer.write();
             
            if (writer.isWroteFile()) {
            	
            	pl("# SUCCESS: Job Done.\n# " + output.size() + " rows written to file: " + fileOut + "\t-- " + new DateTime(System.currentTimeMillis()));
            	System.exit(0);
            	
            }
            
            else {
            	
            	pl("# ERROR: Could not write to file: " + fileOut + "\t-- " + new DateTime(System.currentTimeMillis()));
            	System.exit(0);
            	
            }
             
        }
         
        else {
             
            pl("# ERROR: Could not read from file: " + fileIn + "\t-- " + new DateTime(System.currentTimeMillis()));
            System.exit(0);
             
        }
         
    }
     
    private static void man() {
         
        pl("########### Dummy Data Generator ################################################################################################");
        pl("# Description: Application for generating mock/dummy data for rational database tables\t\t\t\t\t\t#");
        pl("# Usage: \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t#");
        pl("# java com.bh25034.dummydatagenerator.Main <fileIn> <fileOut> <tableName> <rows> <exportMode> <dateFormat>\t\t\t#");
        pl("#   -- <fileIn>:\tThe path to the file containing the SQL DESC statement of the table you wish to generate data for.\t#");
        pl("#   -- <fileOut>:\tThe path where you would like the file containing the mock data (as INSERT statements) to be saved to.\t#");
        pl("#   -- <tableName>:\tThe name of the table\t\t\t\t\t\t\t\t\t\t\t#");
        pl("#   -- <rows>:\t\tThe number of rows of dummy data you wish to generate.\t\t\t\t\t\t\t#");
        pl("#   -- <exportMode>:\tExport CSV or SQL statements (YES/NO, Y/N, TRUE/FALSE, T/F, 0/1).\t\t\t\t\t#");
        pl("#   -- <dateFormat>:\tThe date format you wish to use for date fields.\t\t\t\t\t\t\t#");
        pl();
         
    }
     
    private static void validateArguments(String a1, String a2, String a3, String a4, String a5, String a6) {

        String arg1 = a1;
        String arg2 = a2;
        String arg3 = a3;
        String arg4 = a4;
        String arg5 = a5;
        String arg6 = a6;
         
        if ((arg1 == null) || (arg1.trim().equals(""))) {
         
            pl("The path to the input file could not be found.");
            pl("Please run the programme again and provide a valid input path.");
            System.exit(0);
             
        }
         
        else {
        	
        	File file = new File(arg1);
        	
        	if (file.exists() && ! (file.isDirectory())) {
        	
        		fileIn = arg1;
        		
        	}
        	
        	else {
        		
        		pl("The input file could not be found.");
                pl("Please run the programme again and provide a valid input file.");
                System.exit(0);
        		
        	}
        	
        }
         
        if ((arg2 == null) || (arg2.trim().equals(""))) {
             
            pl("The path for the output file could not be found.");
            pl("Please run the programme again and provide a valid output path.");
            System.exit(0);
             
             
        }
         
        else {
        	
        	fileOut = arg2;
        	
        }
         
        if ((arg3 == null) || (arg3.trim().equals(""))) {
         
            pl("The table name was not specified");
            pl("Please run the programme again and provide a valid table name.");
            System.exit(0);
 
        }
         
        else {
        	
        	tableName = arg3;
        	
        }
         
        if ((arg4 != null) && (! arg4.trim().equals(""))) {
             
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
        
        if ((arg5 == null) || (arg5.trim().equals(""))) {
        	
        	pl("The export mode was not specified");
            pl("Please run the programme again and specify an export mode.");
            System.exit(0);
        	
        }
        
        else {
        	
        	if ((arg5.toUpperCase().equals("TRUE")) || 
        			(arg5.toUpperCase().equals("T")) || 
        			(arg5.toUpperCase().equals("Y")) || 
        			(arg5.toUpperCase().equals("YES")) || 
        			(arg5.toUpperCase().equals("0"))) {
        		
        		exportSQL = true;
        		
        	} 
        	
        	else if ((arg5.toUpperCase().equals("FALSE")) || 
        			(arg5.toUpperCase().equals("F")) || 
        			(arg5.toUpperCase().equals("N")) || 
        			(arg5.toUpperCase().equals("NO")) || 
        			(arg5.toUpperCase().equals("-1"))) {
        		
        		exportSQL = false;
        		
        	}
        	
        	else {
        		
        		pl("An incorrect/unrecognised export mode was not specified");
                pl("Please run the programme again and specify a valid export mode.");
                System.exit(0);
        		
        	}
        	
        }
        
        if ((arg6 == null) || (arg6.trim().equals(""))) {
        	
        	pl("The date format was not specified");
            pl("Please run the programme again and specify an date format.");
            System.exit(0);
        	
        }
        
        else {
        	
        	try {
        	
        		@SuppressWarnings("unused")
				DateTimeFormatter formatter = DateTimeFormat.forPattern(arg6);
        		dateFormat = arg6;
        		
        	}
        	
        	catch (IllegalArgumentException iae) {
        		
        		pl("The date format provided is not a valid date format");
                pl("Please run the programme again and specify a valid date format.");
                System.exit(0);
        		
        	}
        	
        	
        }
        
    }
 
    @SuppressWarnings("unused")
	private static void p(String s) { System.out.print(s); }
     
    private static void pl() { System.out.println(); }
     
    private static void pl(String s) { System.out.println(s); }
 
 
}