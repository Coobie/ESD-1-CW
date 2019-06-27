/*
 * Licensed to JGC
 */
package com;

import com.sun.xml.wss.util.DateUtils;
import dao.CustomerDao;
import dao.DriverDao;
import dao.JourneyDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Journey;
import model.User;

/**
 * Controller for viewing bookings
 * @author Jacob
 */
public class ViewBookingsServlet extends HttpServlet
{
     private static final String url = "/WEB-INF/hidden/bookings/";
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
        //Driver and customer
        HttpSession s = request.getSession(false);
        User u = (User) s.getAttribute("user");
        
        if (u.getUsertype().equals("DRIVER")) //Driver
        {
            this.driverView(request, response, u);
        } else //Customer
        {
            if (request.getParameter("invoice_id") != null) //View invoice
            {
                this.customerView(request, response, u);
            } else //View all bookings
            {
                this.customerViewAll(request, response, u);
            }

        }
    }

    /**
     * Method for customer view all bookings
     * @param request
     * @param response
     * @param u (user)
     * @throws ServletException
     * @throws IOException 
     */
    private void customerViewAll(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException
    {
        //View all customer bookings
        List<Journey> cJs;
        cJs = new JourneyDao().findJourneysByCustomerID(new CustomerDao().findCustomerByUserID(u.getID()).getID());
        
        List<Journey> today = new ArrayList<>();
        List<Journey> soon = new ArrayList<>();
        List<Journey> past = new ArrayList<>();
                
        for (Journey dJ : cJs)
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Long t = new Long(format.format(new Date()));
            Long d = new Long(format.format(dJ.getDate()));
            
            if (t.equals(d)) //today
            {
                today.add(dJ);
            }
            else if (d > t) //upcoming
            {
                soon.add(dJ);
            }
            else //Past
            {
                past.add(dJ);
            }
        }
                
        request.setAttribute("today_list", today);
        request.setAttribute("soon_list", soon);
        request.setAttribute("past_list", past);
        request.getRequestDispatcher(url + "customerBookings.jsp").forward(request, response);
    }

    /**
     * Method for customer viewing invoice
     * @param request
     * @param response
     * @param u (User)
     * @throws ServletException
     * @throws IOException 
     */
    private void customerView(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException
    {
        //Invoice
        Journey j = new JourneyDao().findJourneyByID(Integer.parseInt(request.getParameter("invoice_id")));
        request.setAttribute("journey", j);
        request.getRequestDispatcher(url + "invoice.jsp").forward(request, response);
    }

    /**
     * Method for driver viewing bookings
     * @param request
     * @param response
     * @param u (user)
     * @throws ServletException
     * @throws IOException 
     */
    private void driverView(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException
    {
        List<Journey> dJs; //Get journeys
        dJs = new JourneyDao().findJourneysByDriverID(new DriverDao().findDriverByUserID(u.getID()).getID());
        
        List<Journey> today = new ArrayList<>();
        List<Journey> soon = new ArrayList<>();
        List<Journey> past = new ArrayList<>();
                
        for (Journey dJ : dJs)
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Long t = new Long(format.format(new Date()));
            Long d = new Long(format.format(dJ.getDate()));
            
            if (t.equals(d)) //today
            {
                today.add(dJ);
            }
            else if (d > t) //upcoming
            {
                soon.add(dJ);
            }
            else //Past
            {
                past.add(dJ);
            }
        }
                
        request.setAttribute("today_list", today);
        request.setAttribute("soon_list", soon);
        request.setAttribute("past_list", past);
        request.getRequestDispatcher(url + "driverBookings.jsp").forward(request, response);
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
