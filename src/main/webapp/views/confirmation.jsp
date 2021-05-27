<%@page import="com.stripe.Stripe"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/shared.css">
<link rel="stylesheet" href="/css/confirmation.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
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
				<li class="main-nav__item"><a href="/settings">Update
						Profile</a></li>
				<li class="main-nav__item"><a href="/invoice/myorders">View My Orders</a></li>
				<li class="main-nav__item"><a id="logout-button">Logout</a></li>
			</ul>
		</nav>
	</header>
	<nav class="mobile-nav">
		<ul class="mobile-nav__items">
			<li class="mobile-nav__item"><a href="/settings">Update
					Profile</a></li>
			<li class="mobile-nav__item"><a href="/invoice/myorders">View My Orders</a></li>
			<li class="mobile-nav__item"><a id="logout-button2">Logout</a></li>
		</ul>
		<div class='cart icon-basket'>
			<span class='number'>1</span>
		</div>
	</nav>
	<main>
		<section class="order-confirmation">
			<div class="order-shipping-address">
				<h2>Shipping To</h2>
				<p>
					<c:out value="${user.getUsername()}"></c:out>
				</p>
				<p>
					<c:out value="${user.getAddress1()}"></c:out>
				</p>
				<p>
					<c:out value="${user.getAddress2()}"></c:out>
				</p>
				<p>
					<c:out value="${user.getPincode()}"></c:out>
				</p>
				<p>
					<c:out value="${user.getPhonenumber()}"></c:out>
				</p>
			</div>
			<div class="order-confirmation_header">
				<h4>Please Review Your Order</h4>
			</div>
			<hr>
			<c:forEach var="j" items="${cartItems}">
				<div class="order-confirmation_details">
					<div class="order-items-details_section">
						<c:out value="${j.getItem_name()}"></c:out>
						&nbsp<i class="fas fa-times"></i>&nbsp
						<c:out value="${j.getItem_quantity()}"></c:out>
					</div>
					<div class="order-items-price_section">
						<c:out value="${j.getItem_totalamount()}" />
					</div>
				</div>
			</c:forEach>
			<div class="order-confirmation_payment_details">
				<div class="payment_details">Order Total</div>
				<div class="payment_amount">
					<c:out value="${orderAmount}"></c:out>
				</div>
			</div>
			<div class="order-confirmation_payment_details">
				<div class="payment_details">Tax</div>
				<div class="payment_amount">
					<c:out value="${tax}"></c:out>
				</div>
			</div>
			<div class="order-confirmation_payment_details">
				<div class="payment_details">Shipping Cost</div>
				<div class="payment_amount">
					<c:out value="50"></c:out>
				</div>
			</div>
			<div class="order-confirmation_payment_details">
				<div class="payment_details">Total Amount</div>
				<div class="payment_amount">
					<c:out value="${totalCost}"></c:out>
				</div>
			</div>
			<div class="proceed_button">
				<form action='/invoice/charge' method='POST' id='checkout-form'>
					<input type='hidden' th:value='${amount}' name='amount' />
					<!-- NOTE: data-key/data-amount/data-currency will be rendered by Thymeleaf -->
					<script src='https://checkout.stripe.com/checkout.js'
						class='stripe-button' data-key="${stripePublicKey}"
						data-name='Shopping Cart' data-description='Pay Securely'
						data-image='https://i.pinimg.com/236x/2d/96/4a/2d964a6bf37d9224d0615dc85fccdd62--shopping-cart-logo-info-graphics.jpg'
						data-locale='auto' data-zip-code='false' data-amount="${amount}" data-currency="INR">
					</script>
				</form>
			</div>
		</section>
	</main>
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/js/shared.js"></script>
	<script type="text/javascript">
		function setValue() {
			let value = document.getElementById("category").value;
			console.log(value);
			if (value == "A") {
				document.getElementById("CategoryB").style.display = "none";
				document.getElementById("CategoryC").style.display = "none";
				document.getElementById("CategoryA").style.display = "block";
			} else if (value == "B") {
				document.getElementById("CategoryB").style.display = "block";
				document.getElementById("CategoryC").style.display = "none";
				document.getElementById("CategoryA").style.display = "none";
			} else if (value == "C") {
				document.getElementById("CategoryB").style.display = "none";
				document.getElementById("CategoryC").style.display = "block";
				document.getElementById("CategoryA").style.display = "none";
			}
			return false;
		}
	</script>
</body>
</html>