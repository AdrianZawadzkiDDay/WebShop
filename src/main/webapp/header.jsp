<%@ page import="shop.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
</head>
<body>
MENU
<a href="${pageContext.request.contextPath}/addProduct">Dodaj Produkt</a>
<a href="${pageContext.request.contextPath}/shop">Main Page</a>

<% if(session.getAttribute("user") != null) {%>
<a href="${pageContext.request.contextPath}/logout">Wyloguj</a>
Zalogowany jako: <%= ((User) session.getAttribute("user")).getUserName()%>
<%} else {%>
<a href="${pageContext.request.contextPath}/login">Logowanie</a>
<a href="${pageContext.request.contextPath}/register">Rejestracja</a>
<%}%>

<% if(session.getAttribute("cart") != null) { %>
<a href="${pageContext.request.contextPath}/cart"> Koszyk</a>
<% }%>

</body>
</html>
