<%-- 
    Document   : driverBookings
    Created on : 03-Dec-2018, 17:14:26
    Author     : Jacob
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<div class="container">
    <c:choose>
        <c:when test="${not empty succCust}">
            <div class='alert alert-success'><c:out value="${succCust}"/></div>
        </c:when>
        <c:when test="${not empty errCust}">
            <div class='alert alert-danger'><c:out value="${errCust}"/></div>
        </c:when>
    </c:choose>
    <div class="card">
        <div class="card-header">
            <h1>View all bookings</h1>
        </div>
        <c:choose>
            <c:when test="${not empty today_list}" >
                <h2>Today:</h2>
                <table id="todays_journeys" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Time (24h)</th>
                            <th>Start Address</th>
                            <th>Destination</th>
                            <th>Customer Name</th>
                            <th>Fee</th>
                            <th>Distance (miles)</th>
                            <th>Maps</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${today_list}" var="j">
                            <tr>
                                <td><fmt:formatDate type = "date" value = "${j.date}" /></td>
                                <td><fmt:formatDate type = "time" pattern = "HH:mm" value = "${j.time}" /></td>
                                <td><c:out value="${j.start}"/></td>
                                <td><c:out value="${j.end}"/></td>
                                <td><c:out value="${j.customer.firstName} ${fn:substring(j.customer.lastName, 0, 1)}"/></td>
                                <td><fmt:formatNumber value = "${j.fee / 100}" type = "currency"/></td>
                                <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${j.distance}" /></td>
                                <td>
                                    <c:set var = "startu" value="${fn:replace(j.start, ' ', '+')}" />
                                    <c:set var = "endu" value="${fn:replace(j.end, ' ', '+')}" />
                                    <a href="https://www.google.com/maps/dir/?api=1&origin=<c:out value="${fn:replace(startu, ',', '%2C')}" />&destination=<c:out value="${fn:replace(endu, ',', '%2C')}" />&travelmode=driving" target="_blank"><button type="button" class="btn btn-secondary">Start navigation</button></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <hr>
            </c:when>
        </c:choose>
        <c:choose><c:when test="${not empty soon_list}">
                <h2>Upcoming:</h2>
                <table id="soon_journeys" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Time (24h)</th>
                            <th>Start Address</th>
                            <th>Destination</th>
                            <th>Customer Name</th>
                            <th>Fee</th>
                            <th>Distance (miles)</th>
                            <th>Maps</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${soon_list}" var="j">
                            <tr>
                                <td><fmt:formatDate type = "date" value = "${j.date}" /></td>
                                <td><fmt:formatDate type = "time" pattern = "HH:mm" value = "${j.time}" /></td>
                                <td><c:out value="${j.start}"/></td>
                                <td><c:out value="${j.end}"/></td>
                                <td><c:out value="${j.customer.firstName} ${fn:substring(j.customer.lastName, 0, 1)}"/></td>
                                <td><fmt:formatNumber value = "${j.fee / 100}" type = "currency"/></td>
                                <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${j.distance}" /></td>
                                <td> Available on <c:out value="${j.date}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
        </c:choose>
        <c:choose><c:when test="${not empty past_list}">
                <hr>
                <h2>Past:</h2>
                <table id="past_journeys" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Time (24h)</th>
                            <th>Start Address</th>
                            <th>Destination</th>
                            <th>Customer Name</th>
                            <th>Fee</th>
                            <th>Distance (miles)</th>
                            <th>Maps</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${past_list}" var="j">
                            <tr>
                                <td><fmt:formatDate type = "date" value = "${j.date}" /></td>
                                <td><fmt:formatDate type = "time" pattern = "HH:mm" value = "${j.time}" /></td>
                                <td><c:out value="${j.start}"/></td>
                                <td><c:out value="${j.end}"/></td>
                                <td><c:out value="${j.customer.firstName} ${fn:substring(j.customer.lastName, 0, 1)}"/></td>
                                <td><fmt:formatNumber value = "${j.fee / 100}" type = "currency"/></td>
                                <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${j.distance}" /></td>
                                <td>No longer Available</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>
