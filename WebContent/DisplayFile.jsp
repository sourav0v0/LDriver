<%@page import="utils.FileHandel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="pojo.FFile"%>
<%@ page import="utils.FileHandel"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
<body>
<jsp:include page="header.jsp"/>

<%
String email1=(String)session.getAttribute("email");
if(email1==null)response.sendRedirect("Login.jsp");
List<FFile> file=(List<FFile>)session.getAttribute("files");
if(file==null)
	out.println("Null Files");
else{
if(file.size()==0){ %>
	<h1 class="display-1"> Your Havent Uploaded Any File</h1>
	<h3 class="display-3"> <a href="UploadFile.jsp">Click Here to Upload</a></h3>
<% }
else{%>
<div class="container">
  <div class="row row-cols-auto">
<% for(FFile f:file){
String link="https://api.qrserver.com/v1/create-qr-code/?data='http://192.168.0.109:8080/Final/FileController?action=url,"+f.getId()+"&name=sourav&amp;size=100x100'";%>
<div class="col">
  <div class="card" style="width: 18rem;">
  <img src=<%=link %> class="card-img-top" alt="...">
  <div class="card-body">
    <h5 class="card-title"><%=f.getName() %></h5>
    <a href="FileController?action=download&fid=<%=f.getId()%>" class="btn btn-primary">Download <i class="fa fa-download" aria-hidden="true"></i></a>
    <a href="FileController?action=view&fid=<%=f.getId()%>" class="btn btn-primary">View <i class="fa fa-eye" aria-hidden="true"></i></a>
    <a href="FileController?action=share&fid=<%=f.getId()%>&name=<%=f.getName()%>" class="btn btn-primary">Share <i class="fas fa-share"></i></a>
  </div>     
  </div>
 </div>
<% }%>
</div></div>
<%}
} %>
</body>
</html>