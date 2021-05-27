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
<link rel="stylesheet" href="/css/dashboard.css">
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
		<div class="category_selection">
			<!-- <form method="post" action="index.jsp" name="productForm"> -->
			<select name="category" id="category" onchange="return setValue();">
				<option selected disabled>Choose a Category</option>
				<option value="A" selected>Fruits and Vegetables</option>
				<option value="B">Groceries and Essentials</option>
				<option value="C">Snacks</option>
			</select>
			<!-- </form> -->
		</div>
		<section id="CategoryA">
			<div class='container'>
				<c:forEach var="j" items="${itemsList}">
					<c:if test="${j.category == 'A' && j.getQuantity() != '0' }">
						<div class='product' id=${j.getId()}>
							<div class="image_container">
								<img src=${j.getImageurl() } width="100" height="100">
							</div>
							<div class="product_detail__section">
								<h2 class='header'>
									<c:out value="${j.getItem_name()}" />
								</h2>
								<p class='price'>
									<c:out value="${j.getPrice()}" />
									Rs
								</p>
							</div>
							<form action="item/add" method="POST">
								<input type="hidden" name="id" value="${j.getId()}" /> <input
									type="submit" value="Add to Cart" class="btn" />
							</form>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</section>
		<section id="CategoryB">
			<div class='container'>
				<c:forEach var="j" items="${itemsList}">
					<c:if test="${j.category == 'B' && j.getQuantity() != '0' }">
						<div class='product' id=${j.getId()}>
							<div class="image_container">
								<img src=${j.getImageurl() } width="100" height="100">
							</div>
							<div class="product_detail__section">
								<h2 class='header'>
									<c:out value="${j.getItem_name()}" />
								</h2>
								<p class='price'>
									<c:out value="${j.getPrice()}" />
									Rs
								</p>
							</div>
							<form action="item/add" method="POST">
								<input type="hidden" name="id" value="${j.getId()}" /> <input
									type="submit" value="Add to Cart" class="btn" />
							</form>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</section>
		<section id="CategoryC">
			<div class='container'>
				<c:forEach var="j" items="${itemsList}">
					<c:if test="${j.category == 'C' && j.getQuantity() != '0'}">
						<div class='product' id=${j.getId()}>
							<div class="image_container">
								<img src=${j.getImageurl() } width="100" height="100">
							</div>
							<div class="product_detail__section">
								<h2 class='header'>
									<c:out value="${j.getItem_name()}" />
								</h2>
								<p class='price'>
									<c:out value="${j.getPrice()}" />
									Rs
								</p>
							</div>
							<form action="item/add" method="POST">
								<input type="hidden" name="id" value="${j.getId()}" /> <input
									type="submit" value="Add to Cart" class="btn" />
							</form>
						</div>
					</c:if>
				</c:forEach>
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