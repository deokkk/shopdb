<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>ordersDetail</title>
</head>
<body>
<div class="container-fluid row">
	<div class="col-2"></div>
	<div class="col-8" style="margin-top: 50px;">
		<h1 class="display-4">주문내역</h1>
		<div style="text-align: right; margin-top: 30px;">
			<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/mall/MallIndex">목록</a>
		</div>
		<table class="table table-hover" style="margin-top: 30px;">
			<tr>
				<th style="text-align: center; width: 20%;">orders_id</th>
				<td>${orders.orders.ordersId}</td>
			<tr>
			<tr>
				<th style="text-align: center;">item_name</th>
				<td>${orders.item.itemName}</td>
			<tr>
			<tr>
				<th style="text-align: center;">item_count</th>
				<td>${orders.orders.itemCount}</td>
			<tr>
			<tr>
				<th style="text-align: center;">orders_price</th>
				<td>${orders.orders.ordersPrice}</td>
			<tr>
			<tr>
				<th style="text-align: center;">user_name</th>
				<td>${orders.orders.userName}</td>
			<tr>
			<tr>
				<th style="text-align: center;">user_phone</th>
				<td>${orders.orders.userPhone}</td>
			<tr>
			<tr>
				<th style="text-align: center;">user_address</th>
				<td>${orders.orders.userAddress}</td>
			<tr>
			<tr>
				<th style="text-align: center;">orders_state</th>
				<td>${orders.orders.ordersState}</td>
			<tr>
			<tr>
				<th style="text-align: center;">orders_date</th>
				<td>${orders.orders.ordersDate}</td>
			<tr>
		</table>
	</div>
	<div class="col-2"></div>
</div>
</body>
</html>