<%-- 
    Document   : profile
    Created on : 05-Nov-2018, 17:17:00
    Author     : Jacob
--%>

<%@page import="model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />

<div class="content">
    <c:choose>
        <c:when test="${not empty errUpdate}">
            <div class='alert alert-danger'><c:out value="${errUpdate}"/></div>
        </c:when>
        <c:when test="${not empty sucUpdate}">
            <div class='alert alert-success'><c:out value="${sucUpdate}"/></div>
        </c:when>
    </c:choose>
    <h1>Account settings for <c:out value="${user.firstName} ${user.lastName}"/></h1>

    <h2>Change Email Address</h2>
    <form class="form-inline" method="POST" action="<%=request.getContextPath()%>/UpdateUser">
        <div class="form-group mb-2">
            <label>Email Address*</label>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" class="form-control" id="inputEmail" name="inputEmail" value="<c:out value="${user.username}"/>" required>
        </div>
        <button type="submit" class="btn btn-primary mb-2">Change Email</button>
    </form>
    <label>*Email Address must be unique</label>
    <hr>
    <h2>Change Password</h2>
    <form class="form-inline" method="POST" action="<%=request.getContextPath()%>/UpdateUser">
        <div class="form-group mx-sm-3 mb-2">
            <label for="inputPassword1" class="sr-only">Current Password</label>
            <input type="password" class="form-control" id="inputPassword1" name="inputPassword1" placeholder="Current Password" required>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label for="inputPassword2" class="sr-only">New Password</label>
            <input type="password" class="form-control" id="inputPassword2" name="inputPassword2" placeholder="New Password" required>*
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label for="inputPassword3" class="sr-only">New Password</label>
            <input type="password" class="form-control" id="inputPassword3" name="inputPassword3" placeholder="Confirm new Password" required>*
        </div>
        <button type="submit" class="btn btn-primary mb-2">Change Password</button>
    </form>
    <label>* Password stuff</label>
    <hr>
    <h2>Change Name</h2>
    <form class="form-inline" method="POST" action="<%=request.getContextPath()%>/UpdateUser">
        <div class="form-group mx-sm-3 mb-2">
            <input type="text" class="form-control" id="first_name" name="first_name" value="<c:out value="${user.firstName}"/>" required>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <input type="text" class="form-control" id="last_name" name="last_name" value="<c:out value="${user.lastName}"/>" required>
        </div>
        <button type="submit" class="btn btn-primary mb-2">Change Name on Account</button>
    </form>
    <hr>
    
    <c:choose>
        <c:when test="${user.usertype eq 'CUSTOMER'}">
            <h2>Change address</h2>
            <form class="form-inline" method="POST" action="<%=request.getContextPath()%>/UpdateUser">
        <div class="form-group mx-sm-3 mb-2">
            <input type="text" class="form-control" id="first_name" name="first_name" value="<c:out value="${a}"/>" required>
        </div>
        <button type="submit" class="btn btn-primary mb-2">Change Address</button>
    </form>
        </c:when>
    </c:choose>
    <!-- 
    TODO: if user is customer -> then change address in addition to others 
    
    -->

</div>
</body>
</html>
