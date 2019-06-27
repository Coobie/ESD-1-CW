<%-- 
    Document   : new-driver
    Created on : 22-Nov-2018, 14:43:07
    Author     : Jacob
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<div class="container">
    <%
        if (request.getAttribute("errMessageNewDriver") != null)
        {
            out.println("<div class='alert alert-danger'>" + (String) request.getAttribute("errMessageNewDriver") + "</div>");
        }
    %>
    <div class="row justify-content-md-center">
        <div class="card w-50">
            <div class="card-header">
                <h1> Register New Driver </h1>
            </div>
            <form method="POST" action="<%=request.getContextPath()%>/RegisterDriver">
                <div class="card-body">
                    <div class="panel">
                        <div class="row">
                            <div class="col">
                                <label>Email:</label>
                                <div class="input-group mb-3">
                                    <input type="email" placeholder="Email" class="form-control" name="username" autofocus="autofocus" required/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>First Name:</label>
                                <div class="input-group mb-3">
                                    <input type="text" placeholder="First Name" class="form-control" name="firstName" required/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>Last Name:</label>
                                <div class="input-group mb-3">
                                    <input type="text" placeholder="Last Name" class="form-control" name="lastName" required/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>Registration:</label>
                                <div class="input-group mb-3">
                                    <input type="text" placeholder="Registration:" class="form-control" name="reg" required/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-3">
                                    <input type="submit" value="Register" class="btn btn-primary"/>
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

