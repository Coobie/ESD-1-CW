/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author mherring
 */
public class CustomerDao {
    
    public Customer findCustomerByID(int ID)
    {
        Customer customer = null;
        
         try
        {
            DataBase db = new DataBase();
            
            //Statement to find all drivers
            PreparedStatement stmt = db.getCon().prepareStatement("select * from customer inner join users on customer.USER_ID = users.ID where customer.id = ?");
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {  //iterate through response creating new drivers and adding to list
                customer = new Customer(rs.getInt("id"),rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"),rs.getString("address"));
                //break;
            }

            stmt.close();
            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        
        return customer;
    }
    
    public Customer findCustomerByUserID(int ID)
    {
        Customer customer = null;
        
         try
        {
            DataBase db = new DataBase();
            
            //Statement to find all drivers
            PreparedStatement stmt = db.getCon().prepareStatement("select * from customer inner join users on customer.USER_ID = users.ID where users.id = ?");
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {  //iterate through response creating new drivers and adding to list
                customer = new Customer(rs.getInt("id"),rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"),rs.getString("address"));
                //break;
            }

            stmt.close();
            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        
        return customer;
    }
    
    public List<Customer> getAllCustomers()
    {
        List<Customer> customers = new ArrayList<>();
         try
        {
            DataBase db = new DataBase();
            
            //Statement to find all customers
            PreparedStatement stmt = db.getCon().prepareStatement("select * from customer inner join users on customer.USER_ID = users.ID");
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response creating new customers and adding to list
                Customer d = new Customer(rs.getInt("id"),rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"),rs.getString("address"));
                customers.add(d);
            }

            stmt.close();
            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        
        
       
        return customers;
    }

    public void updateCustomer(Customer c)
    {
        try
        {
            //Update users table
            DataBase db = new DataBase();
            PreparedStatement stmt = db.getCon().prepareStatement("UPDATE users set username = ?, first_name = ?, last_name = ? WHERE id = ?");
            stmt.setString(1, c.getUsername());
            stmt.setString(2, c.getFirstName());
            stmt.setString(3, c.getLastName());
            stmt.setInt(4, c.getUser_id());
            
            // execute insert SQL stetement
            stmt.executeUpdate();
            
            stmt.close();
            
            //Update customer table
            PreparedStatement stmt2 = db.getCon().prepareStatement("UPDATE customer set address = ? WHERE id = ?");
            stmt2.setString(1, c.getAddress());
            stmt2.setInt(2, c.getID());
            
            stmt2.executeUpdate();
            
            stmt2.close();
            
            db.getCon().close();
            
        } catch (SQLException ex)
        {
            Logger.getLogger(UsersDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("FAILED IN UPDATING CUSTOMER");
        }
    }

    public void removeCustomer(Customer c)
    {
        try
        {
            DataBase db = new DataBase();
            PreparedStatement stmt = db.getCon().prepareStatement("DELETE FROM customer WHERE id = ?");
            stmt.setInt(1, c.getID());
            stmt.executeUpdate();
            stmt.close();
            
            PreparedStatement stmt2 = db.getCon().prepareStatement("DELETE FROM users WHERE id = ?");
            stmt2.setInt(1, c.getUser_id());
            stmt2.executeUpdate();
            stmt2.close();
            
            db.getCon().close();
            
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
