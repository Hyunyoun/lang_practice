/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network_ex1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Pposong
 */
public class Network_Ex1 extends Person {

//    public static final int N_MAX_PERSON = 1000;
    private Person[] people = new Person[N_MAX_PERSON];
    
    private BufferedReader bufferedReader = null;
    private BufferedWriter bufferedWriter = null;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        Network_Ex1 exercise = new Network_Ex1();
        
        exercise.connectToInputFile();
        exercise.connectToOutputFile();
        exercise.storeInput();        
        exercise.doCommand();
        exercise.closeFiles();
    }    
    
    public void connectToInputFile()
    {
        String inputFileName = getFileName("Input File: ");
        
        try
        {
            bufferedReader = new BufferedReader(
                            new FileReader(inputFileName));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File " + inputFileName + " not found.");
            System.exit(0);
        }
        catch(Exception e)
        {
            System.out.println("Error: opening input file " + inputFileName);
            System.exit(0);
        }
    }
    
    private String getFileName(String prompt)
    {
        String fileName;
        System.out.println(prompt);
        
        Scanner keyboard = new Scanner(System.in);
        fileName = keyboard.next();
        
        return fileName;        
    }
    
    public void connectToOutputFile()
    {
        String outputFileName = getFileName("Output File Name: ");
        
        try
        {
        bufferedWriter = new BufferedWriter(
                            new FileWriter(outputFileName));
        }
        catch(IOException e)
        {
            System.out.println("Error: opening output file " + outputFileName);            
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    
    public void storeInput()
    {
        int n = 0;
        int m;
        String inputLine;
        String name1, name2;
        int index1, index2;
        
        try
        {
            while (true)
            {
                inputLine = bufferedReader.readLine();
                
                if (inputLine.equals("$"))
                    break;
                    
                m = 0;
                while (m < n)
                {
                    if (inputLine.equals(people[m].getName()))
                    {
                        System.out.println("Error: duplicated name");            
                        System.exit(0);                        
                    }
                    m++;
                }
                
                people[n].setPerson(inputLine);                
            }
            
            while(true)
            {
                inputLine = bufferedReader.readLine();
                
                name1 = inputLine.substring(0, inputLine.indexOf(" "));
                name2 = inputLine.substring(inputLine.indexOf(" ") + 1);
                
                index1 = getIndex(people, name1);
                index2 = getIndex(people, name2);
                
                people[index1].setRelation(index2);
                people[index2].setRelation(index1);
            }    
        }

        catch(IOException e)
        {
            System.out.println("Error: reading file");            
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    
    private int getIndex(Person[] people, String targetName)
    {
        int n = 0;
        int targetIndex = 0;
        
        while (n < N_MAX_PERSON)
        {
            if (targetName.equals(people[n].getName()))
                targetIndex = n;
            n++;
        }
        
        if (n == N_MAX_PERSON)
        {
            System.out.println("Error: no person in there");
            System.exit(0);
        }
        
        return targetIndex;        
    }
    
    public void doCommand()
    {
        Scanner keyboard = new Scanner(System.in);
        String commandLine, commandType;
        int pos1, pos2;
        int index1, index2;
        int n;
        
        try
        {
            do
            {
                commandLine = keyboard.nextLine();

                bufferedWriter.write(commandLine + "\n");

                pos1 = commandLine.indexOf(" ");
                pos2 = commandLine.indexOf(" ", pos1 + 1);

                commandType = commandLine.substring(0, pos1);
                index1 = getIndex(people, 
                        commandLine.substring(pos1 + 1, pos2 - pos1 - 1));
                index2 = getIndex(people, 
                        commandLine.substring(pos2 + 1));

                if (commandType.equalsIgnoreCase("isfreind"))
                {
                    if (people[index1].getRelation(index2) == 1)
                        bufferedWriter.write("yes\n");
                    else
                        bufferedWriter.write("no\n");
                }
                else if (commandType.equalsIgnoreCase("mutual"))
                {
                    n = 0;
                    
                    while (people[n].getName() != null && n < N_MAX_PERSON)
                    {
                        if (people[index1].getRelation(n) == 1)
                        {
                            if (people[index2].getRelation(n) == 1)
                                bufferedWriter.write(people[n].getName() + "\n");
                        }

                        n++;
                    }
                }
                else if (commandType.equalsIgnoreCase("relation"))
                {
                    // Fill In Here
                    // Using doubly linked list
                }
                else
                {
                    bufferedWriter.write("Error: wrong command\n");
                }

                bufferedWriter.write("$\n");                    
            }
            while (commandLine.equalsIgnoreCase("quit"));            
        }
        catch(EOFException e)
        {
            // Do Nothing
        }
        catch(IOException e)
        {
            System.out.println("Error: reading file");            
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    
    public void closeFiles()
    {
        try
        {
            bufferedReader.close();
            bufferedWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("Error: closing files " + e.getMessage());            
            System.exit(0);            
        }
    }
}
