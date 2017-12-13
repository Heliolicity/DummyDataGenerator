package com.bh25034.dummydatagenerator.test.preparer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bh25034.dummydatagenerator.entities.Field;
import com.bh25034.dummydatagenerator.parser.Parser;
import com.bh25034.dummydatagenerator.parser.impl.ParserImpl;
import com.bh25034.dummydatagenerator.preparer.Preparer;
import com.bh25034.dummydatagenerator.preparer.impl.PreparerImpl;
import com.bh25034.dummydatagenerator.processor.Processor;
import com.bh25034.dummydatagenerator.processor.impl.ProcessorImpl;
import com.bh25034.dummydatagenerator.randomer.Randomer;
import com.bh25034.dummydatagenerator.randomer.impl.RandomerImpl;

public class PreparerTest {

	@Test
	public void testPrepareDelimitedExportWorks() {

		String line = " COMPANY_NAME NOT NULL VARCHAR2(50) ";
		List<String> lines = new ArrayList<String>();
		lines.add(line);
		int rows = 1;
		String tableName = "TEST";
		boolean exportSQL = true;
		Parser parser = new ParserImpl();
        Randomer randomer = new RandomerImpl();
        Processor processor = new ProcessorImpl(lines, rows, parser, randomer);
        processor.parseDescription();
        processor.generateDummyData();
        List<List<Field>> fieldsWithData = processor.getDataMatrix();
        String dateFormat = "YYYY-MM-DD";
        Preparer preparer = new PreparerImpl(fieldsWithData, tableName, ",", exportSQL, dateFormat);
		preparer.prepareDelimitedExport();
		int inputSize = lines.size();
		int outputSize = preparer.getOutput().size();
		assertEquals(inputSize, outputSize);
		
	}

	@Test
	public void testPrepareSQLScriptsWorks() {
		
		String line = " COMPANY_NAME NOT NULL VARCHAR2(50) ";
		List<String> lines = new ArrayList<String>();
		lines.add(line);
		int rows = 1;
		String tableName = "TEST";
		boolean exportSQL = true;
		Parser parser = new ParserImpl();
        Randomer randomer = new RandomerImpl();
        Processor processor = new ProcessorImpl(lines, rows, parser, randomer);
        processor.parseDescription();
        processor.generateDummyData();
        List<List<Field>> fieldsWithData = processor.getDataMatrix();
        String dateFormat = "YYYY-MM-DD";
        Preparer preparer = new PreparerImpl(fieldsWithData, tableName, ",", exportSQL, dateFormat);
		preparer.prepareSQLScripts();
		int inputSize = lines.size();
		int outputSize = preparer.getOutput().size();
		assertEquals(inputSize, outputSize);
		
	}
	
}
