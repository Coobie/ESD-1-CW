<%-- 
    Document   : editFees
    Created on : 05-Dec-2018, 14:10:26
    Author     : Jacob
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />

<div class="row justify-content-md-center">
    <div class="card w-50">
        <div class="card-header">
            <h1>Edit Fees </h1>
            <p>These changes will only take effect for new bookings after saving the changes</p>
        </div>
        <form method="POST" action="/AlphaCab/EditFees">
            <div class="card-body">
                <div class="panel">
                    <div class="row">
                        <div class="col">
                            <label>VAT (0.2 = 20%)</label>
                            <div class="input-group mb-3">
                                <input type="number" class="form-control" name="vat" min="0.0" value="${fee.vat}" step="0.01" autofocus required/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <label>Price per mile (200 = £2)</label>
                            <div class="input-group mb-3">
                                <input type="number" class="form-control" name="ppm" min="0" value="${fee.ppm}" step="1" autofocus required/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <label>Minium distance not to incur an extra charge (1 = 1 mile)</label>
                            <div class="input-group mb-3">
                                <input type="number" class="form-control" name="sDis" min="0" value="${fee.sDistance}" step="0.01" autofocus required/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <label>Extra charge for below distance above (200 = £2)</label>
                            <div class="input-group mb-3">
                                <input type="number" class="form-control" name="extra" min="0" value="${fee.xCharge}"  step="1" autofocus required/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <input value="edit_status" name="edit_status" type="hidden"/>
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
