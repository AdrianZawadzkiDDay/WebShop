<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja</title>
</head>
<body>

<c:choose>
    <c:when test="${error != null}">
        <p style="color: red"> ${error} </p>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>

<form action="${pageContext.request.contextPath}/register" method="post" >
    <p>Mail: <input type="text" name="email" /> </p>
    <p>Password: <input type="text" name="password"/> </p>
    <p>Repeat password: <input type="text" name="repeatPassword" /> </p>
    <p>Username: <input type="text" name="username" /> </p>
    <p>Submit <input type="submit" name="submit" value="submit" /></p>
</form>

</body>
</html>
