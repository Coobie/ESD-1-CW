/*
 * Licensed to JGC
 */
package com;

import dao.DriverDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Driver;

/**
 * Controller for managing drivers
 * @author Jacob
 */
public class ManageDriversServlet extends HttpServlet
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
            //Get all drivers
            DriverDao dDao = new DriverDao();
            List<Driver> drivers = dDao.getAllDrivers();
            request.setAttribute("drivers_list", drivers);
            request.getRequestDispatcher(url + "viewAllDrivers.jsp").forward(request, response);
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

//    private void updateDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//    {
//        //request.setAttribute("Driver", "");
//        //request.getRequestDispatcher(url+"viewDriver.jsp").forward(request, response);
//    }
//    
//    private void viewDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//    {
//        request.setAttribute("Driver", "");
//        request.getRequestDispatcher(url+"viewDriver.jsp").forward(request, response);
//    }
//
//    private void editDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//    {
//        request.setAttribute("Driver", "");
//        request.getRequestDispatcher(url+"editDriver.jsp").forward(request, response);
//    }
//
//    private void viewAllDrivers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//    {
//        
//    }
}
