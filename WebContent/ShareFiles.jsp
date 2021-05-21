<%@page import="dao.ShareDaoImpl"%>
<%@page import="dao.FileDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="pojo.Share"%>
<%@ page import="pojo.Couple"%>
<%@ page import="pojo.FFile"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<jsp:include page="header.jsp"/>
<body>
<%
String email1=(String)session.getAttribute("email");
if(email1==null)response.sendRedirect("Login.jsp");
HashMap<Integer,ArrayList<Couple>> list=(HashMap<Integer,ArrayList<Couple>>)request.getAttribute("share");
if(list.size()==0){ %>
<h2 class="display-2">You have Not Shared Any File</h2>
<%}else{ %>
<div class="card-deck ">
<% for(int s:list.keySet()){
	FileDaoImpl fdi=new FileDaoImpl();
	ShareDaoImpl sdi=new ShareDaoImpl();
	%>
<div class="card">
  	<div class="card-body">
  	<div class="cart-text"><h1><%=fdi.getFileWithId(s).getName()%></h1></div>
    <h5 class="card-title">You have Shared Your File <%=sdi.typeOfAccess(s)%></h5>
     <p class="card-text"><a href="FileController?action=deleteShareFile&fid=<%=fdi.getId(fdi.getFileWithId(s).getName()) %>" class="btn btn-danger">Delete</a></p> 
    <% ArrayList<Couple> temp= list.get(s);
     if(sdi.typeOfAccess(s).equals("private")) {%>
     <a class="btn btn-primary cart-footer" data-toggle="collapse" href="#collapseExample<%=s%>" role="button" aria-expanded="false" aria-controls="collapseExample<%=s%>">
	    List of Users
	 </a>
	  <div class="collapse" id="collapseExample<%=s%>">
	  <table class="table">
	  <thead>
    	<tr>
    	 <th scope="col">Name</th>
      	 <th scope="col">Action</th>
    	</tr>
      </thead>
      <tbody>
	  	 <% for(Couple name:temp){ %>
	  	 <tr>
	  	  	 <td><div class="card-text"> <%=name.getEmail() %></div></td>
	  	  	 <td><a href="FileController?action=removeUser&sid=<%=name.getId()%>" class="btn btn-outline-danger">Reject <i class="fa fa-ban" aria-hidden="true"></i></a></td>
	  	</tr>
	  	 <%} %>
	  </tbody>
	  </table>
	  </div>
    	</div>
</div>
	<% }else { %>
	 <p class="card-text">Every Member In Our Orginization can Download it AKA Public Share</p>
    <%}} %>
    
<% } %>
    
</div>
</body>
</body>
</html>