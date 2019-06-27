/*
 * Licensed to JGC
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Driver;
import model.User;

/**
 *
 * @author Jacob
 */
public class DriverDao
{

    public Driver findDriverByID(int ID)
    {
        Driver driver = null;

        try
        {
            DataBase db = new DataBase();

            //Statement to find all drivers
            PreparedStatement stmt = db.getCon().prepareStatement("select * from driver inner join users on driver.USER_ID = users.ID where driver.id = ?");
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {  //iterate through response creating new drivers and adding to list
                driver = new Driver(rs.getInt("id"), rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("registration"));
                //break;
            }

            stmt.close();
            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }

        return driver;
    }
    
    public Driver findDriverByUserID(int ID)
    {
        Driver driver = null;

        try
        {
            DataBase db = new DataBase();

            //Statement to find all drivers
            PreparedStatement stmt = db.getCon().prepareStatement("select * from driver inner join users on driver.USER_ID = users.ID where users.ID = ?");
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {  //iterate through response creating new drivers and adding to list
                driver = new Driver(rs.getInt("id"), rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("registration"));
                //break;
            }

            stmt.close();
            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }

        return driver;
    }

    public List<Driver> getAllDrivers()
    {
        List<Driver> drivers = new ArrayList<>();
        try
        {
            DataBase db = new DataBase();

            //Statement to find all drivers
            PreparedStatement stmt = db.getCon().prepareStatement("select * from driver inner join users on driver.USER_ID = users.ID");
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response creating new drivers and adding to list
                Driver d = new Driver(rs.getInt("id"), rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("registration"));
                drivers.add(d);
            }

            stmt.close();
            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }

        return drivers;
    }

    public boolean removeDriver(Driver d)
    {
        boolean success = false;
        try
        {
            DataBase db = new DataBase();
            PreparedStatement stmt = db.getCon().prepareStatement("DELETE FROM driver WHERE id = ?");
            stmt.setInt(1, d.getID());
            stmt.executeUpdate();
            stmt.close();

            PreparedStatement stmt2 = db.getCon().prepareStatement("DELETE FROM users WHERE id = ?");
            stmt2.setInt(1, d.getUser_id());
            stmt2.executeUpdate();
            stmt2.close();

            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }

        return success;
    }

    public void updateDriver(Driver d)
    {
        try
        {
            //Update users table
            DataBase db = new DataBase();
            PreparedStatement stmt = db.getCon().prepareStatement("UPDATE users set username = ?, first_name = ?, last_name = ? WHERE id = ?");
            stmt.setString(1, d.getUsername());
            stmt.setString(2, d.getFirstName());
            stmt.setString(3, d.getLastName());
            stmt.setInt(4, d.getUser_id());

            // execute insert SQL stetement
            stmt.executeUpdate();

            stmt.close();

            //Update driver table
            PreparedStatement stmt2 = db.getCon().prepareStatement("UPDATE driver set registration = ? WHERE id = ?");
            stmt2.setString(1, d.getReg());
            stmt2.setInt(2, d.getID());

            stmt2.executeUpdate();

            stmt2.close();

            db.getCon().close();

        } catch (SQLException ex)
        {
            Logger.getLogger(UsersDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("FAILED IN UPDATING DRIVER");
        }
    }

    public void newDriver(String reg, int userID)
    {
        try
        {
            DataBase db = new DataBase();
            PreparedStatement stmt = db.getCon().prepareStatement("INSERT INTO driver(REGISTRATION, USER_ID) VALUES (?, ?)");
            stmt.setString(1, reg);
            stmt.setInt(2, userID);

            // execute insert SQL stetement
            stmt.executeUpdate();

            stmt.close();
            db.getCon().close();

        } catch (SQLException ex)
        {
            Logger.getLogger(UsersDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("FAILED IN CREATING DRIVER");
        }
    }
}
