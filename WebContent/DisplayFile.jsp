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
<body>
<jsp:include page="header.jsp"/>

<%List<FFile> file=(List<FFile>)session.getAttribute("files");
if(file==null)
	out.println("Null Files");
else{
if(file.size()==0){ %>
	<h1 class="display-1"> Your Havent Uploaded Any File</h1>
	<h3 class="display-3"> <a href="UploadFile.jsp">Click Here to Upload</a></h1>
<% }
else{%>
	<table class="table table-striped table-hover">
	<thead>
	    <tr>
	      <th scope="col">File Name</th>
	      <th scope="col">Download</th>
	    </tr>
	  </thead>
	  <tbody>
<% for(FFile f:file){%>
    <tr>
      <td><%=f.getName().toUpperCase()%></td>
      <td><a href="FileController?action=download&fid=<%=f.getId()%>" class="btn btn-primary">Download</a></td>
      <td><a href="FileController?action=view&fid=<%=f.getId()%>" target="_blank"  class="btn btn-primary">View</a></td>
      <td>
    <div class="dropdown"> 
  	<button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
    Share
  	</button>
  	<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
    	<li><a class="dropdown-item" href="FileController?action=generate&fid=<%=f.getId()%>&type=link">Link</a></li>
    	<li><a class="dropdown-item" href="FileController?action=generate&fid=<%=f.getId()%>&type=image">Image</a></li>
  	</ul>
	</div>
      <%-- <a href="FileController?action=generate&fid=<%=f.getId()%>" class="btn btn-primary">Share</a> --%>
      </td>
      
      
    </tr>   
<% }}} %>
  </tbody>
</table>
</body>
</html>