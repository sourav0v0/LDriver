<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
<link rel="icon" href="css/logo_4.ico">
<script type="text/javascript">
function vali()
{
	var x=document.getElementById("npass").value;
	var y=document.getElementById("cpass").value;
	if(x.length< 8)
		{
		alert("Password Length should be greater than 8 character");
	    return false;
		}
	else if (x != y) {
		alert(" Password dosent match");
	    return false;
	  }
	return true;
	
}
</script>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1 class="display-1">Change Password</h1>
  <div class="container p-2">
    <form action="User" method="post" onsubmit="return vali()">
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
          <input type="password" id="npass" class="form-control-plaintext rounded border border-dark" required/>
        </div>
      </div>
      <div class="mb-3 row" >
        <label for="L" class="col-sm-2 col-form-label">Confirm Password</label>
        <div class="col-sm-6">
          <input type="password" name="cpass" id="cpass" class="form-control-plaintext rounded border border-dark" required/>
        </div>
      </div>
      <div class="col-12">
        <button type="submit" class="btn btn-primary">Update Account</button>
      </div>
    </form>
  </div>
<jsp:include page="footer.html"/>
</body>
</html>