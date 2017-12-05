package com.bh25034.dummydatagenerator.test.filehandling;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bh25034.dummydatagenerator.filehandling.Writer;

public class WriterTest {

	@Test
	public void testWriteWorks() {

		String output = "INSERT INTO TA_USER.TEST_TA_PROCESSES (PROCESSID,PROCESSNAME,PROCESSDESCRIPTION,PROCESSDATASET,CREATEDON,CREATEDBY) VALUES (1335,'2JDBr9DAB6','K2gJup2Aouxto7uaDp6PWGt2qf7fS9QAozxGsZNElbVJhfeJe6Bbc4OZeIe0TQpYDosLHXequ1ljG6V6BoWaIDz1o00erQqWW3MN','GzFkbrIOAHJwN',TO_DATE('2017-12-04', 'yyyy-mm-dd'),'yUtmtd2nCkpOn9MZH1cz');";
		List<String> lines = new ArrayList<String>();
		lines.add(output);
		String path = "C:/Users/BobHi/Documents/Workspace/DummyDataGenerator/TEST_SCRIPTS.txt";
		Writer writer = new Writer(path, lines);
		writer.write();
		assertTrue(writer.isWroteFile());
		
	}

}
