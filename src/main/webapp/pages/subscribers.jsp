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
<h1>${admin }</h1>

<form name="f" method="get" action="searchplace">
Search place:<input type="text" name="place">
<input type="submit" value="search">
</form>
<table border="1">
<tr>
<th>SubScriber Email</th>
<th>SubScriber Name</th>
<th>SubScriber Place</th>
<th>Subscriber Phone No</th>
<th>Delete</th>
</tr>

<c:forEach var="sub" items="${subscriber}">
<tr>
<td>${sub.getEmail() }</td>
<td>${sub.getUser_name() }</td>
<td>${sub.getPlace() }</td>
<td>${sub.getPhone_number() }</td>
<td><a href="https://localhost:8057/unsubscribe?id=${sub.getUser_id() }">Delete</a></td>

</tr>
</c:forEach>

</table>
</body>
</html>