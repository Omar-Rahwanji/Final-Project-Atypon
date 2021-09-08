<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Heebo:wght@500&display=swap" rel="stylesheet">
    <title>Log in</title>
	<style>
		body {
			font-family: 'Heebo', sans-serif;			
			max-width: 600px;
			margin: auto;
			font-size: 24px;
			margin-top: 50px;
			background-color: #FFF2E0;
		}
		div{
		   text-align: center;
		}
		input{
			margin-left: 10px;
			height: 20px;
		}
		strong, p{
		  color:red;
		}
	</style>
</head>
<body>
	<form action="/login.do" method="POST">
		<fieldset>
			<legend><strong>Log in</strong></legend>
			<br />
			<div>
				<label>Admin key</label><input type="password" name="password" placeholder="password" required />
				<br /><br />
				<input type="submit" value="Login" />
				<p>${errorMessage}</p>
			</div>
		</fieldset>
	</form>
</body>
</html>