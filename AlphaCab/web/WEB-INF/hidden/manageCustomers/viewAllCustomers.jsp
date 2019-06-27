<%-- 
    Document   : viewAllCustomers
    Created on : 22-Nov-2018, 17:26:10
    Author     : mherring
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <h1>View all customers</h1>
        </div>
        <table id="customers" class="table table-striped table-bordered" style="width:100%">
            <thead>
                <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Address</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${customers_list}" var="cust">
                    <tr>
                        <td><c:out value="${cust.username}"/></td>
                        <td><c:out value="${cust.firstName}"/></td>
                        <td><c:out value="${cust.lastName}"/></td>
                        <td><c:out value="${cust.address}"/></td>
                        <td>
                            <form class="form-inline" method="POST" action="<%=request.getContextPath()%>/EditCustomer" >
                                <button type="submit" class="btn btn-info mb-2" name="edit_id" value="<c:out value="${cust.ID}"/>"><i class="fas fa-edit"></i></button>&nbsp;
                                <button type="submit" class="btn btn-danger mb-2" name="delete_id" value="<c:out value="${cust.ID}"/>" onclick="return confirm('Are you sure you wanted to delete this customer? \nThis cannot undone.')"><i class="fas fa-trash-alt"></i></button>
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
