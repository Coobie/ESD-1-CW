/*
 * Licensed to JGC
 */
package com;

import dao.DriverDao;
import dao.UsersDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 * Controller for registering driver
 * @author Jacob
 */
public class RegisterDriverServlet extends HttpServlet
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
        //Get stuff from form
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String reg = request.getParameter("reg");
        
        UsersDaoImpl uDao = new UsersDaoImpl();
        
        //Check username doesn't exist already
        if (uDao.findByName(username) != null)
        {
            request.setAttribute("errMessageNewDriver", "The email " + username + " is already taken");
            request.getRequestDispatcher("/new-driver.jsp").forward(request, response);
        }
        else
        { //Create new driver
            DriverDao dDao = new DriverDao();
            
            uDao.addUser(firstName, lastName, reg, username, "DRIVER");
            User u = uDao.findByName(username);
            dDao.newDriver(reg,u.getID());
            
            response.sendRedirect("/AlphaCab/ManageDrivers");
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
