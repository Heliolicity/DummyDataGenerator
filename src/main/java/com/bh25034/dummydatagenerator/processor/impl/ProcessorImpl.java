package com.bh25034.dummydatagenerator.processor.impl;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
 
import org.joda.time.DateTime;
 
import com.bh25034.dummydatagenerator.entities.Data;
import com.bh25034.dummydatagenerator.entities.Field;
import com.bh25034.dummydatagenerator.parser.Parser;
import com.bh25034.dummydatagenerator.processor.Processor;
import com.bh25034.dummydatagenerator.randomer.Randomer;
 
public class ProcessorImpl implements Processor {
 
    private List<String> lines;
    private List<Field> fields;
    private List<Field> fieldsWithData;
    private List<List<Field>> dataMatrix;
    private int rows;
    private Parser parser;
    private Randomer randomer;
     
    public ProcessorImpl(List<String> lines, int rows, Parser parser, Randomer randomer) {
        super();
        this.lines = lines;
        this.rows = rows;
        this.parser = parser;
        this.randomer = randomer;
    }
     
    @Override
    public void parseDescription() {
        
        this.fields = new ArrayList<Field>();
         
        Field field;
         
        if (this.lines != null) {
             
            for (String line : this.lines) {
                 
                this.parser.setLine(line);
                this.parser.determineFieldName();
                this.parser.determineNotNull();
                this.parser.determineFieldType();
                this.parser.determineFieldLength();
                this.parser.determinePrimaryKey();
                field = this.parser.getCompletedField();
                this.fields.add(field);
                 
            }
             
        }
         
    }
 
    @Override
    public void generateDummyData() {
        
        this.dataMatrix = new ArrayList<List<Field>>();
         
        float size;
        Field newField;
         
        try {
             
            if (this.fields != null) {
             
            	long primaryKey = 1;
            	List<String> primaryKeyCollection = new ArrayList<String>();
            	
                for (int i = 0; i < this.rows; i ++) {
                     
                    this.fieldsWithData = new ArrayList<Field>();
                     
                    for (Field field : this.fields) {
                         
                        newField = new Field();
                        newField.setName(field.getName());
                        newField.setNotNull(field.isNotNull());
                        newField.setType(field.getType());
                        newField.setLength(field.getLength());
                         
                        size = field.getLength();
                        
                        if (! field.isPrimaryKey()) {
                        
                        	newField.setPrimaryKey(false);
                        	
	                        if (field.getType().toUpperCase().equals("NUMBER")) {
	                             
	                            //Data<Integer> data = new Data<Integer>();
	                            //Integer randInt = new Integer(this.randomer.generateRandomInteger((int) size));
	                            //data.set(randInt);
	                        	Data<Float> data = new Data<Float>();
	                            Float randFloat = new Float(this.randomer.generateRandomInteger((int) size));
	                            data.set(randFloat);
	                            newField.setData(data);
	                             
	                        } 
	                         
	                        else if (field.getType().toUpperCase().equals("DATE")) {
	                         
	                            Data<Date> data = new Data<Date>();
	                        	Date date = new Date(System.currentTimeMillis());
	                        	data.set(date);
	                        	newField.setData(data);
	                             
	                        }
	                         
	                        else if (field.getType().toUpperCase().equals("VARCHAR2")) {
	                             
	                            Data<String> data = new Data<String>();
	                            String randStr = this.randomer.generateRandomString((int) size);
	                            data.set(randStr);
	                            newField.setData(data);
	                             
	                        }
	                         
	                        else if (field.getType().toUpperCase().equals("TIMESTAMP")) {
	                             
	                            Data<Timestamp> data = new Data<Timestamp>();
	                            DateTime dateTime = new DateTime();
	                            Timestamp timeStamp = new Timestamp(dateTime.getMillis());
	                            data.set(timeStamp);
	                            newField.setData(data);
	                             
	                        }
	                         
	                        else {
	                             
	                            Data<String> data = new Data<String>();
	                            String randStr = this.randomer.generateRandomString((int) size);
	                            data.set(randStr);
	                            newField.setData(data);
	                             
	                        }
	                    
                        }
                        
                        else {
                        	
                        	newField.setPrimaryKey(true);
                        	
                        	if (field.getType().toUpperCase().equals("NUMBER")) {
	                            
	                            Data<Long> data = new Data<Long>();
	                            //Integer randInt = new Integer(this.randomer.generateRandomInteger(size));
	                            Long key = new Long(primaryKey);
	                            //data.set(randInt);
	                            data.set(key);
	                            newField.setData(data);
	                            primaryKey ++;
	                             
	                        } 
                        	
                        	else {
	                             
	                            Data<String> data = new Data<String>();
	                            String randStr = this.randomer.generateRandomString((int) size);
	                            randStr = this.ensureUniqueness(primaryKeyCollection, randStr, (int) size);
	                            data.set(randStr);
	                            newField.setData(data);
	                            primaryKeyCollection.add(randStr);
	                             
	                        }
                        	
                        }
                        
                        this.fieldsWithData.add(newField);
                        
                    }
                     
                    this.dataMatrix.add(this.fieldsWithData);
                     
                }
                 
            }
         
        } 
         
        catch (NumberFormatException nfe) {
             
            System.err.println("Number Format Exception " + nfe.getMessage());
             
        } 
         
    }
 
    private String ensureUniqueness(List<String> collection, String key, int size) {
    	
    	String uniqueString;
    	
    	if (collection.contains(key)) {
    		
    		key = this.randomer.generateRandomString(size);
    		uniqueString = this.ensureUniqueness(collection, key, size);
    		
    	}
    	
    	else {
    		
    		uniqueString = key;
    		
    	}
    	
    	return uniqueString;
    	
    }
    
    public List<String> getLines() {
        return lines;
    }
 
    public void setLines(List<String> lines) {
        this.lines = lines;
    }
 
    public List<Field> getFields() {
        return fields;
    }
 
    /*public void setFields(List<ArrayList<Field>> fields) {
        this.fields = fields;
    }*/
 
    public List<List<Field>> getDataMatrix() {
        return dataMatrix;
    }
 
    /*public void setDataMatrix(List<List<Field>> dataMatrix) {
        this.dataMatrix = dataMatrix;
    }*/
 
    public int getRows() {
        return rows;
    }
 
    public void setRows(int rows) {
        this.rows = rows;
    }
 
    public Parser getParser() {
        return parser;
    }
 
    public void setParser(Parser parser) {
        this.parser = parser;
    }
     
}