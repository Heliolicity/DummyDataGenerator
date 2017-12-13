package com.bh25034.dummydatagenerator.parser;

import com.bh25034.dummydatagenerator.entities.Field;

public interface Parser {
 
    public void setLine(String line);
     
    public void trimLine();
     
    public void determineFieldName();
     
    public void determineNotNull();
     
    public void determineFieldType();
     
    public void determineFieldLength();
    
    public void determinePrimaryKey();
     
    public Field getCompletedField();
     
}
