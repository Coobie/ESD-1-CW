<%-- 
    Document   : index
    Created on : 07-Nov-2018, 15:31:46
    Author     : Jacob
--%>

<%@page import="model.User"%>
<%@page import="dao.UsersDaoImpl"%>
<%
    UsersDaoImpl usersDaoImpl = new UsersDaoImpl();
    User user = null;
    
    String username = "";
    if (session.getAttribute("user") != null) 
    {
        user = usersDaoImpl.findByName(session.getAttribute("user").toString());
    }
    
    
    
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<div class="content">
    <h1>Welcome to AlphaCab
    <%
        if (user != null) 
        {
            out.print(user.getFirstName() + " "+user.getLastName()+"</h1>");
            out.print("<h2>Current user level: "+ user.getUsertype()+"</h2>");
        }
        else
        {
            out.print("!</h1>");
        }
    %>
</div>
</body>
</html>
