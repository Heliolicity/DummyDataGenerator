package com.bh25034.dummydatagenerator.test.filehandling;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.bh25034.dummydatagenerator.filehandling.Reader;

public class ReaderTest {

	@Test
	public void testReadWorks() {

		String path = "C:/Users/BobHi/Documents/Workspace/DummyDataGenerator/TEST_DESC.txt";
		Reader reader = new Reader(path);
		reader.read();
		assertTrue(reader.isReadFile());
		List<String> lines = reader.getLines();
		assertNotNull(lines);
		
	}

}
