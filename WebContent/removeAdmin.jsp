<%@page import="dao.AdminDaoImpl"%>
<%@page import="utils.FileHandel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="pojo.Admin"%>
<%@ page import="utils.FileHandel"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
</head>
<body>
<jsp:include page="header.jsp"/>

<%
AdminDaoImpl adi=new AdminDaoImpl();
List<Admin> user=adi.getAdmins();
if(user==null)
	out.println("Server Error");
else{
if(user.size()==0){ %>
	<h1 class="display-1">No admin is there</h1>
<% }
else{%>
	<table class="table table-striped table-hover">
	<thead>
	    <tr>
	      <th scope="col">Email</th>
	      <th scope="col">Delete</th>
	    </tr>
	  </thead>
	  <tbody>
<% for(Admin f:user){%>
    <tr>
      <td class="fw-lighter"><%=f.getEmail()%></td>
      <td><a href="login?action=removeAdmin&email=<%=f.getEmail()%>" class="fw-lighter">Remove <i class="fa fa-check" aria-hidden="true"></i></a>
    </tr>   
<% }}} %>
  </tbody>
</table>
</body>
</html>