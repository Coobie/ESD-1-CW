<%-- 
    Document   : confirmBooking
    Created on : 04-Dec-2018, 19:26:02
    Author     : Jacob
--%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=<c:out value="${initParam['googlePlaces']}"/>&libraries=places"></script>

<div class="container">
    <div class="row justify-content-md-center">
        <div class="card w-50">
            <div class="card-header">
                <h2>Please confirm booking</h2>
            </div>
            <form method="POST" action="<%=request.getContextPath()%>/CreateBooking">
                <div class="card-body">
                    <div class="panel">
                        <div class="row">
                            <div class="col">
                                <label>Starting address:</label>
                                <div class="input-group mb-3">
                                    <c:set var = "startu" value="${fn:replace(start_address, ' ', '+')}" />
                                    <label><a target="_blank" href="https://www.google.com/maps/search/?api=1&query=<c:out value="${fn:replace(startu, ',', '%2C')}" />"><c:out value="${start_address}" /></a></label>
                                    <input type="hidden" name="start_address" value="${start_address}"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>Destination address:</label>
                                <div class="input-group mb-3">
                                    <c:set var = "endu" value="${fn:replace(destination_address, ' ', '+')}" />
                                    <label><a target="_blank" href="https://www.google.com/maps/search/?api=1&query=<c:out value="${fn:replace(endu, ',', '%2C')}" />"><c:out value="${destination_address}" /></a></label>
                                    <input type="hidden" name="destination_address" value="${destination_address}"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>Date:</label>
                                <div class="input-group mb-3">
                                    <label><fmt:formatDate type = "date" value = "${date}" /></label>
                                    <input type="hidden" name="date" value="${date}"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>Time:</label>
                                <div class="input-group mb-3">
                                    <label><fmt:formatDate type = "time" pattern = "HH:mm" value = "${time}" /></label>
                                    <input type="hidden" name="time" value="${time}"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>Distance:</label>
                                <div class="input-group mb-3">
                                    <label><c:out value="${distance}" /> miles</label>
                                    <input type="hidden" name="distance" value="${distance}"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>Fee:</label>
                                <div class="input-group mb-3">
                                    <fmt:parseNumber var="fee1" type ="number" value="${fee}" />
                                    <label><fmt:formatNumber value = "${fee1 / 100}" type = "currency"/></label>
                                    <input type="hidden" name="fee" value="${fee}"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-3">
                                    <button type="submit" class="btn btn-warning mb-2" name="stage_booking" value="adjust">Adjust booking</button>&nbsp;
                                    <button type="submit" class="btn btn-primary mb-2" name="stage_booking" value="confirmed">Confirm booking</button>&nbsp;
                                </div>
                                <div class="input-group mb-3">
                                <p> By confirming you agree to<a href="#"> AlphaCab's terms and conditions</a></p>
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
