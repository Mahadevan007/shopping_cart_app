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
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/shared.css">
<link rel="stylesheet" href="/css/review-page.css">
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
				<li class="main-nav__item"><a href="/invoice/myorders">View
						My Orders</a></li>
				<li class="main-nav__item"><a id="logout-button">Logout</a></li>
			</ul>
		</nav>
	</header>
	<nav class="mobile-nav">
		<ul class="mobile-nav__items">
			<li class="mobile-nav__item"><a href="/settings">Update
					Profile</a></li>
			<li class="mobile-nav__item"><a href="/invoice/myorders">View
					My Orders</a></li>
			<li class="mobile-nav__item"><a id="logout-button2">Logout</a></li>
		</ul>
		<div class='cart icon-basket'>
			<span class='number'>1</span>
		</div>
	</nav>
	<main>
		<div class="container">
			<div class="row">
				<div
					class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<address>
								<strong>Elf Cafe</strong> <br> 2135 Sunset Blvd <br>
								Los Angeles, CA 90026 <br> <abbr title="Phone">P:</abbr>
								(213) 484-6829
							</address>
						</div>
						<!-- 						<div class="col-xs-6 col-sm-6 col-md-6 text-right">
							<p>
								<em>Date: 1st November, 2013</em>
							</p>
							<p>
								<em>Receipt #: 34522677W</em>
							</p>
						</div> -->
					</div>
					<div class="row">
						<div class="text-center">
							<h1>Please Review Your Details</h1>
						</div>
						</span>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Product</th>
									<th>#</th>
									<th class="text-center">Price</th>
									<th class="text-center">Total</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="j" items="${cartItems}">
									<%-- <div class="order-confirmation_details">
										<div class="order-items-details_section">
											<c:out value="${j.getItem_name()}"></c:out>
											&nbsp<i class="fas fa-times"></i>&nbsp
											<c:out value="${j.getItem_quantity()}"></c:out>
										</div>
										<div class="order-items-price_section">
											<c:out value="${j.getItem_totalamount()}" />
										</div>
									</div> --%>
									<tr>
										<td class="col-md-9"><em><c:out
													value="${j.getItem_name()}"></c:out></em>
											</h4></td>
										<td class="col-md-1" style="text-align: center"><c:out
												value="${j.getItem_quantity()}"></c:out></td>
										<td class="col-md-1 text-center"><c:out
												value="${j.getItem_price()}" />&nbspRs</td>
										<td class="col-md-1 text-center"><c:out
												value="${j.getItem_totalamount()}" />&nbspRs</td>
									</tr>
								</c:forEach>

								<!-- 								<tr>
									<td class="col-md-9"><em>Lebanese Cabbage Salad</em>
										</h4></td>
									<td class="col-md-1" style="text-align: center">1</td>
									<td class="col-md-1 text-center">$8</td>
									<td class="col-md-1 text-center">$8</td>
								</tr>
								<tr>
									<td class="col-md-9"><em>Baked Tart with Thyme and
											Garlic</em>
										</h4></td>
									<td class="col-md-1" style="text-align: center">3</td>
									<td class="col-md-1 text-center">$16</td>
									<td class="col-md-1 text-center">$48</td>
								</tr> -->
								<tr>
									<td> </td>
									<td> </td>
									<td class="text-right">
										<p>
											<strong>Subtotal: </strong>
										</p>
										<p>
											<strong>Tax: </strong>
										</p>
										<p>
											<strong>Shipping: </strong>
										</p>
									</td>
									<td class="text-center">
										<p>
											<strong><c:out value="${orderAmount}"></c:out>&nbspRs</strong>
										</p>
										<p>
											<strong><c:out value="${tax}"></c:out>&nbspRs</strong>
										</p>
										<p>
											<strong><c:out value="50"></c:out>&nbspRs</strong>
										</p>
									</td>
								</tr>
								<tr>
									<td> </td>
									<td> </td>
									<td class="text-right"><h4>
											<strong>Total: </strong>
										</h4></td>
									<td class="text-center text-danger"><h4>
											<strong><c:out value="${totalCost}"></c:out>&nbspRs</strong>
										</h4></td>
								</tr>
							</tbody>
						</table>
						<form action='/invoice/charge' method='POST' id='checkout-form'>
							<input type='hidden' th:value='${amount}' name='amount' />
							<!-- NOTE: data-key/data-amount/data-currency will be rendered by Thymeleaf -->
							<script src='https://checkout.stripe.com/checkout.js'
								class='stripe-button' data-key="${stripePublicKey}"
								data-name='Shopping Cart' data-description='Pay Securely'
								data-image='https://i.pinimg.com/236x/2d/96/4a/2d964a6bf37d9224d0615dc85fccdd62--shopping-cart-logo-info-graphics.jpg'
								data-locale='auto' data-zip-code='false' data-amount="${amount}"
								data-currency="INR">
								
							</script>
						</form>
						</td>
					</div>
				</div>
			</div>
	</main>
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