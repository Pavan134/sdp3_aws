<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<tr>
<th>validityDate</th>
<th>city</th>
<th>predictedMaxTemp</th>
</tr>

<c:forEach var="cast" items="${forecast}">
<tr>
<td>${cast.getValidityDate()  }</td>
<td>${cast.getCity() }</td>
<td>${cast.getPredictedMaxTemp() }</td>
</tr>
</c:forEach>

</table>
</body>
</html>