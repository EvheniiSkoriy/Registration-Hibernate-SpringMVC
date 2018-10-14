<%--
  Created by IntelliJ IDEA.
  User: evghenii
  Date: 9/13/18
  Time: 1:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>SS - Home</title>
</head>
<body>
<h3>Welcome to Evghenii company</h3>

<hr>
<p>
    User: <security:authentication property="principal.username" />
<br>
    Role user: <security:authentication property="principal.authorities"/>
    <br><br>
    First name: ${user.firstName}, Last name: ${user.lastName}, Email: ${user.email}
<hr>

    <security:authorize access="hasAnyRole('MANAGER','ADMIN')">



    <%--Add a link only for managers--%>
    <a href="${pageContext.request.contextPath}/leaders">LeaderShip meeting</a>
    <br>
    </security:authorize>
    <security:authorize access="hasRole('ADMIN')">
    <br>
        <%--Add a link only for Admin--%>
        <a href="${pageContext.request.contextPath}/system">Systems</a>

     </security:authorize>
<hr>
<form:form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>
