package com.bh25034.dummydatagenerator.test.processor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bh25034.dummydatagenerator.parser.Parsing;
import com.bh25034.dummydatagenerator.parser.impl.ParsingImpl;
import com.bh25034.dummydatagenerator.processor.Processing;
import com.bh25034.dummydatagenerator.processor.impl.ProcessingImpl;
import com.bh25034.dummydatagenerator.randomer.Randomise;
import com.bh25034.dummydatagenerator.randomer.impl.RandomiseImpl;

public class ProcessorTest {

	@Test
	public void testParsingDocumentReturnsRightNumberOfRows() {
		
		String line = " COMPANY_NAME		NOT NULL	VARCHAR2(50) ";
		List<String> lines = new ArrayList<String>();
		lines.add(line);
		int rows = 1;
		Parsing parser = new ParsingImpl();
		Randomise randomer = new RandomiseImpl();
		Processing processor = new ProcessingImpl(lines, rows, parser, randomer);
		processor.parseDescription();
		processor.generateDummyData();
		assertEquals(rows, processor.getDataMatrix().size());
		
	}

}
