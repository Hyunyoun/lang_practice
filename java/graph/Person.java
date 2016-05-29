/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network_ex1;

/**
 *
 * @author Pposong
 */
 public class Person
{
    public static final int N_MAX_PERSON = 1000;
    
    private String name;
    private int[] relation = new int[N_MAX_PERSON];

    
    public void setPerson(String newName)
    {
        name = newName;

        for (int n = 0; n < N_MAX_PERSON; n++)
            relation[n] = 0;
    }
    public void setRelation(int indexOfPerson)
    {
        relation[indexOfPerson] = 1;
    }
    public String getName()
    {
        return name;
    }
    public int getRelation(int n)
    {
        return relation[n];
    }
 }
