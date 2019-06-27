<%-- 
    Document   : register
    Created on : 08-Nov-2018, 12:31:09
    Author     : Jacob
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />


<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=<c:out value="${initParam['googlePlaces']}" />&libraries=places"></script>
<div class="container">
    <c:choose>
        <c:when test="${not empty errMessageRegister}">
            <div class='alert alert-danger'><c:out value="${errMessageRegister}" /></div>
        </c:when>
    </c:choose>

    <div class="row justify-content-md-center">
        <div class="card w-50">
            <div class="card-header">
                <h1>Register As New Customer</h1>
            </div>
            <form method="POST" action="<%=request.getContextPath()%>/Register">
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
                                    <input type="text" placeholder="First Name" class="form-control" name="firstName" maxlength="15" required/>
                                    <script>
                                        var firstnameL = document.getElementById("firstName");
                                        var max = 15;
                                        if (firstnameL.value.length == max) {
                                            e.preventDefault();
                                        }
                                    </script>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-3">
                                    <input type="text" placeholder="Last Name" class="form-control" name="lastName" maxlength="25" required/>
                                    <script>
                                        var LastnameL = document.getElementById("lastName");
                                        var max = 25;
                                        if (LastnameL.value.length == max) {
                                            e.preventDefault();
                                        }
                                    </script>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-3">
                                    <input type="text" placeholder="Address" class="form-control" name="address" id="address_field" />
                                    <script>
                                        function init() {
                                            var input = document.getElementById('address_field');
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
                                    <input type="password" placeholder="Password" class="form-control" name="password1" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" maxlength="20" required/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-3">
                                    <input type="password" placeholder="Confirm Password" class="form-control" name="password2" maxlength="20" required/>
                                    <script>
                                        var password2L = document.getElementById("password2");
                                        var max = 20;
                                        if (password2L.value.length == max) {
                                            e.preventDefault();
                                        }
                                    </script>
                                </div>
                            </div>
                        </div>
                        <div id="message">
                            <h3>Password must contain the following:</h3>
                            <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
                            <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
                            <p id="number" class="invalid">A <b>number</b></p>
                            <p id="length" class="invalid">Minimum <b>8 characters</b></p>
                            <div class="row">
                                <div class="col">
                                    <div class="input-group mb-3">
                                        <input type="submit" value="Register" class="btn btn-primary"/>
                                    </div>
                                </div>
                                <script>
                                    var myInput = document.getElementById("password1");
                                    var letter = document.getElementById("letter");
                                    var capital = document.getElementById("capital");
                                    var number = document.getElementById("number");
                                    var length = document.getElementById("length");

                                    // When the user starts to type something inside the password field
                                    myInput.onkeyup = function () {
                                        var max = 20;
                                        if (myInput.value.length == max) {
                                            e.preventDefault();
                                        }
                                        // Validate lowercase letters
                                        var lowerCaseLetters = /[a-z]/g;
                                        if (myInput.value.match(lowerCaseLetters)) {
                                            letter.classList.remove("invalid");
                                            letter.classList.add("valid");
                                        } else {
                                            letter.classList.remove("valid");
                                            letter.classList.add("invalid");
                                        }

                                        // Validate capital letters
                                        var upperCaseLetters = /[A-Z]/g;
                                        if (myInput.value.match(upperCaseLetters)) {
                                            capital.classList.remove("invalid");
                                            capital.classList.add("valid");
                                        } else {
                                            capital.classList.remove("valid");
                                            capital.classList.add("invalid");
                                        }

                                        // Validate numbers
                                        var numbers = /[0-9]/g;
                                        if (myInput.value.match(numbers)) {
                                            number.classList.remove("invalid");
                                            number.classList.add("valid");
                                        } else {
                                            number.classList.remove("valid");
                                            number.classList.add("invalid");
                                        }

                                        // Validate length
                                        if (myInput.value.length >= 8) {
                                            length.classList.remove("invalid");
                                            length.classList.add("valid");
                                        } else {
                                            length.classList.remove("valid");
                                            length.classList.add("invalid");
                                        }
                                    }
                                </script>
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
