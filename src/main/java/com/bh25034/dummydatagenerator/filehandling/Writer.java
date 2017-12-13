package com.bh25034.dummydatagenerator.filehandling;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
 
public class Writer {
 
    private FileWriter fileWriter;
    private PrintWriter printWriter;
    private String path;
    private List<String> lines;
    private boolean wroteFile;
     
    public Writer(String path, List<String> lines) {
        super();
        this.path = path;
        this.lines = lines;
        this.wroteFile = false;
    }
     
    public void write() {
         
        this.wroteFile = false;
         
        try {
         
            this.fileWriter = new FileWriter(this.path);
            this.printWriter = new PrintWriter(this.fileWriter);
             
            for (String line : this.lines) {
              
            	this.printWriter.write(line + "\n");
             
            }
            
            this.printWriter.close();
            this.wroteFile = true;
             
        }
         
        catch (IOException ioe) {
             
        	System.err.println(ioe.getMessage());
            this.printWriter.close();
            this.wroteFile = false;
             
        }
         
    }
 
    public String getPath() {
        return path;
    }
 
    public void setPath(String path) {
        this.path = path;
    }
 
    public List<String> getLines() {
        return lines;
    }
 
    public void setLines(List<String> lines) {
        this.lines = lines;
    }
     
    public boolean isWroteFile() {
        return wroteFile;
    }
 
    /*public void setWroteFile(boolean wroteFile) {
        this.wroteFile = wroteFile;
    }*/
     
}