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
<form name="f" action="futuretemp" method="get">
Enter Validate Date:<input type="text" name="date"><br>
Enter Your place:<input type="text" name="place"><br>
<input type="submit" value="submit">
</form>

<h2>Date:</h2>
${cast.getValidityDate() }<br>
<h2>City:</h2>
${cast.getCity() }<br>
<h2>Predicted Temperature:</h2>
${cast.getPredictedMaxTemp() }

</body>
</html>