<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Upload File</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
	></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<form action="FileController" enctype="multipart/form-data" method="post">
			<input type="file" name="file" value="" class="form-controll"
				multiple> <input type="submit" value=" Upload File">
		</form>
	</div>
	<jsp:include page="footer.html" />
</body>
</html>

