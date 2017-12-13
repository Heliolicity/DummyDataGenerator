package com.bh25034.dummydatagenerator.preparer.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
 
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.bh25034.dummydatagenerator.entities.Data;
import com.bh25034.dummydatagenerator.entities.Field;
import com.bh25034.dummydatagenerator.preparer.Preparer;
 
public class PreparerImpl implements Preparer {
 
    private List<List<Field>> dataMatrix;
    private String tableName;
    private List<String> output;
    private String delimiter;
    private boolean exportSQL = false;
    private String dateFormat;
     
    public PreparerImpl(List<List<Field>> dataMatrix, String tableName, String delimiter, boolean exportSQL, String dateFormat) {
        super();
        this.dataMatrix = dataMatrix;
        this.tableName = tableName;
        this.delimiter = delimiter;
        this.exportSQL = exportSQL;
        this.dateFormat = dateFormat;
    }
    
    public PreparerImpl(List<List<Field>> dataMatrix) {
        super();
        this.dataMatrix = dataMatrix;
    }
     
    @Override
    public void prepareDelimitedExport() {
         
        this.output = new ArrayList<String>();
        String quote = "'";
        String line = "";
         
        for (List<Field> listFields : this.dataMatrix) {
             
            line = "";
             
            for (Field field : listFields) {
            	
                if (field.getType().equals("VARCHAR2") || field.getType().equals("VARCHAR")) {
                 
                    Data<String> data = field.getData();
                     
                    if (data != null) {
                    	
                    	line += quote + data.get().toString() + quote;
                    	
                    }
                    
                    else {
                    	
                    	line += quote + "" + quote;
                    	
                    }
                     
                }
                 
                else if (field.getType().equals("NUMBER")) {
                     
                    Data<Float> data = field.getData();
                    Float f = new Float(data.get().floatValue());
                                        
                    if (data != null) {
                    	
                    	line += f;
                    	
                    }
                    
                    else {
                    	
                    	line += "";
                    	
                    }
                     
                }
                 
                else if (field.getType().equals("DATE")) {
                     
                    Data<DateTime> data = field.getData();
                    DateTimeFormatter formatter = DateTimeFormat.forPattern(this.dateFormat);
                    DateTime dateTime = new DateTime(data.get());
                    String date = formatter.print(dateTime);
                     
                    if (this.exportSQL) {
                    	
                    	Date tempDate = new Date(System.currentTimeMillis());
                        
                        if (data != null) {
                        	
                        	line += "TO_DATE(" + quote + data.get() + quote + ", '" + this.dateFormat + "')";
                        }
                        
                        else {
                        	
                        	line += "TO_DATE(" + quote + tempDate + quote + ", '" + this.dateFormat + "')";
                        	
                        }
                    	
                    }
                    
                    else {
                    
	                    if (data != null) {
	                    	
	                    	line += quote + date + quote;
	                    	
	                    }
	                    
	                    else {
	                    	
	                    	line += quote + "" + quote;
	                    	
	                    }
	                    
                    }
                    
                }
                 
                else if (field.getType().equals("TIMESTAMP")) {
                     
                    Data<Timestamp> data = field.getData();
                    
                    if (this.exportSQL) {
                    	
                    	Timestamp tempTS = new Timestamp(System.currentTimeMillis());
                    	
                    	if (data != null) {
                    		
                    		line += "TO_TIMESTAMP(" + quote + data.get() + quote + ", 'YYYY-MM-DD HH24:MI:SS')";
                    	
                    	}
                    	
                        else {
                        	
                        	line += line += "TO_TIMESTAMP(" + quote + tempTS + quote + ", 'YYYY-MM-DD HH24:MI:SS')";
                        	
                        }
                    	
                    }
                    
                    else {
                     
	                    if (data != null) {
	                    	
	                    	line += "" + data.get().getTime() + "";
	                    	
	                    }
	                    
	                    else {
	                    	
	                    	line += "";
	                    	
	                    }
	                    
                    }
                    
                }
                 
                else {
                     
                    Data<String> data = field.getData();
                     
                    if (data != null) {
                    	
                    	line += quote + data.get().toString() + quote;
                    
                    }
                    
                    else {
                    	
                    	line += quote + "" + quote;
                    	
                    }
                     
                }
                 
                if (! (listFields.indexOf(field) == (listFields.size() - 1))) {
                	
                	line += this.delimiter;
                	
                }
                 
            }
             
            this.output.add(line);
             
        }
         
    }
 
    @Override
    public void prepareSQLScripts() {
        
        this.output = new ArrayList<String>();
        String insertLine = "";
        String newString;
        String fieldNames;
        List<Field> listFields;
         
        insertLine = "INSERT INTO " + this.tableName + " (";
         
        listFields = this.dataMatrix.get(0); 
        fieldNames = "";
         
        for (Field field : listFields) {
                 
            fieldNames += field.getName() + ",";
         
        }
        
        insertLine += fieldNames;
        insertLine = insertLine.substring(0, insertLine.length() - 1);
        insertLine += ") VALUES (";
        this.prepareDelimitedExport();
        List<String> newOutput = new ArrayList<String>();
         
        for (String string : this.output) {
             
            newString = insertLine + string + ");";
            newOutput.add(newString);
             
        }
         
        this.output = newOutput;
         
    }
 
    @Override
    public List<List<Field>> getDataMatrix() { return dataMatrix; }
 
    public String getTableName() {
        return tableName;
    }
 
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
 
    public List<String> getOutput() {
        return output;
    }
 
    /*public void setOutput(List<String> output) {
        this.output = output;
    }*/
 
    @Override
    public String getDelimiter() { return this.delimiter; }
 
    @Override
    public void setDelimiter(String delimiter) { this.delimiter = delimiter; }
 
    /*public void setDataMatrix(List<List<Field>> dataMatrix) {
        this.dataMatrix = dataMatrix;
    }*/
 
    public boolean isExportSQL() {
		return exportSQL;
	}

	public void setExportSQL(boolean exportSQL) {
		this.exportSQL = exportSQL;
	}

}