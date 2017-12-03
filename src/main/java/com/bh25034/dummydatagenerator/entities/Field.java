package com.bh25034.dummydatagenerator.entities;

public class Field {

	private String name;
	private String type;
	private String length;
	private boolean primaryKey;
	private boolean notNull;
	private Data data;
	
	public Field(String name, String type, String length, boolean primaryKey, boolean notNull, Data data) {
		super();
		this.name = name;
		this.type = type;
		this.length = length;
		this.primaryKey = primaryKey;
		this.notNull = notNull;
		this.data = data;
	}
	
	public Field() { super(); }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	
	
}
