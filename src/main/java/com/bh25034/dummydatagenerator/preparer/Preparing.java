package com.bh25034.dummydatagenerator.preparer;

import java.util.List;

import com.bh25034.dummydatagenerator.entities.Field;

public interface Preparing {

	public void prepareDelimitedExport();
	
	public void prepareSQLScripts();
	
	public List<List<Field>> getDataMatrix();
	
	public String getDelimiter();
	
	public void setDelimiter(String delimiter);
	
	public List<String> getOutput();
	
}
