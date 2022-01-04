<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Subscribe</title>
</head>
<body>
${msg}
<form action="gotosubscribe" method="post">

<table align="center" border="1">
<tr><td>Enter Mail-ID</td><td><input type="text" name="email"></td></tr>
<tr><td>Enter user_name:</td><td><input type="text" name="user_name"></td></tr>
<tr><td>Enter place:</td><td><input type="text" name="place"></td></tr>
<tr><td>Enter Phone-Number</td><td><input type="text" name="phone_number"></td></tr>
<tr><td><input type="submit"></td></tr>
</table>
</form>
</body>
</html>
