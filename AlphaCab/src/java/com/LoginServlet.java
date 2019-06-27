/*
 * Licensed to JGC
 */
package com;

import dao.UsersDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import dao.*;
import javax.servlet.http.HttpSession;

/**
 * Controller for the login
 * @author Jacob
 */
public class LoginServlet extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        //Check whether user is already logged in
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") != null) 
        {
            response.sendRedirect("/AlphaCab/index.jsp");
        }
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.isEmpty() || password.isEmpty()) //Check if the details are empty
        {
            request.setAttribute("errMessageLogin", "Username and password cannot be left blank");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else
        {
            //Check username and password 
            UsersDaoImpl usersDaoImpl = new UsersDaoImpl();
            User user = usersDaoImpl.findByName(username);
            if (user == null)
            {
                request.setAttribute("errMessageLogin", "Username cannot be found");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else
            {
                if (password.equals(user.getPassword()))
                { //Correct password
                    
                    //Set session
                    Login login = new Login(user, session, response);
                    
                } else
                { //Incorrect password
                    request.setAttribute("errMessageLogin", "Incorrect password");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }

            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
