<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/shared.css">
<link rel="stylesheet" href="/css/register.css">
<link href="https://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet" />
<title>Document</title>
</head>
<body>
<%-- 	<div id="form">
		<h1 class="register-title">Register</h1>
		<form:form action="processRegister" class="register-form"
			modelAttribute="User" method="POST">
			<label for="username">Username:</label>
			<form:input path="username" type="text" />
			<br>
			<br>
			<label for="password">Password:</label>
			<form:input path="password" type="password" />
			<br>
			<br>
			<label for="address1">Address Line 1:</label>
			<form:textarea path="address1" type="text" rows="2" cols="47" />
			<br>
			<br>
			<label for="address1">Address Line 2:</label>
			<form:textarea path="address2" type="text" rows="2" cols="47" />
			<br>
			<br>
			<label for="address1">Pincode:</label>
			<form:input path="pincode" type="number" />
			<br>
			<br>
			<label for="address1">Email:</label>
			<form:input path="email" type="email" />
			<br>
			<br>
			<label for="address1">Phonenumber:</label>
			<form:input path="phonenumber" type="number" />
			<br>
			<br>
			<button type="submit">Submit</button>
		</form:form>
		<br>
		<h4 class="register-content">
			Back to Login ?&nbsp<a href="/login">Login</a>
		</h4>
	</div> --%>
	<div id="container">
		<div class="form-wrap">
			<h1>Sign Up</h1>
			<form:form action="processRegister" class="register-form"
			modelAttribute="User" method="POST">
				<div class="form-group">
					<label for="username">Username</label> <form:input type="text"
						name="username" id="username" path="username" />
				</div>
				<div class="form-group">
					<label for="password">Password</label> <input type="password"
						name="password" id="password" path="password" />
				</div>
				<div class="form-group">
					<label for="address1">Address 1</label> 
					<form:textarea path="address1" type="text" rows="6" cols="36" />
				</div>
				<div class="form-group">
					<label for="address1">Address 2</label> 
					<form:textarea path="address2" type="text" rows="6" cols="36" />
				</div>
				<div class="form-group">
					<label for="pincode">Pincode</label> <form:input type="number"
						name="pincode" id="pincode" path="pincode" />
				</div>
				<div class="form-group">
					<label for="email">Email</label> 
					<form:input type="email" name="email"
						id="email" path="email" />
				</div>
				<div class="form-group">
					<label for="phonenumber">Phonenumber</label> <form:input type="number"
						name="phonenumber" id="phonenumber" path="phonenumber" />
				</div>
				<button type="submit" class="btn">Sign Up</button>
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
				No Account ? Try Signing Up Instead <a href="/login">Log in</a>
			</p>
		</footer>
	</div>
</body>
</html>