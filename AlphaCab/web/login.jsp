<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />


<c:choose>
    <c:when test="${not empty user}">
        <c:redirect url="/index.jsp"/>
    </c:when>
</c:choose>
<div class="container">
    <c:choose>
        <c:when test="${not empty loggedOut}">
            <div class='alert alert-success'>You have logged out</div>
        </c:when>
        <c:when test="${not empty errMessageLogin}">
            <div class='alert alert-danger'><c:out value="${errMessageLogin}"/></div>
        </c:when>
    </c:choose>
    <div class="row justify-content-md-center">
        <div class="card w-50">
            <div class="card-header">
                <h1>Login</h1>
            </div>
            <form method="POST" action="<%=request.getContextPath()%>/Login">
                <div class="card-body">
                    <div class="panel">
                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-3">
                                    <input type="email" placeholder="Email" class="form-control" name="username" autofocus="autofocus" required/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-3">
                                    <input type="password" placeholder="Password" class="form-control" name="password" required/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-3">
                                    <input type="submit" value="Login" class="btn btn-primary"/> 
                                    &nbsp;&nbsp;
                                    <button type="button" class="btn btn-info" onclick="window.location.href = 'register.jsp'">Register</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
