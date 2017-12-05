package com.bh25034.dummydatagenerator.test.preparer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bh25034.dummydatagenerator.entities.Field;
import com.bh25034.dummydatagenerator.parser.Parsing;
import com.bh25034.dummydatagenerator.parser.impl.ParsingImpl;
import com.bh25034.dummydatagenerator.preparer.Preparing;
import com.bh25034.dummydatagenerator.preparer.impl.PreparingImpl;
import com.bh25034.dummydatagenerator.processor.Processing;
import com.bh25034.dummydatagenerator.processor.impl.ProcessingImpl;
import com.bh25034.dummydatagenerator.randomer.Randomise;
import com.bh25034.dummydatagenerator.randomer.impl.RandomiseImpl;

public class PreparerTest {

	@Test
	public void testPrepareDelimitedExportWorks() {

		String line = " COMPANY_NAME NOT NULL VARCHAR2(50) ";
		List<String> lines = new ArrayList<String>();
		lines.add(line);
		int rows = 1;
		String tableName = "TEST";
		boolean exportSQL = true;
		Parsing parser = new ParsingImpl();
        Randomise randomer = new RandomiseImpl();
        Processing processor = new ProcessingImpl(lines, rows, parser, randomer);
        processor.parseDescription();
        processor.generateDummyData();
        List<List<Field>> fieldsWithData = processor.getDataMatrix();
        String dateFormat = "YYYY-MM-DD";
        Preparing preparer = new PreparingImpl(fieldsWithData, tableName, ",", exportSQL, dateFormat);
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
		Parsing parser = new ParsingImpl();
        Randomise randomer = new RandomiseImpl();
        Processing processor = new ProcessingImpl(lines, rows, parser, randomer);
        processor.parseDescription();
        processor.generateDummyData();
        List<List<Field>> fieldsWithData = processor.getDataMatrix();
        String dateFormat = "YYYY-MM-DD";
        Preparing preparer = new PreparingImpl(fieldsWithData, tableName, ",", exportSQL, dateFormat);
		preparer.prepareSQLScripts();
		int inputSize = lines.size();
		int outputSize = preparer.getOutput().size();
		assertEquals(inputSize, outputSize);
		
	}
	
}
