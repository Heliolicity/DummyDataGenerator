package com.bh25034.dummydatagenerator.filehandling;

/*
 * Uses code from:
 * https://www.mkyong.com/java8/java-8-stream-read-a-file-line-by-line/
 * 
 * */
 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {
 
    private String path;
    private List<String> lines;
    private boolean fileWasRead;
     
    public Reader(String path) {
        super();
        this.path = path;
        this.fileWasRead = false;
    }
 
    public void read() {
         
        this.fileWasRead = false;
        this.lines = new ArrayList<String>();
         
        try (Stream<String> stream = Files.lines(Paths.get(this.path))) {

			this.lines = stream.collect(Collectors.toList());
			this.fileWasRead = true;
			
        }
         
        catch (IOException ioe) {
             
            System.err.println("IO Exception " + ioe.getMessage());
            this.fileWasRead = false;
             
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
        return this.fileWasRead;
    }
     
}