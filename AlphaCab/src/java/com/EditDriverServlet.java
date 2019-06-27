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
import javax.servlet.http.HttpSession;
import model.Driver;
import model.User;

/**
 * Controller for editing driver
 * @author Jacob
 */
public class EditDriverServlet extends HttpServlet
{

    private static final String url = "/WEB-INF/hidden/manageDrivers/";

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
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") != null)
        {
            if (request.getParameter("update_id") != null)
            {
                this.updateDriver(request, response);
            } else if (request.getParameter("edit_id") != null)
            {
                this.editDriver(request, response);
            } else if (request.getParameter("delete_id") != null)
            {
                //Delete driver
                this.removeDriver(request, response);
            } else
            {
                //error
                response.sendRedirect("/AlphaCab/ManageDrivers");
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

    /**
     * Method for updating driver
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void updateDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Get fields
        DriverDao dDao = new DriverDao();
        Driver d = dDao.findDriverByID(Integer.parseInt(request.getParameter("update_id")));

        UsersDaoImpl uDao = new UsersDaoImpl();
        String email = request.getParameter("email");
        User test = uDao.findByName(email);
        if (test == null || d.getUsername().equals(email))
        {
            d.setUsername(request.getParameter("email"));
            d.setFirstName(request.getParameter("first_name"));
            d.setLastName(request.getParameter("last_name"));
            d.setReg(request.getParameter("registration"));

            //Apply changes
            dDao.updateDriver(d);

            //Redirect back
            response.sendRedirect("/AlphaCab/ManageDrivers");
        }
        else
        {
            //Email already exists
            //Redirect back
            response.sendRedirect("/AlphaCab/ManageDrivers");
        }

        
    }

    /**
     * Method for editing driver
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void editDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Find the driver
        DriverDao dDao = new DriverDao();
        Driver d = dDao.findDriverByID(Integer.parseInt(request.getParameter("edit_id")));

        //Send user to edit driver page
        request.setAttribute("driver", d);
        request.getRequestDispatcher(url + "editDriver.jsp").forward(request, response);
    }

    /**
     * Method for removing a driver
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void removeDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Remove the driver
        DriverDao dDao = new DriverDao();

        Driver d = dDao.findDriverByID(Integer.parseInt(request.getParameter("delete_id")));
        dDao.removeDriver(d);

        //Redirect the user
        response.sendRedirect("/AlphaCab/ManageDrivers");
    }
}
