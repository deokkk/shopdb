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
<title>insertOrders</title>
</head>
<body>
<div class="container-fluid row">
	<div class="col-2"></div>
		<div class="col-8" style="margin-top: 50px;">
		<h1 class="display-4">상세보기</h1>
		<!-- item 상세화면 -->
		<table class="table table-hover" style="margin-top: 30px;">
			<tr>
				<th style="text-align: center; width: 20%">item_id</th>
				<td>${item.item.itemId}</td>
			</tr>
			<tr>
				<th style="text-align: center;">category_name</th>
				<td>${item.category.categoryId}</td>
			</tr>
			<tr>
				<th style="text-align: center;">item_name</th>
				<td>${item.item.itemName}</td>
			</tr>
			<tr>
				<th style="text-align: center;">item_price</th>
				<td>${item.item.itemPrice}</td>
			</tr>
			<tr>
				<th style="text-align: center;">item_contents</th>
				<td>${item.item.itemContents}</td>
			</tr>
			<tr>
				<th style="text-align: center; vertical-align: middle;">item_img</th>
				<td><img src="${pageContext.request.contextPath}/imgs/${item.item.itemImg}"></td>
			</tr>
		</table>
		<!-- 주문 폼 -->
		<form class="was-validated" style="margin-top: 50px;" method="post" action="${pageContext.request.contextPath}/mall/InsertOrders">
			<input type="hidden" name="itemId" value="${item.item.itemId}">
			<input type="hidden" name="itemPrice" value="${item.item.itemPrice}">
			<div class="form-group">
				<label for="itemCount">item_count :</label>
				<input class="form-control" type="text" name="itemCount" id="itemCount" placeholder="Enter item count" required>
			</div>
			<div class="form-group">
				<label for="userName">user_name :</label>
				<input class="form-control" type="text" name="userName" id="userName" placeholder="Enter user name" required>
			</div>
			<div class="form-group">
				<label for="userPhone">user_phone :</label>
				<input class="form-control" type="text" name="userPhone" id="userPhone" placeholder="Enter user phone" required>
			</div>
			<div class="form-group">
				<label for="userAddress">user_address :</label>
				<input class="form-control" type="text" name="userAddress" id="userAddress" placeholder="Enter user address" required>
			</div>
			<div style="text-align: right; margin-top: 30px;">
				<button class="btn btn-outline-light text-dark" type="submit">주문</button>
			</div>
		</form>
	</div>
	<div class="col-2"></div>
</div>
</body>
</html>