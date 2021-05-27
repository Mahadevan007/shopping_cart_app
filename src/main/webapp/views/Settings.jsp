<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/shared.css">
<link rel="stylesheet" href="/css/settings.css">
<title>Document</title>
</head>
<body>
	<div class="backdrop"></div>
	<div class="modal">
		<h1 class="modal__title">Do you want to continue?</h1>
		<div class="modal__actions">
			<a href="/logout" class="modal__action modal__action--yes">Yes!</a>
			<button class="modal__action modal__action--no" type="button">No!</button>
		</div>
	</div>
	<header class="main-header">
		<div>
			<button class="toggle-button">
				<span class="toggle-button__bar"></span> <span
					class="toggle-button__bar"></span> <span class="toggle-button__bar"></span>
			</button>
			<a href="/dashboard" class="main-header__brand"> <img
				src="/images/shopping-cart.jpeg" alt="ShopMart - Shopping Store">
			</a>
		</div>
		<nav class="main-nav">
			<ul class="main-nav__items">
				<li class="main-nav__item"><a href="/settings">Update Profile</a></li>
				<li class="main-nav__item"><a href="/invoice/myorders">View My Orders</a></li>
				<li class="main-nav__item"><a id="logout-button">Logout</a></li>
			</ul>
		</nav>
	</header>
	<nav class="mobile-nav">
		<ul class="mobile-nav__items">
			<li class="mobile-nav__item"><a href="/settings">Update Profile</a></li>
			<li class="mobile-nav__item"><a href="/invoice/myorders">View My Orders</a></li>
			<li class="mobile-nav__item"><a id="logout-button2">Logout</a></li>
		</ul>
	</nav>
	<br>
	<br>
	<section>
		<!-- 		<div id="contact">Update Profile</div> -->
		<main>
			<div id="contactForm">

				<h1>Let's Update Your Profile</h1>
				<small>I'll get back to you as quickly as possible</small>

				<form:form action="updateForm" class="update-form" modelAttribute="User"
					method="POST">
					<form:input path="id" type="hidden" />
					<form:input path="role" type="hidden" />
					<form:input path="flag" type="hidden" />
					<div>
						<label>Username:</label>
						<form:input path="username" type="text" />
					</div>
					<div>
						<label>Password:</label>
						<form:input path="password" type="password" />
					</div>
					<div>
						<label>Address 1:</label>
						<form:textarea path="address1" type="text" />
					</div>
					<div>
						<label>Address 2:</label>
						<form:textarea path="address2" type="text" />
					</div>
					<div>
						<label>Pincode:</label>
						<form:input path="pincode" type="number" />
					</div>
					<div>
						<label>Email:</label>
						<form:input path="email" type="email" />
					</div>
					<div>
						<label>Phonenumber:</label>
						<form:input path="phonenumber" type="number" />
					</div>
					<!-- 					<input placeholder="Name" type="text" required />
					<input placeholder="Email" type="email" required />
					<input placeholder="Subject" type="text" required />
					<textarea placeholder="Comment"></textarea> -->
					<input class="formBtn" type="submit" />
					<input class="formBtn" type="reset" />
				</form:form>
			</div>
	</section>
	</main>
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/js/shared.js"></script>
	<!-- 	<script>
	  $('#contact').click(function() {
	    $('#contactForm').fadeToggle();
	  })
	  $(document).mouseup(function (e) {
	    var container = $("#contactForm");

	    if (!container.is(e.target) // if the target of the click isn't the container...
	        && container.has(e.target).length === 0) // ... nor a descendant of the container
	    {
	        container.fadeOut();
	    }
	  });
	</script> -->
</body>
</html>