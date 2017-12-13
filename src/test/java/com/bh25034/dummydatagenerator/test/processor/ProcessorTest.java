package com.bh25034.dummydatagenerator.test.processor;

import static org.junit.Assert.*;
		
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bh25034.dummydatagenerator.parser.Parser;
import com.bh25034.dummydatagenerator.parser.impl.ParserImpl;
import com.bh25034.dummydatagenerator.processor.Processor;
import com.bh25034.dummydatagenerator.processor.impl.ProcessorImpl;
import com.bh25034.dummydatagenerator.randomer.Randomer;
import com.bh25034.dummydatagenerator.randomer.impl.RandomerImpl;

public class ProcessorTest {

	@Test
	public void testParsingDocumentReturnsRightNumberOfRows() {
	 
		String line = " COMPANY_NAME NOT NULL VARCHAR2(50) ";
		List<String> lines = new ArrayList<String>();
		lines.add(line);
		int rows = 1;
		Parser parser = new ParserImpl();
		Randomer randomer = new RandomerImpl();
		Processor processor = new ProcessorImpl(lines, rows, parser, randomer);
		processor.parseDescription();
		processor.generateDummyData();
		assertEquals(rows, processor.getDataMatrix().size());
	 
	}

}