<%@page import="dao.UserDaoImpl"%>
<%@page import="utils.FileHandel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="pojo.User"%>
<%@ page import="utils.FileHandel"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Remove User</title>
<link rel="icon" href="css/logo_4.ico">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
</head>
<body>
<jsp:include page="header.jsp"/>

<%
UserDaoImpl udi =new UserDaoImpl();
List<User> user=udi.getUsers();
if(user==null)
	out.println("Something went wrong");
else{
if(user.size()==0){ %>
	<h1 class="display-1">Sorry there are no user</h1>
<% }
else{%>
	<table class="table table-striped table-hover">
	<thead>
	    <tr>
	      <th scope="col">Pid</th>
	      <th scope="col">Email</th>
	      <th scope="col">Name</th>
	      <th scope="col">Action</th>
	    </tr>
	  </thead>
	  <tbody>
<% for(User f:user){%>
    <tr>
      <td class="fw-lighter"><%=f.getPid()%></td>
      <td class="fw-lighter"><%=f.getEmail()%></td>
      <td class="fw-lighter"><%=f.getName().toUpperCase()%></td>
      <td><a href="AdminController?action=deleteUser&pid=<%=f.getPid()%>" class="fw-lighter">Delete <i class="fa fa-check" aria-hidden="true"></i></a>
    </tr>   
<% }}} %>
  </tbody>
</table>
</body>
</html>