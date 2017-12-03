package com.bh25034.dummydatagenerator.filehandling;

/*
 * Uses code from:
 * https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/
 * 
 * */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

	private String path;
	private BufferedReader bufferedReader;
	private FileReader fileReader;
	private List<String> lines;
	private boolean readFile;
    
	public Reader(String path) {
		super();
		this.path = path;
		this.readFile = false;
	}

	public void read() {
		
		this.readFile = false;
		this.lines = new ArrayList<String>();
		
		try {
			
			this.fileReader = new FileReader(this.path);
			this.bufferedReader = new BufferedReader(this.fileReader);
			
			String line = "";
			
			while ((line = this.bufferedReader.readLine()) != null) {
			
				this.lines.add(line);
				//pl(line);
			
			}
			
			this.readFile = true;
			
		} 
		
		catch (FileNotFoundException fnfe) {

			pl("File Not Found Exception " + fnfe.getMessage());
			this.readFile = false;
			
		} 
		
		catch (IOException ioe) {
			
			pl("IO Exception " + ioe.getMessage());
			this.readFile = false;
			
		}
		
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public List<String> getLines() {
		return this.lines;
	}
	
	public boolean isReadFile() {
		return this.readFile;
	}
	
	@SuppressWarnings("unused")
	private void p(String s) { System.out.print(s); }
	
	@SuppressWarnings("unused")
	private void pl() { System.out.println(); }
	
	private void pl(String s) { System.out.println(s); }
	
}
