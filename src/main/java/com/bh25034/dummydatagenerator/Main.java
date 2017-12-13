package com.bh25034.dummydatagenerator;

import java.util.List;
import java.io.File;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.bh25034.dummydatagenerator.entities.Field;
import com.bh25034.dummydatagenerator.filehandling.Reader;
import com.bh25034.dummydatagenerator.filehandling.Writer;
import com.bh25034.dummydatagenerator.parser.Parser;
import com.bh25034.dummydatagenerator.parser.impl.ParserImpl;
import com.bh25034.dummydatagenerator.preparer.Preparer;
import com.bh25034.dummydatagenerator.preparer.impl.PreparerImpl;
import com.bh25034.dummydatagenerator.processor.Processor;
import com.bh25034.dummydatagenerator.processor.impl.ProcessorImpl;
import com.bh25034.dummydatagenerator.randomer.Randomer;
import com.bh25034.dummydatagenerator.randomer.impl.RandomerImpl;
 
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
        
        	System.out.println("Please specify six arguments");
            System.exit(0);
        	
        }
        
        else {
        
        	validateArguments(args);
	    
        }
        
        Reader reader = new Reader(fileIn);
        System.out.println("# Getting lines from file: " + fileIn + "\t-- " + new DateTime(System.currentTimeMillis()));
        reader.read();
         
        if (reader.isReadFile()) {
         
        	List<String> lines = reader.getLines();
        	System.out.println("# Read " + lines.size() + " lines from input file" + "\t-- " + new DateTime(System.currentTimeMillis()));
            Parser parser = new ParserImpl();
            Randomer randomer = new RandomerImpl();
            Processor processor = new ProcessorImpl(lines, rows, parser, randomer);
            System.out.println("# Parsing data to determine table structure for table: " + tableName + "\t-- " + new DateTime(System.currentTimeMillis()));
            processor.parseDescription();
            System.out.println("# Parsing complete" + "\t-- " + new DateTime(System.currentTimeMillis()));
            System.out.println("# Generating dummy data now" + "\t-- " + new DateTime(System.currentTimeMillis()));
            processor.generateDummyData();
            System.out.println("# Dataset generated" + "\t-- " + new DateTime(System.currentTimeMillis()));
            List<List<Field>> fieldsWithData = processor.getDataMatrix();
            Preparer preparer = new PreparerImpl(fieldsWithData, tableName, ",", exportSQL, dateFormat);
            
            if (exportSQL) {
            
            	System.out.println("# Preparing SQL scripts" + "\t-- " + new DateTime(System.currentTimeMillis()));
	            preparer.prepareSQLScripts();
	            System.out.println("# SQL scripts prepared" + "\t-- " + new DateTime(System.currentTimeMillis()));
	            
            }
            
            else {
            	
            	System.out.println("# Preparing delimited output" + "\t-- " + new DateTime(System.currentTimeMillis()));
            	preparer.prepareDelimitedExport();
            	System.out.println("# Delimited output prepared" + "\t-- " + new DateTime(System.currentTimeMillis()));
            	
            }
            
            List<String> output = preparer.getOutput();
            Writer writer = new Writer(fileOut, output);
            System.out.println("# Writing to file: " + fileOut + "\t-- " + new DateTime(System.currentTimeMillis()));
            writer.write();
             
            if (writer.isWroteFile()) {
            	
            	System.out.println("# SUCCESS: Job Done.\n# " + output.size() + " rows written to file: " + fileOut + "\t-- " + new DateTime(System.currentTimeMillis()));
            	System.exit(0);
            	
            }
            
            else {
            	
            	System.out.println("# ERROR: Could not write to file: " + fileOut + "\t-- " + new DateTime(System.currentTimeMillis()));
            	System.exit(0);
            	
            }
             
        }
         
        else {
             
        	System.out.println("# ERROR: Could not read from file: " + fileIn + "\t-- " + new DateTime(System.currentTimeMillis()));
            System.exit(0);
             
        }
         
    }
     
    private static void man() {
         
    	System.out.println("########### Dummy Data Generator ################################################################################################");
        System.out.println("# Description: Application for generating mock/dummy data for rational database tables\t\t\t\t\t\t#");
        System.out.println("# Usage: \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t#");
        System.out.println("# java com.bh25034.dummydatagenerator.Main <fileIn> <fileOut> <tableName> <rows> <exportMode> <dateFormat>\t\t\t#");
        System.out.println("#   -- <fileIn>:\tThe path to the file containing the SQL DESC statement of the table you wish to generate data for.\t#");
        System.out.println("#   -- <fileOut>:\tThe path where you would like the file containing the mock data (as INSERT statements) to be saved to.\t#");
        System.out.println("#   -- <tableName>:\tThe name of the table\t\t\t\t\t\t\t\t\t\t\t#");
        System.out.println("#   -- <rows>:\t\tThe number of rows of dummy data you wish to generate.\t\t\t\t\t\t\t#");
        System.out.println("#   -- <exportMode>:\tExport CSV or SQL statements (YES/NO, Y/N, TRUE/FALSE, T/F, 0/1).\t\t\t\t\t#");
        System.out.println("#   -- <dateFormat>:\tThe date format you wish to use for date fields.\t\t\t\t\t\t\t#");
        System.out.println();
         
    }
     
    private static void validateArguments(String args[]) {

    	String arg1;
        String arg2;
        String arg3;
        String arg4;
        String arg5;
        String arg6;
    	
    	try {
        
    		arg1 = args[0];
            arg2 = args[1];
            arg3 = args[2];
            arg4 = args[3];
            arg5 = args[4];
            arg6 = args[5];
         
            if ((arg1 == null) || (arg1.trim().isEmpty())) {
                
            	System.out.println("The path to the input file could not be found.");
                System.out.println("Please run the programme again and provide a valid input path.");
                System.exit(0);
                 
            }
             
            else {
            	
            	File file = new File(arg1);
            	
            	if (file.exists() && ! (file.isDirectory())) {
            	
            		fileIn = arg1;
            		
            	}
            	
            	else {
            		
            		System.out.println("The input file could not be found.");
            		System.out.println("Please run the programme again and provide a valid input file.");
                    System.exit(0);
            		
            	}
            	
            }
             
            if ((arg2 == null) || (arg2.trim().isEmpty())) {
                 
            	System.out.println("The path for the output file could not be found.");
                System.out.println("Please run the programme again and provide a valid output path.");
                System.exit(0);
                 
                 
            }
             
            else {
            	
            	fileOut = arg2;
            	
            }
             
            if ((arg3 == null) || (arg3.trim().isEmpty())) {
             
            	System.out.println("The table name was not specified");
                System.out.println("Please run the programme again and provide a valid table name.");
                System.exit(0);
     
            }
             
            else {
            	
            	tableName = arg3;
            	
            }
             
            if ((arg4 != null) && (! arg4.trim().isEmpty())) {
                 
                try {
                     
                    rows = new Integer(arg4).intValue();
                 
                }
                 
                catch (NumberFormatException nfe) {
                     
                	System.out.println("The number of rows specified is not numeric.");
                    System.out.println("Please run the application again with a numeric value.");
                    System.exit(0);
                     
                }
                 
            }
             
            else {
                 
            	System.out.println("The number of rows were not specified");
                System.out.println("Please run the programme again and provide a valid number of rows.");
                System.exit(0);
                 
            }
            
            if ((arg5 == null) || (arg5.trim().isEmpty())) {
            	
            	System.out.println("The export mode was not specified");
            	System.out.println("Please run the programme again and specify an export mode.");
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
            		
            		System.out.println("An incorrect/unrecognised export mode was not specified");
            		System.out.println("Please run the programme again and specify a valid export mode.");
                    System.exit(0);
            		
            	}
            	
            }
            
            if ((arg6 == null) || (arg6.trim().isEmpty())) {
            	
            	System.out.println("The date format was not specified");
            	System.out.println("Please run the programme again and specify an date format.");
                System.exit(0);
            	
            }
            
            else {
            	
            	try {
            	
            		@SuppressWarnings("unused")
    				DateTimeFormatter formatter = DateTimeFormat.forPattern(arg6);
            		dateFormat = arg6;
            		
            	}
            	
            	catch (IllegalArgumentException iae) {
            		
            		System.out.println("The date format provided is not a valid date format");
                    System.out.println("Please run the programme again and specify a valid date format.");
                    System.exit(0);
            		
            	}
            	
            }
            
        } 
         
        catch (ArrayIndexOutOfBoundsException aioobe) {
             
        	System.out.println("Please specify six arguments");
            System.exit(0);
             
        }
    	 
    }
 
}