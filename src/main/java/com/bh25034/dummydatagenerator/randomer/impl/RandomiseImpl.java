package com.bh25034.dummydatagenerator.randomer.impl;

import java.util.Random;

import com.bh25034.dummydatagenerator.randomer.Randomise;
 
public class RandomiseImpl implements Randomise {
	
	private Random randomGenerator;
	private String alphabet;

	public RandomiseImpl(String alphabet) {
		super();
		this.alphabet = alphabet;
		this.randomGenerator = new Random();
	}

	public RandomiseImpl() { 
		super(); 
		this.randomGenerator = new Random();
	}

	@Override
	public String generateRandomString(int length) {
		// TODO Auto-generated method stub
		if (this.alphabet == null) this.alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		 
		StringBuilder stringBuilder = new StringBuilder(length);
		
		for (int i = 0; i < length; i ++) 
		
			stringBuilder.append(this.alphabet.charAt(this.randomGenerator.nextInt(this.alphabet.length())));
		
		String data = stringBuilder.toString();
		return data;
	 
	}

	@Override
	public int generateRandomInteger(int size) {
		return this.randomGenerator.nextInt(size);
	}


	@Override
	public long generateRandomLong() {
		// TODO Auto-generated method stub
		return this.randomGenerator.nextLong();
	}


	 public String getAlphabet() {
		 return alphabet;
	 }
	
	 public void setAlphabet(String alphabet) {
		 this.alphabet = alphabet;
	 }
	 
} 