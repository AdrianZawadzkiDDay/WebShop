<%@ page import="shop.model.Product" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<% Map<Product, Integer> map = ((Map<Product, Integer>) session.getAttribute("cart"));
    for(Map.Entry entry : map.entrySet()) { %>
<a href="/product?id=<%= ((Product) entry.getKey()).getId()%>">
    <p>Nazwa produktu: <%= ((Product) entry.getKey()).getName()%></p>
</a>
    <p>Cena produktu: <%= ((Product) entry.getKey()).getPrice() %> </p>
    <p>Cena lacznie: <%= ((Product) entry.getKey()).getPrice() * (Integer) entry.getValue() %></p>

<p>Ilosc: <%= entry.getValue()%></p>
<% } %>

<br><br>
<% long sum = 0; %>
<% Map<Product, Integer> map2= ((Map<Product, Integer>) session.getAttribute("cart"));
    for(Map.Entry entry : map2.entrySet()) { %>

 <% sum = sum + (((Product) entry.getKey()).getPrice() * (Integer) entry.getValue()); %>
<% } %>

<p> Suma:  <%= sum%> </p>

</body>
</html>
