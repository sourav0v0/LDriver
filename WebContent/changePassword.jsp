<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="index.jsp"/>
<h1 class="display-1">Change Password</h1>
  <div class="container p-2">
    <form action="User" method="post">
    <input type="hidden" name="action" value="changePassword"> 
    <input type="hidden" name="emails" value="<%=session.getAttribute("email")%>"> 
      <div class="mb-3 row" >
        <label for="email" class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-6">
          <input type="text" name="email" id="email"  class="form-control-plaintext rounded border border-dark" disabled="disabled" value="<%=session.getAttribute("email")%>">
        </div>
      </div>
      <div class="mb-3 row" >
        <label for="L" class="col-sm-2 col-form-label">Old Password</label>
        <div class="col-sm-6">
          <input type="password" name="pass" class="form-control-plaintext rounded border border-dark" required/>
        </div>
      </div>
      <div class="mb-3 row" >
        <label for="L" class="col-sm-2 col-form-label">New Password</label>
        <div class="col-sm-6">
          <input type="password" id="phone" class="form-control-plaintext rounded border border-dark" required/>
        </div>
      </div>
      <div class="mb-3 row" >
        <label for="L" class="col-sm-2 col-form-label">Confirm Password</label>
        <div class="col-sm-6">
          <input type="password" name="cpass" class="form-control-plaintext rounded border border-dark" required/>
        </div>
      </div>
      <div class="col-12">
        <button type="submit" class="btn btn-primary">Update Account</button>
      </div>
    </form>
  </div>
</body>
</html>