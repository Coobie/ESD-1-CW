/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import dao.DriverDao;
import dao.JourneyDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Driver;
import model.Journey;

/**
 * Controller for admin daily report
 * @author mherring
 */
public class ReportServlet extends HttpServlet
{

    private static final String url = "/WEB-INF/hidden/dailyReport/";

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
            
            //Todays date
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Long today = new Long(format.format(new Date()));
       
            //Totals
            int totalIncome = 0;
            int totalJobs = 0;
            double totalMiles = 0;
            
            //Loop over drivers
            for (Driver d : drivers)
            {
                //Journeys for each driver
                List<Journey> driversJourneys = new JourneyDao().findJourneysByDriverID(d.getID());
                
                for (Journey j : driversJourneys)
                {
                    Long date = new Long(format.format(j.getDate()));
                    if (date.equals(today))
                    { //Find journeys for today's date
                        d.setTotalIncome(d.getTotalIncome() + j.getFee());
                        d.setTotalJobs(d.getTotalJobs() + 1);
                        d.setTotalMiles(d.getTotalMiles() + j.getDistance());
                        
                        //Totals
                        totalIncome += j.getFee();
                        totalJobs ++;
                        totalMiles += j.getDistance();
                    }
                }
            }
            
            //Totals
            request.setAttribute("total_jobs", totalJobs);
            request.setAttribute("total_miles", totalMiles);
            request.setAttribute("total_income", totalIncome);
            
            request.setAttribute("drivers_list", drivers);
            request.getRequestDispatcher(url + "viewReport.jsp").forward(request, response);
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
