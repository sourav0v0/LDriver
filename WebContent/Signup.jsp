<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
  <div class="alert alert-warning alert-dismissible fade show" role="alert">
    <div class="alert alert-heading">
     You Cannot Create an account you could request Admin to Create your account
      <hr>
      <ul class="list-group list-group-horizontal-xxl border-none">
      <li class="list-group-item bg-transparent">You Will Recive An Email</li>
      <li class="list-group-item bg-transparent">Once You Recived Your Username and Password please change Your Password</li>
      <li class="list-group-item bg-transparent">Dont Share Your Credentials</li>
      </ul>
    </div>
   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
  </div>
  <h1 class="display-1">SIGNUP</h1>
  <div class="container p-2">
    <form action="login">
      <div class="mb-3 row" >
        <label for="cid" class="col-sm-2 col-form-label">Company Id</label>
        <div class="col-sm-6">
          <input type="text" id="cid" placeholder="sapwrd12gLm"  class="form-control-plaintext rounded border border-dark" required>
        </div>
      </div>
      <div class="mb-3 row" >
        <label for="Mail" class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-6">
          <input type="email" id="email"  placeholder="email@example.com" class="form-control-plaintext rounded border border-dark" required>
        </div>
      </div>
      <div class="mb-3 row" >
        <label for="Name" class="col-sm-2 col-form-label">Full Name</label>
        <div class="col-sm-6">
          <input type="text" id="Name" placeholder="Sourav Prajapati" class="form-control-plaintext rounded border border-dark" required>
        </div>
      </div>
      <div class="mb-3 row" >
        <label for="L" class="col-sm-2 col-form-label">Phone Number</label>
        <div class="col-sm-6">
          <input type="number" id="phone"  placeholder="8976505050" class="form-control-plaintext rounded border border-dark" required>
        </div>
      </div>
      <div class="col-12">
        <button type="submit" class="btn btn-primary">Request For Account</button>
      </div>
    </form>
  </div>
</body>
</html>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
