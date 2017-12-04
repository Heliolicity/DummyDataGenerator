package com.bh25034.dummydatagenerator.test.randomer;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bh25034.dummydatagenerator.randomer.Randomise;
import com.bh25034.dummydatagenerator.randomer.impl.RandomiseImpl;

public class RandomiseTest {

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	@Test
	public void testRandomStringDoesNotExceedPassedLength() {
		
		Randomise randomise = new RandomiseImpl();
		assertEquals(5, randomise.generateRandomString(5).length());
		assertNotEquals(6, randomise.generateRandomString(5).length());
		
	}
	
	@Test
	public void testRandomIntegerDoesNotExceedPassedLength() {
		
		Randomise randomise = new RandomiseImpl();
		assertNotEquals(7, randomise.generateRandomString(6));
		assertNotEquals(-1, randomise.generateRandomString(6));
		
	}

	
	
}
