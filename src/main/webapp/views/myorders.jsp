<%@page import="com.example.cart.model.Item"%>
<%@page import="com.example.cart.model.Invoice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="/css/shared.css">
<link rel="stylesheet" href="/css/myorders.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<!-- <script>
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
	});
</script> -->
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
		<%-- <div>
			<c:choose>
				<c:when test="${invoiceList.isEmpty()}">
					<h1>You don't have any orders yet</h1>
				</c:when>
				<c:otherwise>
					<c:forEach var="j" items="${invoiceList}">
						<h1>
							<c:out value="${j.getId()}"></c:out>
						</h1>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div> --%>
		<div class="container-xl">
			<div class="table-responsive">
				<div class="table-wrapper">
					<div class="table-title">
						<div class="row">
							<div class="col-sm-8">
								<h2>
									Order <b>Details</b>
								</h2>
							</div>
						</div>
					</div>
					<table class="table table-striped table-hover table-bordered">
						<thead>
							<tr>
								<th>#</th>
								<th>Order<i class="fa fa-sort"></i></th>
								<th>Items</th>
								<th>Subtotal<i class="fa fa-sort"></i></th>
								<th>Shipping and Tax</th>
								<th>Total<i class="fa fa-sort"></i></th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${invoiceList.isEmpty()}">
									<h1>You don't have any orders yet</h1>
								</c:when>
								<c:otherwise>
									<c:forEach var="j" items="${invoiceList}" varStatus="loop">
										<tr>
											<td><c:out value="${loop.index + 1}"></c:out></td>
											<td>Order<c:out value="${j.getId()}"></c:out></td>
											<td>
												<c:forEach var="k" items="${invoiceItems.get(j.getId())}">
													<p><c:out value="${k.key}"></c:out>&nbsp - <c:out value="${k.value}"></c:out></p>
													<br>
												</c:forEach>
											</td>
											<td>
												<c:out value="${j.getSubtotal()}"></c:out>
											</td>
											<td>
												<c:out value="${j.getShipping() + j.getTax()}"></c:out>
											</td>
											<td>
												<c:out value="${j.getTotal()}"></c:out>
											</td>
											<td><a href="/invoice/getPdf/${j.getId()}" class="view" title="View"
												data-toggle="tooltip">Download Invoice</a></td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<!-- 				<div class="clearfix">
						<div class="hint-text">
							Showing <b>5</b> out of <b>25</b> entries
						</div>
						<ul class="pagination">
							<li class="page-item disabled"><a href="#"><i
									class="fa fa-angle-double-left"></i></a></li>
							<li class="page-item"><a href="#" class="page-link">1</a></li>
							<li class="page-item"><a href="#" class="page-link">2</a></li>
							<li class="page-item active"><a href="#" class="page-link">3</a></li>
							<li class="page-item"><a href="#" class="page-link">4</a></li>
							<li class="page-item"><a href="#" class="page-link">5</a></li>
							<li class="page-item"><a href="#" class="page-link"><i
									class="fa fa-angle-double-right"></i></a></li>
						</ul>
					</div> -->
				</div>
			</div>
		</div>
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