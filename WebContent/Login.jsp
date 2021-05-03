<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
  <div class="container  border border-dark bg-light position-absolute top-50 start-50 translate-middle" style="padding:50px">
    <form action="login" method="post">
     <div class="mb-3 row">
     	<div class="col-sm-6">
     	<input type="radio" class="btn-check btn-outline-primary" value="admin" name="type" id="option1" autocomplete="off" checked>
		<label class="btn btn-outline-primary" for="option1" >Admin</label>
		<input type="radio" class="btn-check btn-outline-primary" value="employee" name="type" id="option2" autocomplete="off" checked>
		<label class="btn btn-outline-primary" for="option2" >Employee</label>
		</div>
     </div>
      <div class="mb-3 row" >
        <label for="Mail" class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-6">
          <input type="email" id="Mail" name="email" placeholder="email@example.com" class="form-control-plaintext rounded border border-dark">
        </div>
      </div>
      <div class="md-3 row">
        <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
        <div class="col-sm-6">
          <input type="password" id="inputPassword"  name="password"  class="form-control-plaintext  rounded border border-dark">
        </div>
      </div>

      <div class="col-12">
        <button type="submit" class="btn btn-primary">Sign in</button>
      </div>
    </form>
  </div>
  
<jsp:include page="footer.html"/>
</body>
</html>
