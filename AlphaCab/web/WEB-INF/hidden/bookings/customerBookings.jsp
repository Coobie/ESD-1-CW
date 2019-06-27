<%-- 
    Document   : customerBookings
    Created on : 03-Dec-2018, 17:11:45
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
                            <th>Driver's Name</th>
                            <th>Driver's registration</th>
                            <th>Fee</th>
                            <th>View Invoice</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${today_list}" var="j">
                            <tr>
                                <td><fmt:formatDate type = "date" value = "${j.date}" /></td>
                                <td><fmt:formatDate type = "time" pattern = "HH:mm" value = "${j.time}" /></td>
                                <td><c:out value="${j.start}"/></td>
                                <td><c:out value="${j.end}"/></td>
                                <td><c:out value="${j.driver.firstName} ${fn:substring(j.driver.lastName, 0, 1)}"/></td>
                                <td><c:out value="${j.driver.reg}"/></td>
                                
                                <td><fmt:formatNumber value = "${j.fee / 100}" type = "currency"/></td>
                                <td>
                                    <form method="POST" action="<%=request.getContextPath()%>/myBookings">
                                        <input type="hidden" name="invoice_id" value="<c:out value="${j.id}"/>">
                                        <button type="submit" class="btn btn-secondary mb-2"><i class="fas fa-external-link-alt"></i></button>
                                    </form> 
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
                            <th>Driver's Name</th>
                            <th>Driver's registration</th>
                            <th>Fee</th>
                            <th>View Invoice</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${soon_list}" var="j">
                            <tr>
                                <td><fmt:formatDate type = "date" value = "${j.date}" /></td>
                                <td><fmt:formatDate type = "time" pattern = "HH:mm" value = "${j.time}" /></td>
                                <td><c:out value="${j.start}"/></td>
                                <td><c:out value="${j.end}"/></td>
                                <td><c:out value="${j.driver.firstName} ${fn:substring(j.driver.lastName, 0, 1)}"/></td>
                                <td><c:out value="${j.driver.reg}"/></td>
                                <td><fmt:formatNumber value = "${j.fee / 100}" type = "currency"/></td>
                                <td>
                                    <form method="POST" action="<%=request.getContextPath()%>/myBookings">
                                        <input type="hidden" name="invoice_id" value="<c:out value="${j.id}"/>">
                                        <button type="submit" class="btn btn-secondary mb-2"><i class="fas fa-external-link-alt"></i></button>
                                    </form> 
                                </td>
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
                            <th>Driver's Name</th>
                            <th>Driver's registration</th>
                            <th>Fee</th>
                            <th>View Invoice</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${past_list}" var="j">
                            <tr>
                                <td><fmt:formatDate type = "date" value = "${j.date}" /></td>
                                <td><fmt:formatDate type = "time" pattern = "HH:mm" value = "${j.time}" /></td>
                                <td><c:out value="${j.start}"/></td>
                                <td><c:out value="${j.end}"/></td>
                                <td><c:out value="${j.driver.firstName} ${fn:substring(j.driver.lastName, 0, 1)}"/></td>
                                <td><c:out value="${j.driver.reg}"/></td>
                                <td><fmt:formatNumber value = "${j.fee / 100}" type = "currency"/></td>
                                <td>
                                    <form method="POST" action="<%=request.getContextPath()%>/myBookings">
                                        <input type="hidden" name="invoice_id" value="<c:out value="${j.id}"/>">
                                        <button type="submit" class="btn btn-secondary mb-2"><i class="fas fa-external-link-alt"></i></button>
                                    </form> 
                                </td>
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
