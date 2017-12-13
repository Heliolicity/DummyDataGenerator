package com.bh25034.dummydatagenerator.processor;

import java.util.List;

import com.bh25034.dummydatagenerator.entities.Field;
 
public interface Processor {
 
    public void parseDescription();
     
    public void generateDummyData();
     
    public List<List<Field>> getDataMatrix();
     
    //public void printData();
     
}