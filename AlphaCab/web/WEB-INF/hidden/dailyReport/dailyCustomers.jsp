<%-- 
    Document   : dailyCustomers
    Created on : 05-Dec-2018, 22:10:09
    Author     : Jacob
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<div class="container">
    <div class="card">
        <div class="card-header">
            <h1>Daily Journeys</h1>
            <p>List all customers served per day including the destinations and the charge incurred</p>
        </div>
        <table id="drivers" class="table table-striped table-bordered" style="width:100%">
            <thead>
                <tr>
                    <th>Customer</th>
                    <th>Driver</th>
                    <th>Start</th>
                    <th>Destination</th>
                    <th>Distance</th>
                    <th>Fee</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${journeys_today}" var="j">
                    <tr>
                        <td><c:out value="${j.customer.firstName} ${fn:substring(j.customer.lastName, 0, 1)}"/></td>
                        <td><c:out value="${j.driver.firstName} ${fn:substring(j.driver.lastName, 0, 1)}"/></td>
                        <td><c:out value="${j.start}"/></td>
                        <td><c:out value="${j.end}"/></td>
                        <td><c:out value="${j.distance}"/></td>
                        <td><fmt:formatNumber value = "${j.fee / 100}" type = "currency"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

