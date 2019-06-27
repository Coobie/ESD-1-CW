/*
 * Licensed to JGC
 */
package com;

import dao.UsersDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Login;
import model.User;

/**
 * Controller for register customer
 * @author Jacob
 */
public class RegisterCustomerServlet extends HttpServlet
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

        //Get stuff from form
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String address = request.getParameter("address");

        if (username == null && firstName == null && lastName == null && password1 == null && password2 == null && address == null)
        {
            request.setAttribute("errMessageRegister", "Fields cannot be left empty");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } else
        {
            //Check if username exists
            UsersDaoImpl usersDaoImpl = new UsersDaoImpl();
            User user = usersDaoImpl.findByName(username);
            if (user != null)
            {
                request.setAttribute("errMessageRegister", "The email " + username + " is already taken");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            } else
            {
                if (password1.equals(password2) == false)
                { // Passwords do not match
                    request.setAttribute("errMessageRegister", "Passwords do not match");
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                } else
                {
                    //Create user - users table
                    //Create customer - customers table
                    user = usersDaoImpl.addCustomer(firstName, lastName, password1, username, address);
                    
                    //Log user in 
                    if (user != null)
                    {
                        Login login = new Login(user, session, response);
                    }
                    else
                    {
                        request.setAttribute("errMessageRegister", "An Error has occured");
                        request.getRequestDispatcher("/register.jsp").forward(request, response);
                        System.out.println("Login error - no user");
                    }

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
