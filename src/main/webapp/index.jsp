<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Staatliches&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Satisfy&display=swap" rel="stylesheet">    
	<title>My Grading System</title>
	<style>
		body {
			max-width: 500px;
			margin: auto;
			margin-top: 100px;
			text-align: center;
			font-size: 24px;
			background-color: #ECFFFB;
		}
		h1{
			font-family: 'Staatliches', cursive;
		}
		h2{
			font-family: 'Satisfy', cursive;
		}
		a{
			text-decoration: none;
			color: red;
			font-size:18px;
		}
		button{
			width: 12em;
			height: 3em;
		}
		strong{
			color: red;
		}
	</style>
</head>
<body>
	<h1>Welcome to <strong>My</strong> Multithreaded In-memory Database System</h1>
	<h2>"Redis is in danger ⚠"</h2>
	<button><a href="${pageContext.request.contextPath}/login.do">To login, click me</a></button>
</body>
</html>
