<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zawad
  Date: 19.02.2019
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h2>Sklep Internetowy</h2>

<p>Logowanie:  </p>
<c:choose>
    <c:when test="${error != null}">
        <p style="color:red">incorrect login or password</p>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>

<form action="${pageContext.request.contextPath}/login" method="post">
    <p>Username <input type="text" name="username" /> </p>
    <p>Password <input type="password" name="password" /> </p>
    <p>Submit <input type="submit" name="submit" value="submit"/> </p>
</form>

</body>
</html>
