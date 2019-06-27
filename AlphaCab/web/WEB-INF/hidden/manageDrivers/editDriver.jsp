<%-- 
    Document   : editDriver
    Created on : 21-Nov-2018, 23:06:16
    Author     : Jacob
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<div class="row justify-content-md-center">
    <div class="card w-50">
        <div class="card-header">
            <h1>Edit driver (<c:out value="${driver.firstName} ${driver.lastName}"/>)</h1>
        </div>
        <form method="POST" action="/AlphaCab/EditDriver">
            <div class="card-body">
                <div class="panel">
                    <input type="hidden" name="update_id" value="<c:out value="${driver.ID}" />" />
                    <div class="row">
                        <div class="col">
                            <label>Email:</label>
                            <div class="input-group mb-3">
                                <input type="email" class="form-control" name="email" value="<c:out value="${driver.username}" />" autofocus="autofocus" required/>
                            </div>
                            <span class="badge badge-warning">Warning the driver will require the new email address to login</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label>First Name:</label>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" name="first_name" value="<c:out value="${driver.firstName}" />" required/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label>Last Name:</label>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" name="last_name" value="<c:out value="${driver.lastName}" />" required/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label>Registration:</label>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" name="registration" value="<c:out value="${driver.reg}" />" required/>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="col">
                        <div class="input-group mb-3">
                            <input type="submit" value="Save Changes" class="btn btn-primary"/>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</div>

</body>
</html>
