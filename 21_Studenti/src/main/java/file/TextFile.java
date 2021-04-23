/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import eccezioni.FileException;
import java.io.*;

/**
 *
 * @author User
 */
public class TextFile 
{
    private char mode;
    private BufferedReader reader;
    private BufferedWriter writer;
    
    public TextFile(String nomeFile, char mode) throws FileNotFoundException, IOException
    {
        this.mode='R';
        if (mode=='w' || mode=='W')
                this.mode='W';
        if (this.mode=='R')
            reader=new BufferedReader(new FileReader(nomeFile));
        else
            writer=new BufferedWriter(new FileWriter(nomeFile));   
    }
    
    public TextFile(String nomeFile,char mode, boolean append) throws FileNotFoundException, IOException
    {
         this.mode='R';
        if (mode=='w' || mode=='W')
               this.mode='W';
        if (this.mode=='R')
            reader=new BufferedReader(new FileReader(nomeFile));
        else
            writer=new BufferedWriter(new FileWriter(nomeFile, append));     
    }
    
    public void toFile(String line) throws FileException, IOException
    {
        if (mode=='R')
            throw new FileException("Impossibile scrivere sul file. File aperto in lettura.");
        
        writer.write(line);
        writer.newLine();   
    }
    
    public String fromFile() throws FileException, IOException
    {
        String s;
        if (mode=='W')
            throw new FileException("Impossibile leggere dal file. File aperto in scrittura.");
        s=reader.readLine();
        if (s==null)
            throw new FileException("Fine del file");
        return s;   
    }
    
    public void close() throws IOException
    {
        if (mode=='R')
            reader.close();
        else
            writer.close();
    }
    
    
}
