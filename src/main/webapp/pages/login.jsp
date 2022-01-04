<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${msg}
<form name="f" method="post" action="/admin-login">
Enter Username:<input type="text" name="username" id="userName"><br>
Enter Password:<input type="password" name="password" id="password"><brs>
<input type="submit" value="submit">
</form>
</body>
</html>