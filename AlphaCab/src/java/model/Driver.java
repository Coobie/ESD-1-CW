/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mherring
 */
public class Driver
{

    private int ID;
    private int user_id;
    private String firstName;
    private String lastName;
    private String username;
    private String reg; //Registration
    
    //needed for report
    private int totalJobs;
    private double totalMiles;
    private int totalIncome;

    public Driver(int driver_id, int user_id, String firstName, String lastName, String username, String reg)
    {
        this.ID = driver_id;
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.reg = reg;
        this.totalIncome = 0;
        this.totalJobs = 0;
        this.totalMiles = 0;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int driver_id)
    {
        this.ID = driver_id;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getReg()
    {
        return reg;
    }

    public void setReg(String reg)
    {
        this.reg = reg;
    }

    public int getTotalJobs()
    {
        return totalJobs;
    }

    public void setTotalJobs(int totalJobs)
    {
        this.totalJobs = totalJobs;
    }

    public double getTotalMiles()
    {
        return totalMiles;
    }

    public void setTotalMiles(double totalMiles)
    {
        this.totalMiles = totalMiles;
    }

    public int getTotalIncome()
    {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome)
    {
        this.totalIncome = totalIncome;
    }

    
}
