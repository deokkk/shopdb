<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>updateOrders</title>
</head>
<body>
<div class="container-fluid row">
	<div class="col-3"></div>
	<div class="col-6" style="margin-top: 50px;">
		<h1 class="display-4">주문 수정</h1>
		<form class="was-validated" style="margin-top: 50px;" method="post" action="${pageContext.request.contextPath}/mall/UpdateOrders">
			<input type="hidden" name="currentPage" value="${currentPage}">
			<input type="hidden" name="itemPrice" value="${orders.item.itemPrice}">
			<div class="form-group">
				<label for="ordersId">orders_id :</label>
				<input class="form-control" type="text" name="ordersId" id="ordersId" value="${orders.orders.ordersId}" readonly required>
			</div>
			<div class="form-group">
				<label for="itemName">item_name :</label>
				<input class="form-control" type="text" name="itemName" id="itemName" value="${orders.item.itemName}" readonly required>
			</div>
			<div class="form-group">
				<label for="itemCount">item_count :</label>
				<input class="form-control" type="text" name="itemCount" id="itemCount" value="${orders.orders.itemCount}" required>
			</div>
			<div class="form-group">
				<label for="userName">user_name :</label>
				<input class="form-control" type="text" name="userName" id="userName" value="${orders.orders.userName}" required>
			</div>
			<div class="form-group">
				<label for="userPhone">user_phone :</label>
				<input class="form-control" type="text" name="userPhone" id="userPhone" value="${orders.orders.userPhone}" required>
			</div>
			<div class="form-group">
				<label for="userAddress">user_address :</label>
				<input class="form-control" type="text" name="userAddress" id="userAddress" value="${orders.orders.userAddress}" required>
			</div>
			<div style="text-align: right; margin-top: 30px;">
				<button class="btn btn-outline-light text-dark" type="submit">수정</button>
			</div>
		</form>
	</div>
	<div class="col-3"></div>
</div>
</body>
</html>