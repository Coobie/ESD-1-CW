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
import model.User;

/**
 * Controller for updating user account
 * @author Jacob
 */
public class UpdateUserServlet extends HttpServlet
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
        HttpSession session = request.getSession(false);

        if (session != null)
        {
            if (session.getAttribute("user") != null) //If session is not null
            {
                User user = (User) session.getAttribute("user");
                if (request.getParameter("inputEmail") != null)
                { //user wants to change email
                    String username = request.getParameter("inputEmail");
                    UsersDaoImpl uDao = new UsersDaoImpl();

                    if (uDao.findByName(username) == null)
                    {
                        User user2 = new User(user);
                        user2.setUsername(username);
                        uDao.updateUser(user2);
                        if (uDao.findByName(username) != null)
                        {
                            user = user2;
                            session.setAttribute("user", user);
                            request.setAttribute("sucUpdate", "Successfully changed email");
                            request.getRequestDispatcher("/account.jsp").forward(request, response);
                        } else
                        {
                            request.setAttribute("errUpdate", "Error chaning email");
                            request.getRequestDispatcher("/account.jsp").forward(request, response);
                        }
                    } else
                    { //Email already exists
                        request.setAttribute("errUpdate", "Email already exists");
                        request.getRequestDispatcher("/account.jsp").forward(request, response);
                    }

                } else if (request.getParameter("inputPassword1") != null && request.getParameter("inputPassword2") != null && request.getParameter("inputPassword3") != null)
                {
                    //Check user's current password
                    String cPass = request.getParameter("inputPassword1");
                    if (cPass.equals(user.getPassword()))
                    {
                        String newPass1 = request.getParameter("inputPassword2");
                        String newPass2 = request.getParameter("inputPassword3");

                        if (newPass1.equals(newPass2))
                        {
                            UsersDaoImpl uDao = new UsersDaoImpl();
                            user.setPassword(newPass1);
                            uDao.updateUser(user);
                            request.setAttribute("sucUpdate", "Password has been changed");
                            request.getRequestDispatcher("/account.jsp").forward(request, response);
                        } else
                        {
                            request.setAttribute("errUpdate", "Passwords do not match");
                            request.getRequestDispatcher("/account.jsp").forward(request, response);
                        }
                    } else
                    {
                        request.setAttribute("errUpdate", "Current password is not correct");
                        request.getRequestDispatcher("/account.jsp").forward(request, response);
                    }
                } else if (request.getParameter("first_name") != null && request.getParameter("last_name") != null)
                {
                    String firstName = request.getParameter("first_name");
                    String lastName = request.getParameter("last_name");

                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    UsersDaoImpl uDao = new UsersDaoImpl();
                    uDao.updateUser(user);

                    request.setAttribute("sucUpdate", "Name has been changed");
                    request.getRequestDispatcher("/account.jsp").forward(request, response);
                } else
                {
                    request.setAttribute("errUpdate", "Fields not filled in");
                    request.getRequestDispatcher("/account.jsp").forward(request, response);
                }

            } else
            {
                response.sendRedirect("/AlphaCab/index.jsp");
            }
        } else
        {
            response.sendRedirect("/AlphaCab/index.jsp");
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
