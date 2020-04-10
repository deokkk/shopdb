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
	<div class="col-2"></div>
	<div class="col-8" style="margin-top: 50px;">
	<h1 class="display-4">주문 수정</h1>
	<form class="was-validated" style="margin-top: 50px;" method="post" action="${pageContext.request.contextPath}/admin/UpdateOrdersState">
		<div class="form-group">
			<label for="ordersId">orders_id</label>
			<input class="form-control" type="text" name="ordersId" id="ordersId" value="${orders.orders.ordersId}" readonly required>
		</div>
		<div class="form-group">
			<label for="categoryName">category_name :</label>
			<input class="form-control" type="text" name="categoryName" id="categoryName"  value="${orders.category.categoryName}" readonly required>
		</div>
		<div class="form-group">
			<label for="itemName">item_name :</label>
			<input class="form-control" type="text" name="itemName" id="itemName" value="${orders.item.itemName}" readonly required>
		</div>
		<div class="form-group">
			<label for="itemCount">item_count :</label>
			<input class="form-control" type="text" name="itemCount" id="itemCount" value="${orders.orders.itemCount}" readonly required>
		</div>
		<div class="form-group">
			<label for="userName">user_name :</label>
			<input class="form-control" type="text" name="userName" id="userName" value="${orders.orders.userName}" readonly required>
		</div>
		<div class="form-group">
			<label for="userPhone">user_phone</label>
			<input class="form-control" type="text" name="userPhone" id="userPhone" value="${orders.orders.userPhone}" readonly required>
		</div>
		<div class="form-group">
			<label for="userAddress">user_address :</label>
			<input class="form-control" type="text" name="userAddress" id="userAddress" value="${orders.orders.userAddress}" readonly required>
		</div>
		<div class="form-group">
			<label for="ordersPrice">orders_price :</label>
			<input class="form-control" type="text" name="ordersPrice" id="ordersPrice" value="${orders.orders.ordersPrice}" readonly required>
		</div>
		<div class="form-group">
			<label for="ordersDate">orders_date :</label>
			<input class="form-control" type="text" name="ordersDate" id="ordersDate" value="${orders.orders.ordersDate}" readonly required>
		</div>
		<div class="form-group">
			<label for="ordersState">orders_state :</label>
			<select class="form-control" name="ordersState" id="ordersState" required>
				<c:forEach var="s" items="${state}">
					<option value="${s}" <c:if test="${s.equals(orders.orders.ordersState)}">selected</c:if>>${s}</option>
				</c:forEach>
			</select>
		</div>
		<div style="text-align: right; margin-top: 30px;">
			<button class="btn btn-outline-light text-dark" type="submit">수정</button>
		</div>
	</form>
	</div>
	<div class="col-2"></div>
</div>
</body>
</html>