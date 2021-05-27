<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Login</title>
<link href="https://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet" />
<link rel="stylesheet" href="/css/shared.css">
<link rel="stylesheet" href="/css/login.css">
</head>
<body>
	<div id="container">
		<div class="form-wrap">
			<h1>Login</h1>
			<form:form action="processLogin" class="login-form"
				modelAttribute="User" method="POST">
				<div class="form-group">
					<label for="username">Username</label>
					<form:input type="text" path="username" name="username"
						id="user-name" />
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<form:input type="password" path="password" name="password" id="password" />
				</div>
				 <button type="submit" class="btn">Log In</button>
				<div>
					<%
					if (request.getAttribute("error") != null) {
					%>
					<p class="error bottom-text"><%=request.getAttribute("error")%></p>
					<%
					}
					%>
				</div>
			</form:form>
		</div>
		<footer>
			<p>
				No Account ? Try Signing Up Instead <a href="/register">Sign Up</a>
			</p>
		</footer>
	</div>
</body>
</html>
