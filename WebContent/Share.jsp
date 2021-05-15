<%@page import="dao.UserDaoImpl"%>
<%@page import="pojo.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
 // on Private Share
</script>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1> Share page for <%-- <%= request.getParameter("fid") %> --%></h1>
<%
UserDaoImpl udi=new UserDaoImpl();
List<User> user=udi.getUsers();
%>
<form action="FileController" method="post">
<input type="hidden" name="action" value="shareType">
<input type="hidden" name="fid" value="<%=request.getParameter("fid")%>">
<label >Choose Type</label>  		
 for="success-outlined"   		<!-- 	RADIO BUTTON   name="type"-->
<div class="hide">
	<label for="user">Choose a Person to Share</label>
	<select class="form-select" name="privateName" aria-label="Default select example">
    <option selected>Choose Someone</option>
	<%for(int i=0;i<user.size();i++) {%>
	<option value="<%=user.get(i).getName() %>"><%=user.get(i).getName() %></option>
	<%}%>
</select>
</div>
</form>
</body>
</html>