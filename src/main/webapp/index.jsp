
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDateTime" %>
<html>
<head>
    <title>Java Server Page</title>
</head>
<body>
<h1> Welcome to JSP page</h1>
<br>
<p>Today: <%= LocalDateTime.now().toString() %>  </p>
<br>

<p>Next 6 days: </p>
<%
    LocalDateTime today = LocalDateTime.now();
for(int i=0; i<6; i++){
    out.println(today.plusDays(i).toString());
    out.print("<br>");
}
%>

<%! int VisitCounter = 0; %>
<p> Licznik odwiedzin: <%= VisitCounter++%> </p>

<p>${person.name}</p>
<p>${person.lastName}</p>
<p>${person.age}</p>

</body>
</html>
