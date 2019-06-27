<%-- 
    Document   : invoice
    Created on : 03-Dec-2018, 18:00:03
    Author     : Jacob
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice</title>
    </head>
    <body onload="window.print()">
        <c:choose>
            <c:when test="${not empty journey}">
                <h1 style="text-align: center;">Invoice</h1>
                <p><img style="display: block; margin-left: auto; margin-right: auto; background-color:black;" src="https://image.ibb.co/nR52cq/Logo.png" width="436" height="245" /></p>
                <h1 style="text-align: center;"><strong>THANKS FOR BOOKING </strong></h1>
                <h1 style="text-align: center;"><strong>WITH AlphaCab</strong></h1>
                <blockquote>
                    <p style="text-align: center;">Invoice Number :<c:out value="${journey.id}" /></p>
                    <p style="text-align: center;">Hi <c:out value="${journey.customer.firstName}" />,</p>
                    <p style="text-align: center;">We have received your booking and your driver will see you at</p>
                    <p style="text-align: center;"><fmt:formatDate type = "date" value = "${journey.date}" /> <fmt:formatDate type = "time" pattern = "HH:mm" value = "${journey.time}" />.</p>
                    <p style="text-align: center;">--------------------------------------------------------------------</p>
                    <h3 style="text-align: center;">INVOICE SUMMARY</h3>
                    <p style="text-align: center;">For: <c:out value="${journey.customer.firstName} ${journey.customer.lastName}" /></p>
                    <p style="text-align: center;">From: <c:out value="${journey.start}" /></p>
                    <p style="text-align: center;">--------------------------------------------------------------------</p>
                    <p style="text-align: center;">To: <c:out value="${journey.end}" /></p>
                    <p style="text-align: center;">--------------------------------------------------------------------</p>
                    <p style="text-align: center;">Driver Name: <c:out value="${journey.driver.firstName} ${fn:substring(journey.driver.lastName, 0, 1)}"/></p>
                    <p style="text-align: center;">Driver ID: <c:out value="${journey.driver.ID}" /></p>
                    <p style="text-align: center;">--------------------------------------------------------------------</p>
                    <p style="text-align: center;">Distance: <c:out value="${journey.distance}" /> (miles)</p>
                    <p style="text-align: center;">Total Price: <fmt:formatNumber value = "${journey.fee / 100}" type = "currency"/> (In. VAT)</p>
                </blockquote>
            </c:when>
        </c:choose>
    </body>
</html>
