
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : header
    Created on : 07-Nov-2018, 15:28:53
    Author     : Jacob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1 shrink-to-fit=no"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"/>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>  
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <link rel="stylesheet" href="style.css"/>
    </head>
    <body>            

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="<%=request.getContextPath()%>"><img src="https://image.ibb.co/nR52cq/Logo.png" height="56" width="100"></img></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div id="navbarNavDropdown" class="navbar-collapse collapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/AlphaCab/">Home</a>
                    </li>
                    <c:choose>
                        <c:when test="${user.usertype eq 'ADMIN'}">

                            <li class="nav-item">
                                <a class="nav-link" href="ManageCustomers"><i class="fas fa-user-cog"></i> Manage Customers</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-taxi"></i> Manage Drivers
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="ManageDrivers"><i class="fas fa-edit"></i>&nbsp; Manage Drivers</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="new-driver.jsp"><i class="fas fa-plus-square"></i>&nbsp; New Driver</a>

                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="DailyReport"><i class="fas fa-chart-line"></i> Daily Report</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="DailyCustomers"><i class="fas fa-chart-bar"></i> Daily Journeys</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="EditFees"><i class="fas fa-hand-holding-usd"></i> Edit Fees</a>
                            </li>
                        </c:when>
                        <c:when test="${user.usertype eq 'DRIVER'}">
                            <li class="nav-item">
                                <a class="nav-link" href="myBookings"><i class="fas fa-list"></i>&nbsp; View Bookings</a>
                            </li>
                        </c:when>
                        <c:when test="${user.usertype eq 'CUSTOMER'}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-taxi"></i> Bookings
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="myBookings"><i class="fas fa-list"></i>&nbsp; View Bookings</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="newbooking.jsp"><i class="fas fa-plus-square"></i>&nbsp; New Booking</a>
                            </li>
                        </c:when>
                    </c:choose>
                    <li class="nav-item">
                        <a class="nav-link" href="about.jsp">About Us</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="contact.jsp">Contact Us</a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <c:choose>
                        <c:when test="${not empty user}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <c:out value="${user.username}" /> &nbsp;<i class="far fa-user-circle"></i> 
                                </a>
                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                                    <div class="dropdown-header">
                                        <h5><c:out value="${user.firstName} ${user.lastName}"/></h5>
                                        <h6><c:out value="${user.username}"/></h6>
                                    </div>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="account.jsp"><i class="fas fa-user"></i> &nbsp; My Account</a>
                                    <a class="dropdown-item" href="Logout"><i class="fas fa-sign-out-alt"></i> &nbsp; Sign out</a>
                                </div>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link" href="login.jsp">Login</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="register.jsp">Register</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </nav>


