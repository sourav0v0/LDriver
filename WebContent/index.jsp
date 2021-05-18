<%@page import="javax.servlet.descriptor.JspConfigDescriptor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title> 
</head>
<body>
<jsp:include page="header.jsp"/>
  <div class="container nav-pills nav-justified">
  	<div class="row border-bottom rounded border-info" style="padding :5% ;text-align:center;">
  	  <div class="col-lg-6 col-md-6"><div class="img"><img src="Images/p1.jpg" class="img-thumbnail"></div></div>
  	  <div class="col-lg-6 col-md-6"><div class="display-6" style="padding-top: 10%;">It's convenient with the LDrive, you can easily store and share your files with whoever and whenever you want to.</div></div>
  	</div>
  	<div class="row border-bottom rounded border-info" style="padding:5%; align-content: center; ">
  	  <div class="col-lg-6 col-md-6"><div class="display-6" style="padding-top: 10%;">Share or download files of any size and type, as many times as you want, all for free.</div></div>
      <div class="col-lg-6 col-md-6"><div class="img"><img src="Images/p2.jpg" class="img-thumbnail" alt="image..."></div></div>
    </div>
    <div class="row border-bottom rounded border-info" style="padding:2%  ">
      <div class="col-lg-6 col-md-6"><div class="img"><img src="Images/p3.jpg" class="img-thumbnail" alt="image..."></div></div>
      <div class="col-lg-6 col-md-6"><div class="display-6" style="padding-top: 10%;">  Use QR Code Scanner to Scan and Download the files you want Quickly Specify.</div></div>
    </div>
    <div class="row border-bottom rounded border-info" style="padding:2% ">
      <div class="col-lg-6 col-md-6"><div class="display-6" style="padding-top: 10%;">LDrive allows you to view files before downloading such as videos,images,etc.</div></div>
      <div class="col-lg-6 col-md-6"><div class="img"><img src="Images/p4.jpg" class="img-thumbnail" alt="image..."></div></div>
    </div>
  </div>
<jsp:include page="footer.html"/>
</body>
</body>
</html>