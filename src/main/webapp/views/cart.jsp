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
<link rel="stylesheet" href="/css/cart.css">
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
		<div class="shopping-cart">
			<!-- Title -->
			<div class="title">Shopping Bag</div>

			<!-- Product #1 -->
			<c:forEach var="j" items="${cartItems}">
					<div class="item">
						<div class="buttons">
							<span class="delete-btn"> 
							<a class="deletebtn" href="/item/deleteItem/${j.getId()}">
									<i class="far fa-times-circle"></i>
							</a>
							</span>
						</div>

						<div class="image">
							<img src=${j.getImage_url() } width="100" height="100">
						</div>

						<div class="description">
							<span>
								<c:out value="${j.getItem_name()}" />
							</span> 
							<span>
								<c:choose>
									<c:when test="${j.getCategory() == 'A' }">
										Fruits and Vegetables
									</c:when>
									<c:when test="${j.getCategory() == 'B' }">
										Groceries and Essentials
									</c:when>
									<c:otherwise>
    									Snacks
  									</c:otherwise>
								</c:choose>
							</span>
						</div>

						<div class="quantity">
							<c:if test="${j.getItem_quantity() < j.getItem_total_quantity() }">
								<a class="plus-btn" href="/item/addupdate/${j.getId()}"> <i class="fas fa-plus"></i></a> 
							</c:if>
							<c:if test="${j.getItem_quantity() == j.getItem_total_quantity()}">
								 <i class="fas fa-plus"></i>
							</c:if>
							<input type="text" name="name" value="${j.getItem_quantity()}"> 
							<c:if test="${j.getItem_quantity() != 1 }">
								<a class="minus-btn" href="/item/deleteupdate/${j.getId()}"> <i class="fas fa-minus"></i> </a>
							</c:if>
							<c:if test="${j.getItem_quantity() == 1 }">
								<i class="fas fa-minus"></i>
							</c:if>
						</div>

						<div class="total-price">
							<c:out value="${j.getItem_totalamount()}" />
						</div>
					</div>
			</c:forEach>
			<div class="item_total_amount">

						<div class="description">
							<span>
								<h4>Total Amount</h4>
							</span> 
						</div>

						<div class="total-price">
							<c:out value="${totalAmount}" />
						</div>
					</div>
			<!-- Product #2 -->
			<!-- <div class="item">
				<div class="buttons">
					<span class="delete-btn"></span> <span class="like-btn"></span>
				</div>

				<div class="image">
					<img src="item-2.png" alt="" />
				</div>

				<div class="description">
					<span>Maison Margiela</span> <span>Future Sneakers</span> <span>White</span>
				</div>

				<div class="quantity">
					<button class="plus-btn" type="button" name="button">
						<img src="plus.svg" alt="" />
					</button>
					<input type="text" name="name" value="1">
					<button class="minus-btn" type="button" name="button">
						<img src="minus.svg" alt="" />
					</button>
				</div>

				<div class="total-price">$870</div>
			</div> -->

			<!-- Product #3 -->
			<!-- <div class="item">
				<div class="buttons">
					<span class="delete-btn"></span> <span class="like-btn"></span>
				</div>

				<div class="image">
					<img src="item-3.png" alt="" />
				</div>

				<div class="description">
					<span>Our Legacy</span> <span>Brushed Scarf</span> <span>Brown</span>
				</div>

				<div class="quantity">
					<button class="plus-btn" type="button" name="button">
						<img src="plus.svg" alt="" />
					</button>
					<input type="text" name="name" value="1">
					<button class="minus-btn" type="button" name="button">
						<img src="minus.svg" alt="" />
					</button>
				</div>

				<div class="total-price">$349</div>
			</div> -->
		</div>
		
		<div class="proceed_button">
			<form action="/item/confirmation" method="POST">
				<input type="submit" value="Proceed" class="btn" />
			</form>
		</div>
		
	</main>
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/js/shared.js"></script>
	<!-- 	<script type="text/javascript">
	$('.minus-btn').on('click', function(e) {
	    e.preventDefault();
	    var $this = $(this);
	    var $input = $this.closest('div').find('input');
	    var value = parseInt($input.val());
	    if (value &amp;amp;gt; 1) {
	        value = value - 1;
	    } else {
	        value = 0;
	    }
	 
	  $input.val(value);
	 
	});
	 
	$('.plus-btn').on('click', function(e) {
	    e.preventDefault();
	    var $this = $(this);
	    var $input = $this.closest('div').find('input');
	    var value = parseInt($input.val());
	 
	    if (value &amp;amp;lt; 100) {
	        value = value + 1;
	    } else {
	        value =100;
	    }
	 
	    $input.val(value);
	});
	</script> -->
</body>
</html>