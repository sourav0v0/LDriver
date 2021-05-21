
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="dao.PublicDaoImpl"%>
<%@page import="dao.ShareDaoImpl"%>
<%@page import="pojo.FFile"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%
ShareDaoImpl sdi=new ShareDaoImpl();
PublicDaoImpl pdi=new PublicDaoImpl();
String email = (String)session.getAttribute("email");
if(email==null)response.sendRedirect("Login.jsp");
List<FFile> files=sdi.sharedWithUs(email);
if(files==null ) {%>
<h1 class="display-4" style="padding-bottom:25%">None File</h1>
<%	}else{
	if(files.size()==0){ %>
	<h1 class="display-4" style="padding-bottom:25%">Sorry No one have Share any file with you</h1>
	<% } else{%>
	<table class="table table-striped table-hover">
	<thead>
	    <tr>
	      <th scope="col">Name</th>	      
	      <th scope="col">Download</th>
	    </tr>
	  </thead>
	  <tbody>
	<% for(FFile f:files){%>
    <tr>
      <td class="fw-lighter"><%=f.getName()%></td>
      <td><a href="FileController?action=downloadShare&fid=<%=f.getId()%>" class="fw-lighter">Download <i class="fa fa-check" aria-hidden="true"></i></a>
    </tr>   
	<% }}} %>
  </tbody>
</table>
<jsp:include page="footer.html"></jsp:include>
</body>
</html>