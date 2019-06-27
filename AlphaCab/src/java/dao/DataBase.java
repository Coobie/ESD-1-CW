/*
 * Licensed to JGC
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jacob
 */
public class DataBase
{

    private static final String url = "jdbc:derby://localhost:1527/AlphaCab";
    private static final String user = "alpha";
    private static final String pass = "password";

    private Connection con;

    public DataBase()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex)
        {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            con = null;
        }
        
    }

    public static String getUrl()
    {
        return url;
    }

    public static String getUser()
    {
        return user;
    }

    public static String getPass()
    {
        return pass;
    }

    public Connection getCon()
    {
        return con;
    }

    
}
