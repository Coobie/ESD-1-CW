<%-- 
    Document   : viewAllDrivers
    Created on : 21-Nov-2018, 23:06:55
    Author     : Jacob
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<div class="container">
    <div class="card">
        <div class="card-header">
            <h1>View all drivers</h1>
            <a href="<%=request.getContextPath()%>/new-driver.jsp"><button type="button" class="btn btn-primary"><i class="far fa-plus-square"></i> New Driver</button></a>
        </div>
        <table id="drivers" class="table table-striped table-bordered" style="width:100%">
            <thead>
                <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Registration</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${drivers_list}" var="driver">
                    <tr>
                        <td><c:out value="${driver.username}"/></td>
                        <td><c:out value="${driver.firstName}"/></td>
                        <td><c:out value="${driver.lastName}"/></td>
                        <td><c:out value="${driver.reg}"/></td>
                        <td>
                            <form class="form-inline" method="POST" action="<%=request.getContextPath()%>/EditDriver" >
                                <button type="submit" class="btn btn-info mb-2" name="edit_id" value="<c:out value="${driver.ID}"/>"><i class="fas fa-edit"></i></button>&nbsp;
                                <button type="submit" class="btn btn-danger mb-2" name="delete_id" value="<c:out value="${driver.ID}"/>" onclick="return confirm('Are you sure you wanted to delete this driver? \nThis cannot undone.')"><i class="fas fa-trash-alt"></i></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
