/*
 * Licensed to JGC
 */
package com;

import dao.CustomerDao;
import dao.DriverDao;
import dao.UsersDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.Driver;
import model.User;

/**
 * Controller for Admin editing customer
 * @author Jacob
 */
public class EditCustomerServlet extends HttpServlet
{

    private static final String url = "/WEB-INF/hidden/manageCustomers/";

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
            if (request.getParameter("update_id") != null) //Admin has updated the customer
            {
                this.updateCustomer(request, response);
            } else if (request.getParameter("edit_id") != null) //Admin wants to edit the customer
            {
                //System.out.println("djlds:" + request.getParameter("edit_id"));
                this.editCustomer(request, response);
            } else if (request.getParameter("delete_id") != null) //Admin wants to delete the customer
            {
                //Delete customer
                this.removeCustomer(request, response);
            } else
            {
                //error
                request.setAttribute("errCust", "An error has occured processing your request");
                response.sendRedirect("/AlphaCab/ManageCustomers");
            }

        }
    }

    /**
     * Method for updating customer
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Get fields
        CustomerDao cDao = new CustomerDao();
        Customer c = cDao.findCustomerByID(Integer.parseInt(request.getParameter("update_id")));

        UsersDaoImpl uDao = new UsersDaoImpl();
        String email = request.getParameter("email");
        User test = uDao.findByName(email);
        if (test == null || c.getUsername().equals(email))
        {
            c.setUsername(request.getParameter("email"));
            c.setFirstName(request.getParameter("first_name"));
            c.setLastName(request.getParameter("last_name"));
            c.setAddress(request.getParameter("address"));

            //Apply changes
            cDao.updateCustomer(c);

            //Redirect back
            request.setAttribute("succCust", "Customer was updated successfully");
            response.sendRedirect("/AlphaCab/ManageCustomers");
        }
        else
        {
            request.setAttribute("succCust", "Email already exists");
            response.sendRedirect("/AlphaCab/ManageCustomers");
        }

    }

    /**
     * Method for editing customer
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void editCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Find the customer
        CustomerDao cDao = new CustomerDao();
        Customer c = cDao.findCustomerByID(Integer.parseInt(request.getParameter("edit_id")));

        //Send user to edit customer page
        request.setAttribute("customer", c);
        request.getRequestDispatcher(url + "editCustomer.jsp").forward(request, response);
    }

    /**
     * Method for removing customer
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void removeCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Remove the customer
        CustomerDao cDao = new CustomerDao();

        Customer c = cDao.findCustomerByID(Integer.parseInt(request.getParameter("delete_id")));
        cDao.removeCustomer(c);

        //request.setAttribute("succCust","Customer was removed");
        //Redirect the user
        response.sendRedirect("/AlphaCab/ManageCustomers");
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
