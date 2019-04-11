<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<title>Online Shop</title>
<jsp:include page="header.jsp"/>
<h2>Sklep Internetowy</h2>

<p>Lista produktow: </p>

<a href="${pageContext.request.contextPath}/addProduct">Dodaj Produkt</a>

<c:choose>
        <c:when test="${lastProductView != null}">
                <p>Last Viewed Product: </p>
                <jsp:include page="productView.jsp">
                        <jsp:param name="name" value="${lastProductView.name}"/>
                        <jsp:param name="description" value="${lastProductView.description}"/>
                        <jsp:param name="price" value="${lastProductView.price}"/>
                        <jsp:param name="category" value="${lastProductView.category}"/>
                        <jsp:param name="id" value="${lastProductView.id}"/>
                        <jsp:param name="quantity" value="${lastProductView.quantity}"/>
                </jsp:include>
        </c:when>
        <c:otherwise>
        </c:otherwise>
</c:choose>
<br><br>

<c:forEach items="${products}" var="product">
        <jsp:include page="productView.jsp">
                <jsp:param name="name" value="${product.name}"/>
                <jsp:param name="description" value="${product.description}"/>
                <jsp:param name="price" value="${product.price}"/>
                <jsp:param name="category" value="${product.category}"/>
                <jsp:param name="id" value="${product.id}"/>
                <jsp:param name="quantity" value="${product.quantity}"/>
        </jsp:include>
        <p><a href="${pageContext.request.contextPath}/product?id=${product.id}">Zobacz</a></p>
</c:forEach>

</body>
</html>
