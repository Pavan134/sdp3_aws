<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<p>${msg }</p>
<button align="center"><a href="gotosubscribe">Subscribe Here</a></button>

<h1>${admin }</h1>

<h2>Search :</h2>
<form action="searchLocation" method="post">
<input type="text" name="location" placeholder="Enter Location">
<input type="submit" value="Search">
</form>
<c:if test="${not empty LocationData}">

<!-- For Location Data -->

<c:set var="keys" scope="session" value="${LocationData.keys()}"/>
<c:forEach var="key" items="${keys}">
${key }:${LocationData.get(key) }<br>
</c:forEach>
<br><br>


<!-- For Weather Data -->

<c:set var="keys" scope="session" value="${WeatherData.keys()}"/>
<c:forEach var="key" items="${keys}">
${key }:${WeatherData.get(key) }<br>
</c:forEach>

<br><br>

<!-- For Additional Forecasts Data -->

<c:set var="keys" scope="session" value="${AdditionalForecasts.keys()}"/>
<c:forEach var="key" items="${keys}">
${key }:<br>
Value: ${AdditionalForecasts.get(key).get("Value") }<br>
Category: ${AdditionalForecasts.get(key).get("Category") }<br><br>
</c:forEach>

</c:if>
<c:if test="${empty LocationData}">
Location Not Found
</c:if>
<c:if test = "${not empty admin }">
<table>
<tr>
<th>SubScriber Name</th>
<th>Subscriber Phone No</th>
</tr>

<c:forEach var="sub" items="${subscribers}">
<tr>
<td>${sub.getEmail() }</td>
<td>${sub.getPhone_number() }</td>
</tr>
</c:forEach>

</table>
</c:if>
</head>
</html>