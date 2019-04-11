<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Product</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<jsp:include page="productView.jsp">
    <jsp:param name="name" value="${product.name}"/>
    <jsp:param name="description" value="${product.description}"/>
    <jsp:param name="price" value="${product.price}"/>
    <jsp:param name="category" value="${product.category}"/>
    <jsp:param name="id" value="${product.id}"/>
    <jsp:param name="quantity" value="${product.quantity}"/>
</jsp:include>

<form action = "${pageContext.request.contextPath}/buyProduct" method="post">
    <p>
        <input type="hidden" name="id" value="${product.id}">
    </p>
    <p>
        <input type="hidden" name="quantity" value="1">
    </p>
    <p>Add To cart
        <input type="submit" name="addTo" value="submit">
    </p>

</form>

</body>
</html>
