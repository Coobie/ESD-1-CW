/*
 * Licensed to JGC
 */
package model;

import dao.CustomerDao;
import dao.DriverDao;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Jacob
 */
public class Journey
{
    private int id;
    private int customer_id;
    private int driver_id;
    private String start;
    private String end;
    private double distance;
    private int status;
    private Time time;
    private Date date;
    private Driver driver;
    private Customer customer;
    private int fee;

    public Journey(int id, int customer_id, int driver_id, String start, String end, double distance, int status, Time time, Date date, int fee)
    {
        this.id = id;
        this.customer_id = customer_id;
        this.driver_id = driver_id;
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.status = status;
        this.time = time;
        this.date = date;
        this.fee = fee;
        
        // Look up the driver and the customer
        this.driver = new DriverDao().findDriverByID(this.driver_id);
        this.customer = new CustomerDao().findCustomerByID(this.customer_id);
    }

    public Journey(String start, String end, double distance, int status, Time time, Date date, Driver d, Customer c, int fee)
    {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.status = status;
        this.time = time;
        this.date = date;
        this.driver = d;
        this.customer = c;
        this.fee = fee;
    }

   
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }

    public int getDriver_id()
    {
        return driver_id;
    }

    public void setDriver_id(int driver_id)
    {
        this.driver_id = driver_id;
    }

    public String getStart()
    {
        return start;
    }

    public void setStart(String start)
    {
        this.start = start;
    }

    public String getEnd()
    {
        return end;
    }

    public void setEnd(String end)
    {
        this.end = end;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public Time getTime()
    {
        return time;
    }

    public void setTime(Time time)
    {
        this.time = time;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Driver getDriver()
    {
        return driver;
    }

    public void setDriver(Driver driver)
    {
        this.driver = driver;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public int getFee()
    {
        return fee;
    }

    public void setFee(int fee)
    {
        this.fee = fee;
    }

    
    
    
}
