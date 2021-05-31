<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Admin</title>
  <link href="css/AddAdmin.css" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="main">
<form action="login" method="post">
    <p class="sign" align="center">Add Admin</p>
    <input type="hidden" name ="action" value="addAdmin">
	<input class="un" type="text" name="email" placeholder="Email" required>
	<input class="pass" type="text" name="pass" placeholder="Password" required>
	<input class="submit" type="submit" value="Add Admin" class="button" >
</form>
</div>
</body>
</html>