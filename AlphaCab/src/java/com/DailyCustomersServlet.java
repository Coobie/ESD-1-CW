/*
 * Licensed to JGC
 */
package com;

import dao.JourneyDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Journey;

/**
 * Controller for admin viewing daily customer's journeys
 * @author Jacob
 */
public class DailyCustomersServlet extends HttpServlet
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
            //Todays date
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Long today = new Long(format.format(new Date()));
            
            //List of all journeys
            List<Journey> all = new JourneyDao().getJourneys();
            
            //List of journeys for today
            List<Journey> tJ = new ArrayList<>();
            
            //Find journeys today
            for (Journey j : all)
            {
                Long date = new Long(format.format(j.getDate()));
                if (date.equals(today))
                {
                    tJ.add(j);
                }
            }
            
            request.setAttribute("journeys_today", tJ);
            request.getRequestDispatcher(url + "dailyCustomers.jsp").forward(request, response);
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
