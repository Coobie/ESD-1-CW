/*
 * Licensed to JGC
 */
package com;

import dao.CustomerDao;
import dao.DriverDao;
import dao.JourneyDao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.Driver;
import model.Journey;
import model.User;
import client1.Client;

/**
 * Controller for customer create booking 
 * @author Jacob
 */
public class CreateBookingServlet extends HttpServlet
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

        String stage = request.getParameter("stage_booking").toString();
        if (stage.equals("adjust")) //User wants to make changes
        {
            response.sendRedirect("/AlphaCab/newbooking.jsp");
        } else
        {
            // Get booking details from jsp
            HttpSession s = request.getSession(false);
            User u = (User) s.getAttribute("user");
            Customer customer = new CustomerDao().findCustomerByUserID(u.getID());

            String start = request.getParameter("start_address");
            String end = request.getParameter("destination_address");

            Date date = Date.valueOf(request.getParameter("date").toString());

            String timestr = request.getParameter("time");
            
            if (stage.equals("new")) //User has filled in booking
            {
                Time time = Time.valueOf(timestr + ":00");
                //Calculate distance
                double distance = this.distanceCalculator(start, end, getServletContext().getInitParameter("googleAPIkey"));

                if (distance == -12345) //Check for no distance found ie invalid address(es)
                {
                    //Error
                    request.setAttribute("errMessageBooking", "Address not found");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/newbooking.jsp");
                    requestDispatcher.forward(request, response);
                } else
                {
                    //Check driver avaiablilty
                    Driver driver = this.findAvailableDriver(date, time);

                    if (driver != null)
                    {
                        //Web services for calculating fee
                        int fee = this.calculateFee(distance); // Calculate in pence (p)

                        //Send user to the confirm page
                        request.setAttribute("start_address", start);
                        request.setAttribute("destination_address", end);
                        request.setAttribute("date", date);
                        request.setAttribute("time", time);
                        request.setAttribute("distance", distance);
                        request.setAttribute("fee", fee);

                        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url + "/confirmBooking.jsp");
                        requestDispatcher.forward(request, response);
                    } else
                    {
                        request.setAttribute("errMessageBooking", "Unfortunately there is no driver available");
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/newbooking.jsp");
                        requestDispatcher.forward(request, response);
                    }

                }
            } else if (stage.equals("confirmed")) //User has confirmed the booking
            {
                Time time = Time.valueOf(timestr);
                Driver driver = this.findAvailableDriver(date, time); //Need to check driver available again

                if (driver != null) //A driver has been found
                {
                    Double distance = Double.valueOf(request.getParameter("distance"));

                    //Web services for calculating fee
                    int fee = this.calculateFee(distance); // Calculate in pence (p)

                    //Create booking in db
                    Journey j = new Journey(start, end, distance, 0, time, date, driver, customer, fee);
                    new JourneyDao().newJourney(j);

                    //Redirect
                    response.sendRedirect("/AlphaCab/myBookings");
                } else
                {
                    request.setAttribute("errMessageBooking", "Unfortunately there is no driver available");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/newbooking.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else
            {
                response.sendRedirect("/AlphaCab/myBookings");
            }

        }

    }

    /**
     * Find a available driver 
     * @param date (Date)
     * @param time (Time)
     * @return Driver - null is no driver found
     */
    private Driver findAvailableDriver(Date date, Time time)
    {
        Driver d = new DriverDao().findDriverByID(1);
        
        //Loop over drivers checking date and calculating which drivers are available
        
        return d;
    }

    /**
     * Calculate the fee for the journey
     * @param distance (double) in miles
     * @return (int) the fee 
     */
    private int calculateFee(double distance)
    {
        //Call web service
        return Client.calculateFee(distance);
    }

    /**
     * Method for getting the journey distance
     * @param start starting address
     * @param end ending address
     * @param key API key
     * @return (double) distance
     */
    private double distanceCalculator(String start, String end, String key)
    {
        double d = -12345; //Set distance equal to random value which is negative to check for errors later

        //Format the strings to make them acceptable for google url
        start = start.replace(" ", "+");
        start = start.replace(",", "%2C");
        end = end.replace(" ", "+");
        end = end.replace(",", "%2C");
        String dis = null;
        try
        { //Use google maps distance matrix api to get distance
            URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + start + "&destinations=" + end + "&key=" + key);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String str = "";
            String all = "";

            //Loops through google response looking for the distance
            while (null != (str = br.readLine()))
            {
                //System.out.println(str);
                if (str.contains("distance"))
                { //Find the next line after distance is mentioned for the value
                    dis = br.readLine();
                }
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        //System.out.println("----");
        //System.out.println(dis);
        if (dis != null)
        { //Decode the string (from json) to get the value for the distance
            dis = dis.replace("\"", "");
            String[] a = dis.split(" : ");
            String[] b = a[1].split(" mi");
            //System.out.println(":::::"+b[0]);
            d = Double.parseDouble(b[0]);
        }

        return d;
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
