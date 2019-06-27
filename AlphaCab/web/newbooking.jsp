<%-- 
    Document   : newbooking
    Created on : 19-Nov-2018, 19:09:39
    Author     : Jacob
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=<c:out value="${initParam['googlePlaces']}"/>&libraries=places"></script>

<div class="container">
    <%
        if (request.getAttribute("errMessageBooking") != null)
        {
            out.println("<div class='alert alert-danger'>" + (String) request.getAttribute("errMessageBooking") + "</div>");
        }
    %>
    <div class="row justify-content-md-center">
        <div class="card w-50">
            <div class="card-header">
                New booking
            </div>
            <form method="POST" action="<%=request.getContextPath()%>/CreateBooking">
                <div class="card-body">
                    <div class="panel">
                        <div class="row">
                            <div class="col">
                                <label>Starting address:</label>
                                <div class="input-group mb-3">
                                    <input type="text" placeholder="Starting address" id="start_address" class="form-control" name="start_address" autofocus="autofocus" required/>
                                </div>
                                <script>
                                    function init() {
                                        var input = document.getElementById('start_address');
                                        var autocomplete = new google.maps.places.Autocomplete(input);
                                    }
                                    google.maps.event.addDomListener(window, 'load', init);
                                </script>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>Destination address:</label>
                                <div class="input-group mb-3">
                                    <input type="text" placeholder="Destination address" id="destination_address" class="form-control" name="destination_address" required/>
                                </div>
                            </div>
                            <script>
                                function init2() {
                                    var input = document.getElementById('destination_address');
                                    var autocomplete = new google.maps.places.Autocomplete(input);
                                }
                                google.maps.event.addDomListener(window, 'load', init2);
                            </script>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>Date of booking:</label>
                                <div class="input-group mb-3">
                                    <input type="date" placeholder="Date of booking" class="form-control" name="date" required/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>Time of booking:</label>
                                <div class="input-group mb-3">
                                    <input type="time" placeholder="Time of booking" class="form-control" name="time" required/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="input-group mb-3">
                                <input type="hidden" value="new" name="stage_booking"/>
                                <input type="submit" value="Create Booking" class="btn btn-primary"/>
                            </div>
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
