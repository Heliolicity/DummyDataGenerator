package com.bh25034.dummydatagenerator.processor.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.bh25034.dummydatagenerator.entities.Data;
import com.bh25034.dummydatagenerator.entities.Field;
import com.bh25034.dummydatagenerator.parser.Parsing;
import com.bh25034.dummydatagenerator.processor.Processing;
import com.bh25034.dummydatagenerator.randomer.Randomise;

public class ProcessingImpl implements Processing {

	private List<String> lines;
	private List<Field> fields;
	private List<Field> fieldsWithData;
	private List<List<Field>> dataMatrix;
	private int rows;
	private Parsing parser;
	private Randomise randomer;
	
	public ProcessingImpl(List<String> lines, int rows, Parsing parser, Randomise randomer) {
		super();
		this.lines = lines;
		this.rows = rows;
		this.parser = parser;
		this.randomer = randomer;
	}
	
	@Override
	public void parseDescription() {
		// TODO Auto-generated method stub
		this.fields = new ArrayList<Field>();
		
		Field field;
		
		if (this.lines != null) {
			
			for (String line : this.lines) {
				
				this.parser.setLine(line);
				this.parser.determineFieldName();
				this.parser.determineNotNull();
				this.parser.determineFieldType();
				this.parser.determineFieldLength();
				field = this.parser.getCompletedField();
				this.fields.add(field);
				
			}
			
		}
		
	}

	@Override
	public void generateDummyData() {
		// TODO Auto-generated method stub
		this.dataMatrix = new ArrayList<List<Field>>();
		
		int size;
		Field newField;
		
		try {
			
			if (this.fields != null) {
			
				for (int i = 0; i < this.rows; i ++) {
					
					this.fieldsWithData = new ArrayList<Field>();
					
					for (Field field : this.fields) {
						
						newField = new Field();
						newField.setName(field.getName());
						newField.setNotNull(field.isNotNull());
						newField.setType(field.getType());
						newField.setLength(field.getLength());
						
						if (field.getLength() != null) size = (int) new Double(field.getLength()).doubleValue();
						else size = 1;
						
						if (field.getType().toUpperCase().equals("NUMBER")) {
							
							Data<Integer> data = new Data<Integer>();
							Integer randInt = new Integer(this.randomer.generateRandomInteger(size));
							data.set(randInt);
							newField.setData(data);
							
						} 
						
						else if (field.getType().toUpperCase().equals("DATE")) {
						
							Data<DateTime> data = new Data<DateTime>();
							DateTime dateTime = new DateTime();
							data.set(dateTime);
							newField.setData(data);
							
						}
						
						else if (field.getType().toUpperCase().equals("VARCHAR2")) {
							
							Data<String> data = new Data<String>();
							String randStr = this.randomer.generateRandomString(size);
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
							String randStr = this.randomer.generateRandomString(size);
							data.set(randStr);
							newField.setData(data);
							
						}
					
						this.fieldsWithData.add(newField);
						
					}
					
					this.dataMatrix.add(this.fieldsWithData);
					
				}
				
			}
		
		} 
		
		catch (NumberFormatException nfe) {
			
			pl("Number Format Exception " + nfe.getMessage());
			nfe.printStackTrace();
			
		} 
		
	}
	
	public void printData() {
		
		pl("PRINTING DATA NOW");
		
		Data data;
		Object o;
		
		for (List<Field> listFields : this.dataMatrix) {
			
			for (Field field : listFields) {
				
				p("NAME: " + field.getName() + " ");
				data = field.getData();
				
				if (data != null) o = field.getData().get();
				else o = new Object();
				
				if (o == null) p("DATA: null ");
				else p("DATA: " + o + " ");
				
				//p("DATA:"  + field.getData() + " ");
				this.fieldsWithData.add(field);
				
			}
			
			pl();
			
		}
		
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

	public Parsing getParser() {
		return parser;
	}

	public void setParser(Parsing parser) {
		this.parser = parser;
	}

	private void p(String s) { System.out.print(s); }
	
	private void pl() { System.out.println(); }
	
	private void pl(String s) { System.out.println(s); }
	
}
