/*
 * Licensed to JGC
 */
package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Journey;

/**
 *
 * @author Jacob
 */
public class JourneyDao
{

    public Journey findJourneyByID(int id)
    {
        Journey j = null;

        try
        {
            DataBase db = new DataBase();

            //Statement to find all drivers
            PreparedStatement stmt = db.getCon().prepareStatement("select * from journey inner join driver on journey.DRIVER_ID "
                    + "= driver.id inner join customer on journey.CUSTOMER_ID "
                    + "= customer.ID where journey.id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response and building journey list
                j = new Journey(rs.getInt("id"), rs.getInt("customer_id"),
                        rs.getInt("driver_id"), rs.getString("start"),
                        rs.getString("destination"), rs.getDouble("distance"),
                        rs.getInt("status"), rs.getTime("time"),
                        rs.getDate("date"), rs.getInt("fee"));
            }

            stmt.close();
            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        return j;
    }
    
    public List<Journey> findJourneysByCustomerID(int customerID)
    {
        int id = customerID;

        ArrayList<Journey> js = new ArrayList<>();
        try
        {
            DataBase db = new DataBase();

            //Statement to find all drivers
            PreparedStatement stmt = db.getCon().prepareStatement("select * from journey inner join driver on journey.DRIVER_ID "
                    + "= driver.id inner join customer on journey.CUSTOMER_ID "
                    + "= customer.ID where customer.id = ? order by journey.date, journey.time");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response and building journey list
                js.add(new Journey(rs.getInt("id"), rs.getInt("customer_id"),
                        rs.getInt("driver_id"), rs.getString("start"),
                        rs.getString("destination"), rs.getDouble("distance"),
                        rs.getInt("status"), rs.getTime("time"),
                        rs.getDate("date"), rs.getInt("fee")));
            }

            stmt.close();
            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        return js;
    }

    public List<Journey> getJourneys()
    {
        ArrayList<Journey> js = new ArrayList<>();
        try
        {
            DataBase db = new DataBase();

            //Statement to find all drivers
            PreparedStatement stmt = db.getCon().prepareStatement("select * from journey inner join driver on journey.DRIVER_ID = driver.id inner join customer on journey.CUSTOMER_ID = customer.ID order by journey.date, journey.time");
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response and building journey list
                js.add(new Journey(rs.getInt("id"), rs.getInt("customer_id"),
                        rs.getInt("driver_id"), rs.getString("start"),
                        rs.getString("destination"), rs.getDouble("distance"),
                        rs.getInt("status"), rs.getTime("time"),
                        rs.getDate("date"), rs.getInt("fee")));
            }

            stmt.close();
            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        return js;
    }

    public List<Journey> findJourneysByDriverID(int driverID)
    {
        int id = driverID;

        ArrayList<Journey> js = new ArrayList<>();
        try
        {
            DataBase db = new DataBase();

            //Statement to find all drivers
            PreparedStatement stmt = db.getCon().prepareStatement("select * from journey inner join driver on journey.DRIVER_ID "
                    + "= driver.id inner join customer on journey.CUSTOMER_ID "
                    + "= customer.ID where driver.id = ? order by journey.date, journey.time");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response and building journey list
                js.add(new Journey(rs.getInt("id"), rs.getInt("customer_id"),
                        rs.getInt("driver_id"), rs.getString("start"),
                        rs.getString("destination"), rs.getDouble("distance"),
                        rs.getInt("status"), rs.getTime("time"),
                        rs.getDate("date"), rs.getInt("fee")));
            }

            stmt.close();
            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }

        return js;
    }

    public List<Journey> allJourneys()
    {
        ArrayList<Journey> js = new ArrayList<>();

        try
        {
            DataBase db = new DataBase();

            //Statement to find all drivers
            PreparedStatement stmt = db.getCon().prepareStatement("select * from driver inner join users on driver.USER_ID = users.ID");
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response and building journey list
                js.add(new Journey(rs.getInt("id"), rs.getInt("customer_id"),
                        rs.getInt("driver_id"), rs.getString("start"),
                        rs.getString("destination"), rs.getDouble("distance"),
                        rs.getInt("status"), rs.getTime("time"),
                        rs.getDate("date"), rs.getInt("fee")));
            }

            stmt.close();
            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }

        return js;
    }

    public void newJourney(Journey j)
    {
        try
        {
            DataBase db = new DataBase();
            PreparedStatement stmt = db.getCon().prepareStatement("INSERT INTO journey(CUSTOMER_ID, DRIVER_ID, START, DESTINATION, "
                    + "DISTANCE, DATE, TIME, FEE, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, j.getCustomer().getID());
            stmt.setInt(2, j.getDriver().getID());
            stmt.setString(3, j.getStart());
            stmt.setString(4, j.getEnd());
            stmt.setDouble(5, j.getDistance());
            stmt.setDate(6, j.getDate());
            stmt.setTime(7, j.getTime());
            stmt.setInt(8, j.getFee());
            stmt.setInt(9, j.getStatus());

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
