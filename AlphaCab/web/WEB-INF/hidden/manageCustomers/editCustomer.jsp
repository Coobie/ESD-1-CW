<%-- 
    Document   : editCustomer
    Created on : 22-Nov-2018, 17:25:50
    Author     : mherring
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=<c:out value="${initParam['googleAPIkey']}" />&libraries=places"></script>
<div class="row justify-content-md-center">
    <div class="card w-50">
        <div class="card-header">
            <h1>Edit Customer (<c:out value="${customer.firstName} ${customer.lastName}"/>)</h1>
        </div>
        <form method="POST" action="/AlphaCab/EditCustomer">
            <div class="card-body">
                <div class="panel">
                    <input type="hidden" name="update_id" value="<c:out value="${customer.ID}" />" />
                    <div class="row">
                        <div class="col">
                            <label>Email:</label>
                            <div class="input-group mb-3">
                                <input type="email" class="form-control" name="email" value="<c:out value="${customer.username}" />" autofocus="autofocus" required/>
                            </div>
                            <span class="badge badge-warning">Warning the customer will require the new email address to login</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label>First Name:</label>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" name="first_name" value="<c:out value="${customer.firstName}" />" required/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label>Last Name:</label>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" name="last_name" value="<c:out value="${customer.lastName}" />" required/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label>Address</label>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" name="address" id="address" value="<c:out value="${customer.address}" />" required/>
                            </div>
                            <script>
                                function init() {
                                    var input = document.getElementById('address');
                                    var autocomplete = new google.maps.places.Autocomplete(input);
                                }
                                google.maps.event.addDomListener(window, 'load', init);
                            </script>
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
