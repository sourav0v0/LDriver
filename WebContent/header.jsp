<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Document</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" >
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" ></script>
</head>
<body>

<% String type=(String)session.getAttribute("type"); 
if(type !=null && type.equals("employee")){ %>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">LDRIVE</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">  
        <a class="nav-link " aria-current="page" href="index.jsp">Home</a>
        <a class="nav-link" href="FileController">List Of Files Uploaded</a>
        <a class="nav-link" href="UploadFile.jsp">Upload Files</a>
        <a class="nav-link" href="#">Download Files</a>
        <a class="nav-link" href="changePassword.jsp">Change Password</a>
        <a class="nav-link" href="Main?action=logout">Logout</a>
      </div>
    </div>
  </div>
</nav>
<% }else if (type !=null && type.equals("admin")){%>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">LDRIVE</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">  
        <a class="nav-link " aria-current="page" href="index.jsp">Home</a>
        <a class="nav-link" href="#">Process Requested User</a>
        <a class="nav-link" href="#">Logout</a>
      </div>
    </div>
  </div>
</nav>
<% }else{%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">LDRIVE</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">  
        <a class="nav-link " aria-current="page" href="index.jsp">Home</a>
        <a class="nav-link" href="Login.jsp">Login</a>
        <a class="nav-link" href="Signup.jsp">Sign up</a>
        <a class="nav-link" href="#">About</a>
      </div>
    </div>
  </div>
</nav>
<%} %>

</body>
</html>