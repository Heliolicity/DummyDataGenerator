package com.bh25034.dummydatagenerator.parser.impl;

import com.bh25034.dummydatagenerator.entities.Field;
import com.bh25034.dummydatagenerator.parser.Parser;
 
public class ParserImpl implements Parser {
     
    private String inputLine;
    private Field field;
     
    public ParserImpl() { super(); }
 
    @Override
    public void setLine(String line) {
        this.inputLine = line;
        this.inputLine = this.inputLine.toUpperCase();
        this.field = new Field();
    }
     
    @Override
    public void trimLine() { this.inputLine = this.inputLine.trim(); }
 
    @Override
    public void determineFieldName() {

        this.trimLine();
         
        try {
             
            int pos = this.inputLine.indexOf("\t");
             
            if (pos <= 0) {
            	
            	pos = this.inputLine.indexOf(" ");
            	
            }
             
            String text = this.inputLine.substring(0, pos);
            this.field.setName(text);
            
             
        }
         
        catch (StringIndexOutOfBoundsException sioobe) {
             
        	System.err.println("String Index Out Of Bounds Exception " + sioobe.getMessage());
            this.field.setName(null);
             
        }
         
    }
 
    @Override
    public void determineNotNull() {
        
        if (this.inputLine.contains("NOT NULL")) {
        	
        	this.field.setNotNull(true);
        	
        }
        
        else {
        	
        	this.field.setNotNull(false);
        	
        }
         
    }
 
    @Override
    public void determineFieldType() {
        
        this.trimLine();
         
        try {
 
            String text = "";
            int pos = this.inputLine.indexOf("(");
             
            if (pos > 0) {
             
                text = this.inputLine.substring(0, pos);
                pos = text.lastIndexOf("\t");
                
                if (pos <= 0) {
                	
                	pos = text.lastIndexOf(" ");
                	
                }
                
                text = text.substring(pos + 1, text.length());
                this.field.setType(text);
             
                 
            }
             
            else {
                
            	pos = this.inputLine.lastIndexOf("\t");
                
                if (pos <= 0) {
                	
                	pos = this.inputLine.lastIndexOf(" ");
                	
                }
                
                text = this.inputLine.substring(pos + 1, this.inputLine.length());
                this.field.setType(text);
                 
            }
             
        }
         
        catch (StringIndexOutOfBoundsException sioobe) {
             
        	System.err.println("String Index Out Of Bounds Exception " + sioobe.getMessage());
            this.field.setType(null);
             
        }
         
    }
 
    @Override
    public void determineFieldLength() {
        
        this.trimLine();
         
        try {
         
            int pos = this.inputLine.indexOf("(");
            float len = 0;
             
            if (pos > 0) {
             
                String text = this.inputLine.substring(pos + 1, this.inputLine.length());
                pos = text.lastIndexOf(")");
                text = text.substring(0, pos);
                text = text.replaceAll(",", ".");
                len = new Float(text).floatValue();
                this.field.setLength(len);
                
            }
             
            else {
                 
                this.field.setLength(32000);
                 
            }
             
        } 
         
        catch (NumberFormatException nfe) {
             
        	System.err.println("Number Format Exception " + nfe.getMessage());
            this.field.setLength(0);
             
        }
         
        catch (StringIndexOutOfBoundsException sioobe) {
             
        	System.err.println("String Index Out Of Bounds Exception " + sioobe.getMessage());
            this.field.setLength(0);
             
        }
         
    }
    
    @Override
    public void determinePrimaryKey() {
    	
        this.trimLine();
    	
        try {
            
            int pos = this.inputLine.indexOf("PK");
            
            if (pos > 0) {
            	
            	this.field.setPrimaryKey(true);
            	
            }
            
            else {
            	
            	this.field.setPrimaryKey(false);
            	
            }
             
        } 
     
        catch (StringIndexOutOfBoundsException sioobe) {
             
            System.err.println("String Index Out Of Bounds Exception " + sioobe.getMessage());
            this.field.setPrimaryKey(false);
             
        }
    	
    }
 
    @Override
    public Field getCompletedField() { return this.field; }
 
    public String getInputLine() {
        return inputLine;
    }
 
    public void setInputLine(String inputLine) {
        this.inputLine = inputLine;
    }
 
}
