/*
 * Licensed to JGC
 */
package model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jacob
 */
public class Login
{

    private User user;

    public Login(User user, HttpSession session, HttpServletResponse response)
    {
        this.user = user;
        
        session.setAttribute("user",user);
        try
        {
            //Redirect to the home page
            response.sendRedirect("/AlphaCab/index.jsp");
        } catch (IOException ex)
        {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Login()
    {
    }

    
    
    public boolean validateUser(String username, String password)
    {
        boolean valid = false;
        if (user != null)
        {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                valid = true;
            }
        }

        return valid;
    }

    public String getUsername()
    {
        return user.getUsername();
    }

    public String getPassword()
    {
        return user.getPassword();
    }

}
