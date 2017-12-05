package com.bh25034.dummydatagenerator.parser.impl;

import com.bh25034.dummydatagenerator.entities.Field;
import com.bh25034.dummydatagenerator.parser.Parsing;
 
public class ParsingImpl implements Parsing {
     
    private String inputLine;
    private Field field;
     
    public ParsingImpl() { super(); }
 
    @Override
    public void setLine(String line) {
        this.inputLine = line;
        this.inputLine = this.inputLine.toUpperCase();
        this.field = new Field();
    }
     
    @Override
    public void trimLine() {
        // TODO Auto-generated method stub
        this.inputLine = this.inputLine.trim();     
    }
 
    @Override
    public void determineFieldName() {
        // TODO Auto-generated method stub
        this.trimLine();
         
        try {
             
            int pos = this.inputLine.indexOf("\t");
             
            if (pos <= 0) pos = this.inputLine.indexOf(" ");
             
            String text = this.inputLine.substring(0, pos);
            this.field.setName(text);
            //pl("FIELD NAME: " + this.field.getName());
             
        }
         
        catch (StringIndexOutOfBoundsException sioobe) {
             
            pl("String Index Out Of Bounds Exception " + sioobe.getMessage());
            this.field.setName(null);
             
        }
         
    }
 
    @Override
    public void determineNotNull() {
        // TODO Auto-generated method stub
        if (this.inputLine.contains("NOT NULL")) this.field.setNotNull(true);
        else this.field.setNotNull(false);
         
        //pl("IS NOT NULL: " + this.field.isNotNull());
         
    }
 
    @Override
    public void determineFieldType() {
        // TODO Auto-generated method stub
        this.trimLine();
         
        try {
 
            String text = "";
            int pos = this.inputLine.indexOf("(");
             
            if (pos > 0) {
             
                text = this.inputLine.substring(0, pos);
                pos = text.lastIndexOf("\t");
                
                if (pos <= 0) pos = text.lastIndexOf(" ");
                
                text = text.substring(pos + 1, text.length());
                this.field.setType(text);
             
                 
            }
             
            else {
                
            	pos = this.inputLine.lastIndexOf("\t");
                
                if (pos <= 0) pos = this.inputLine.lastIndexOf(" ");
                
                text = this.inputLine.substring(pos + 1, this.inputLine.length());
                this.field.setType(text);
                 
            }
             
            //pl("FIELD TYPE: " + this.field.getType());
             
        }
         
        catch (StringIndexOutOfBoundsException sioobe) {
             
            pl("String Index Out Of Bounds Exception " + sioobe.getMessage());
            this.field.setType(null);
             
        }
         
    }
 
    @Override
    public void determineFieldLength() {
        // TODO Auto-generated method stub
        this.trimLine();
         
        try {
         
            int pos = this.inputLine.indexOf("(");
             
            if (pos > 0) {
             
                String text = this.inputLine.substring(pos + 1, this.inputLine.length());
                pos = text.lastIndexOf(")");
                text = text.substring(0, pos);
                text = text.replaceAll(",", ".");
                this.field.setLength(text);
                //pl("FIELD LENGTH: " + this.field.getLength());
             
            }
             
            else {
                 
                this.field.setLength("32000");
                 
            }
             
        } 
         
        catch (NumberFormatException nfe) {
             
            pl("Number Format Exception " + nfe.getMessage());
            this.field.setLength(null);
             
        }
         
        catch (StringIndexOutOfBoundsException sioobe) {
             
            pl("String Index Out Of Bounds Exception " + sioobe.getMessage());
            this.field.setLength(null);
             
        }
         
    }
    
    @Override
    public void determinePrimaryKey() {
    	// TODO Auto-generated method stub
        this.trimLine();
    	
        try {
            
            int pos = this.inputLine.indexOf("PK");
            
            if (pos > 0) this.field.setPrimaryKey(true);
            else this.field.setPrimaryKey(false);
             
        } 
     
        catch (StringIndexOutOfBoundsException sioobe) {
             
            pl("String Index Out Of Bounds Exception " + sioobe.getMessage());
            this.field.setPrimaryKey(false);
             
        }
    	
    }
 
    @Override
    public Field getCompletedField() {
        // TODO Auto-generated method stub
        return this.field;
    }
 
    public String getInputLine() {
        return inputLine;
    }
 
    public void setInputLine(String inputLine) {
        this.inputLine = inputLine;
    }
 
    @SuppressWarnings("unused")
    private void p(String s) { System.out.print(s); }
     
    @SuppressWarnings("unused")
    private void pl() { System.out.println(); }
     
    private void pl(String s) { System.out.println(s); }
 
}
