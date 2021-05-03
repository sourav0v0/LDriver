<%@page import="utils.FileHandel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="pojo.Ruser"%>
<%@ page import="utils.FileHandel"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<%List<Ruser> user=(List<Ruser>)session.getAttribute("users");
if(user==null)
	out.println("No New Request");
else{
if(user.size()==0){ %>
	<h1 class="display-1"> No New Request</h1>
<% }
else{%>
	<table class="table table-striped table-hover">
	<thead>
	    <tr>
	      <th scope="col">Pid</th>
	      <th scope="col">Email</th>
	      <th scope="col">Name</th>
	      <th scope="col">Process Request</th>
	    </tr>
	  </thead>
	  <tbody>
<% for(Ruser f:user){%>
    <tr>
      <td><%=f.getPid()%></td>
      <td><a href="#" class="fw-lighter"><%=f.getEmail()%></a></td>
      <td><a href="#" class="fw-lighter"><%=f.getName().toUpperCase()%></a></td>
      <td><a href="AdminController?action=status&status=approved&pid=<%=f.getPid()%>" class="fw-lighter">Approve</a>
      <a href="AdminController?action=status&status=reject&pid=<%=f.getPid()%>" class="fw-lighter">Reject</a></td>
    </tr>   
<% }}} %>
  </tbody>
</table>
</body>
</html>