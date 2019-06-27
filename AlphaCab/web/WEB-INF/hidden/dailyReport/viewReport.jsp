<%-- 
    Document   : viewReport
    Created on : 03-Dec-2018, 16:46:59
    Author     : mherring
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<div class="container">
    <div class="card">
        <div class="card-header">
            <h1>Daily Report</h1>
            <p>Prepare a daily report (including the turnover, and the number of customers served)</p>
        </div>
        <table id="drivers" class="table table-striped table-bordered" style="width:100%">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Registration</th>
                    <th>Total Jobs</th>
                    <th>Total Miles</th>
                    <th>Total Income</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${drivers_list}" var="driver">
                    <tr>
                        <td><c:out value="${driver.firstName} ${driver.lastName}"/></td>
                        <td><c:out value="${driver.reg}"/></td>
                        <td><c:out value="${driver.totalJobs}"/></td>
                        <td><c:out value="${driver.totalMiles}"/></td>
                        <td><fmt:formatNumber value = "${driver.totalIncome / 100}" type = "currency"/></td>
                    </tr>
                </c:forEach>
                    <tr>
                        <td>TOTAL</td>
                        <td></td>
                        <td><c:out value="${total_jobs}"/></td>
                        <td><c:out value="${total_miles}"/></td>
                        <td><fmt:formatNumber value = "${total_income / 100}" type = "currency"/></td>
                    </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

