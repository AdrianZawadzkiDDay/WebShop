<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="shop.model.Product" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Koszyk</title>
</head>
<body>
<jsp:include page="header.jsp"/>


<% Map<Product, Integer> map = ((Map<Product, Integer>) session.getAttribute("cart"));
    for(Map.Entry entry : map.entrySet()) { %>
<a href="/product?id=<%= ((Product) entry.getKey()).getId()%>">
    <p>Nazwa produktu: <%= ((Product) entry.getKey()).getName()%></p>
</a>
<p>Ilosc: <%= entry.getValue()%></p>
<% } %>

<form action="${pageContext.request.contextPath}/order" method="post">
    <p> Address <input type="text" name="address"/></p>

    <p>Submit <input type="submit" name="submit" value="submit" /> </p>

</form>

</body>
</html>
