package com.bh25034.dummydatagenerator.preparer.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.bh25034.dummydatagenerator.entities.Data;
import com.bh25034.dummydatagenerator.entities.Field;
import com.bh25034.dummydatagenerator.preparer.Preparing;

public class PreparingImpl implements Preparing {

	private List<List<Field>> dataMatrix;
	private String tableName;
	private List<String> output;
	private String delimiter;
	
	public PreparingImpl(List<List<Field>> dataMatrix, String tableName, String delimiter) {
		super();
		this.dataMatrix = dataMatrix;
		this.tableName = tableName;
		this.delimiter = delimiter;
	}
	
	public PreparingImpl(List<List<Field>> dataMatrix) {
		super();
		this.dataMatrix = dataMatrix;
	}
	
	@Override
	public void prepareDelimitedExport() {
		// TODO Auto-generated method stub
		
		this.output = new ArrayList<String>();
		String quot = "'";
		String line = "";
		String strVal;
		DateTime dateVal;
		Timestamp tsVal;
		Integer intVal;
		Double dblVal;
		
		//pl("DATA MATRIX SIZE: " + this.dataMatrix.size());
		
		for (List<Field> listFields : this.dataMatrix) {
			
			line = "";
			
			for (Field field : listFields) {
				
				if (field.getType().equals("VARCHAR2") || field.getType().equals("VARCHAR")) {
				
					Data<String> data = field.getData();
					
					if (data != null) line += quot + data.get().toString() + quot;
					else line += quot + "" + quot;
					
				}
				
				else if (field.getType().equals("NUMBER")) {
					
					Data<Integer> data = field.getData();
					
					if (data != null) line += "" + data.get().intValue();
					else line += "";
					
				}
				
				else if (field.getType().equals("DATE")) {
					
					Data<DateTime> data = field.getData();
					
					if (data != null) line += quot + data.get() + quot;
					else line += quot + "" + quot;
				
				}
				
				else if (field.getType().equals("TIMESTAMP")) {
					
					Data<Timestamp> data = field.getData();
					
					if (data != null) line += "" + data.get().getTime() + "";
					else line += "";
					
				}
				
				else {
					
					Data<String> data = field.getData();
					
					if (data != null) line += quot + data.get().toString() + quot;
					else line += quot + "" + quot;
					
				}
				
				if (! (listFields.indexOf(field) == (listFields.size() - 1))) line += this.delimiter;
				
			}
			
			this.output.add(line);
			
		}
		
		//pl("OUTPUT SIZE: " + this.output.size());
		
	}

	@Override
	public void prepareSQLScripts() {
		// TODO Auto-generated method stub
		this.output = new ArrayList<String>();
		String quot = "'";
		String line = "";
		String insertLine = "";
		String strVal;
		DateTime dateVal;
		Timestamp tsVal;
		Integer intVal;
		Double dblVal;
		String newString;
		String fieldNames;
		List<Field> listFields;
		
		insertLine = "INSERT INTO " + this.tableName + " (";
		
		listFields = this.dataMatrix.get(0); 
		fieldNames = "";
		
		for (Field field : listFields) 
				
			fieldNames += field.getName() + ",";
		
		insertLine += fieldNames;
		insertLine = insertLine.substring(0, insertLine.length() - 1);
		insertLine += ") VALUES (";
		this.prepareDelimitedExport();
		List<String> newOutput = new ArrayList<String>();
		
		for (String string : this.output) {
			
			newString = insertLine + string + ");";
			newOutput.add(newString);
			//pl(newString);
			
		}
		
		this.output = newOutput;
		
	}

	@Override
	public List<List<Field>> getDataMatrix() {
		// TODO Auto-generated method stub
		return dataMatrix;
	}

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
	public String getDelimiter() {
		// TODO Auto-generated method stub
		return this.delimiter;
	}

	@Override
	public void setDelimiter(String delimiter) {
		// TODO Auto-generated method stub
		this.delimiter = delimiter;
	}

	/*public void setDataMatrix(List<List<Field>> dataMatrix) {
		this.dataMatrix = dataMatrix;
	}*/

	@SuppressWarnings("unused")
	private void p(String s) { System.out.print(s); }
	
	@SuppressWarnings("unused")
	private void pl() { System.out.println(); }
	
	private void pl(String s) { System.out.println(s); }
	
}
