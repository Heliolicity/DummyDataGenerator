package com.bh25034.dummydatagenerator.randomer.impl;

import java.util.Random;

import com.bh25034.dummydatagenerator.randomer.Randomer;
 
public class RandomerImpl implements Randomer {
	
	private Random randomGenerator;
	private String alphabet;

	public RandomerImpl(String alphabet) {
		super();
		this.alphabet = alphabet;
		this.randomGenerator = new Random();
	}

	public RandomerImpl() { 
		super(); 
		this.randomGenerator = new Random();
	}

	@Override
	public String generateRandomString(int length) {
		
		if (this.alphabet == null) {
			
			this.alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			
		}
		 
		StringBuilder stringBuilder = new StringBuilder(length);
		
		for (int i = 0; i < length; i ++) {
		
			stringBuilder.append(this.alphabet.charAt(this.randomGenerator.nextInt(this.alphabet.length())));
			
		}
		
		String data = stringBuilder.toString();
		return data;
	 
	}

	@Override
	public int generateRandomInteger(int size) {
		return this.randomGenerator.nextInt(size);
	}

	@Override
	public long generateRandomLong() { return this.randomGenerator.nextLong(); }

	public String getAlphabet() {
		return alphabet;
	}
	
	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}
 
} 