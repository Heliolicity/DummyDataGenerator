package com.bh25034.dummydatagenerator.test.randomer;

import static org.junit.Assert.*;
		
import org.junit.Test;

import com.bh25034.dummydatagenerator.randomer.Randomer;
import com.bh25034.dummydatagenerator.randomer.impl.RandomerImpl;

public class RandomiseTest {

	@Test
	public void testRandomStringDoesNotExceedPassedLength() {
	
		Randomer randomise = new RandomerImpl();
		assertEquals(5, randomise.generateRandomString(5).length());
		assertNotEquals(6, randomise.generateRandomString(5).length());
	 
	}
	
	@Test
	public void testRandomIntegerDoesNotExceedPassedLength() {
	 
		Randomer randomise = new RandomerImpl();
		assertNotEquals(7, randomise.generateRandomString(6));
		assertNotEquals(-1, randomise.generateRandomString(6));
	 
	}

}