package com.bh25034.dummydatagenerator.test.parser;

import static org.junit.Assert.*;
		
import org.junit.Test;

import com.bh25034.dummydatagenerator.entities.Field;
import com.bh25034.dummydatagenerator.parser.Parser;
import com.bh25034.dummydatagenerator.parser.impl.ParserImpl;

public class ParserTest {

	@Test
	public void testDetermineFieldNameNotNull() {
	
		String line = " COMPANY_NAME NOT NULL VARCHAR2(50) ";
		Parser parser = new ParserImpl();
		parser.setLine(line);
		parser.determineFieldName();
		Field field = parser.getCompletedField();
		assertNotNull(field.getName());
	 
	}

	@Test
	public void testDetermineFieldNameWorks() {
	
		String line = " COMPANY_NAME NOT NULL VARCHAR2(50) ";
		Parser parser = new ParserImpl();
		parser.setLine(line);
		parser.determineFieldName();
		assertEquals("COMPANY_NAME", parser.getCompletedField().getName());
		 
	}
	
	@Test
	public void testDetermineNotNullWorks() {
	
		String line = " COMPANY_NAME NOT NULL VARCHAR2(50) ";
		Parser parser = new ParserImpl();
		parser.setLine(line);
		parser.determineNotNull();
		assertTrue(parser.getCompletedField().isNotNull());
	 
	}
	 
	@Test
	public void testDetermineFieldTypeNotNull() {
	 
		String line = " COMPANY_NAME NOT NULL VARCHAR2(50) ";
		Parser parser = new ParserImpl();
		parser.setLine(line);
		parser.determineFieldType();
		assertNotNull(parser.getCompletedField().getType());
		
	}
	 
	@Test
	public void testDetermineFieldTypeWorks() {
	 
		String line = " COMPANY_NAME NOT NULL VARCHAR2(50) ";
		Parser parser = new ParserImpl();
		parser.setLine(line);
		parser.determineFieldType();
		assertEquals("VARCHAR2", parser.getCompletedField().getType());
		 
	}
	 
	@Test
	public void testDetermindFieldLengthNotNull() {
	 
		String line = " COMPANY_NAME NOT NULL VARCHAR2(50) ";
		Parser parser = new ParserImpl();
		parser.setLine(line);
		parser.determineFieldLength();
		assertNotNull(parser.getCompletedField().getLength());
	 
	}
	 
	@Test
	public void testDetermindFieldLengthWorks() {
	 
		String line = " COMPANY_NAME NOT NULL VARCHAR2(50) ";
		Parser parser = new ParserImpl();
		parser.setLine(line);
		parser.determineFieldLength();
		float var = 50;
		assertTrue(var == parser.getCompletedField().getLength());
	 
	}
 
}