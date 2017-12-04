package com.bh25034.dummydatagenerator.randomer.impl;

import java.util.Random;

import com.bh25034.dummydatagenerator.randomer.Randomise;
 
public class RandomiseImpl implements Randomise {
 
    private Random randomGenerator;
     
    public RandomiseImpl() { 
        super(); 
        this.randomGenerator = new Random();
    }
     
    @Override
    public String generateRandomString(int length) {
        // TODO Auto-generated method stub
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder(length);
         
        for( int i = 0; i < length; i++ ) 
 
            stringBuilder.append( AB.charAt( this.randomGenerator.nextInt(AB.length()) ) );
            
        String data = stringBuilder.toString();
        return data;
    }
 
    @Override
    public int generateRandomInteger(int size) {
        // TODO Auto-generated method stub
        return this.randomGenerator.nextInt(size);
    }
 
 
 
    @Override
    public long generateRandomLong() {
        // TODO Auto-generated method stub
        return this.randomGenerator.nextLong();
    }
 
     
     
}