/*
 * Licensed to JGC
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.FeeDao;
import model.Fee;
import client1.Client;

/**
 * Controller admin changing the fee amounts
 * @author Jacob
 */
public class EditFeesServlet extends HttpServlet
{
    private static final String url = "/WEB-INF/hidden/editFees/";
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
        if (request.getParameter("edit_status") != null) //Updated the fee amounts
        {
            
            //Get values
            double vat = Double.parseDouble(request.getParameter("vat"));
            int ppm = Integer.parseInt(request.getParameter("ppm"));
            double sDistance = Double.parseDouble(request.getParameter("sDis"));
            int exChar = Integer.parseInt(request.getParameter("extra"));
            
            //Apply values
            Client.changeFee(vat, ppm, sDistance, exChar);
            
            response.sendRedirect("/AlphaCab/");
        }
        else //Wants to edit the fees
        {
            Fee fee = new FeeDao().getFeeAmount();
            request.setAttribute("fee", fee);
            request.getRequestDispatcher(url + "editFees.jsp").forward(request, response);
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
