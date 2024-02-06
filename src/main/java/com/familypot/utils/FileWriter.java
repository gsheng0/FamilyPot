package com.familypot.utils;

public class FileWriter {
    private FileWriter(){}
    private static FileWriter instance;
    private String filename = "";
    public static FileWriter getInstance(){
        if(instance == null){
            instance = new FileWriter();
        }
        return instance;
    }
    public void writeln(String string){
        if(filename.length() > 0){
            FileUtils.writeToFile(filename, string);
        }
        FileUtils.writeToFile(filename,"\n");
    }
    public void write(String string){
        if(filename.length() > 0){
            FileUtils.writeToFile(filename, string);
        }
    }
    public void setFilename(String filename){
        this.filename = "/Users/gsheng/Downloads/" + filename + ".txt";
    }
    public void randomizeFilename(){
        setFilename(FileUtils.generateFilename());
    }
}
