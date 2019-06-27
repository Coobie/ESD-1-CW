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
public class Customer {
    private int ID;
    private int user_id;
    private String firstName;
    private String lastName;
    private String username;
    private String address;

    public Customer(int customer_id, int user_id, String firstName, String lastName, String username, String address)
    {
        this.ID = customer_id;
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.address = address;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int customer_id)
    {
        this.ID = customer_id;
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
}
