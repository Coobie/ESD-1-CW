/*
 * Licensed to JGC
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Fee;

/**
 *
 * @author Jacob
 */
public class FeeDao
{
    public Fee getFeeAmount()
    {
        Fee f = null;
        
        try
        {
            DataBase db = new DataBase();

            //Statement to find all drivers
            PreparedStatement stmt = db.getCon().prepareStatement("select * from fee_amount where id = 1");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {  //iterate through response creating new drivers and adding to list
              //driver = new Driver(rs.getInt("id"), rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("registration"));
                f = new Fee(rs.getDouble("vat"), rs.getInt("price_per_mile"), rs.getDouble("short_distance"),rs.getInt("extra_charge"));
                break;
            }

            stmt.close();
            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        
        return f;
    }
    
    public void changeFeeAmount(Fee f)
    {
        
        try
        {
            //Update users table
            DataBase db = new DataBase();
            PreparedStatement stmt = db.getCon().prepareStatement("UPDATE fee_amount set vat = ?, price_per_mile = ?, short_distance = ?, extra_charge = ? WHERE id = 1");
            stmt.setDouble(1, f.getVat());
            stmt.setInt(2, f.getPpm());
            stmt.setDouble(3, f.getsDistance());
            stmt.setInt(4, f.getxCharge());
            
            // execute insert SQL stetement
            stmt.executeUpdate();
            
            stmt.close();
            
            db.getCon().close();
            
        } catch (SQLException ex)
        {
            Logger.getLogger(FeeDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("FAILED IN UPDATING CUSTOMER");
        }
    }
}
