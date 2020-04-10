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
<title>insertItemForm</title>
</head>
<body>
<div class="container-fluid row">
	<div class="col-2"></div>
	<div class="col-8" style="margin-top: 50px;">
	<h1 class="display-4">상품 입력</h1>
	<form class="was-validated" style="margin-top: 50px;" method="post" action="${pageContext.request.contextPath}/admin/InsertItem">
		<div class="form-group">
			<label for="categoryId">category_name :</label>
			<select class="form-control" name="categoryId" id="categoryId" required>
				<option value="">카테고리 선택</option>
				<c:forEach var="c" items="${list}">
					<option value="${c.categoryId}">${c.categoryName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="itemName">item_name :</label>
			<input class="form-control" type="text" name="itemName" id="itemName" placeholder="Enter item name" required>
		</div>
		<div class="form-group">
			<label for="itemPrice">item_price :</label>
			<input class="form-control" type="text" name="itemPrice" id="itemPrice" placeholder="Enter item price" required>
		</div>
		<div class="form-group">
			<label for="itemContents">item_contents :</label>
			<textarea class="form-control" rows="3" cols="30" name="itemContents" id="itemContents" placeholder="Enter item contents" required></textarea>
		</div>
		<div style="text-align: right; margin-top: 30px;">
			<button class="btn btn-outline-light text-dark" type="submit">상품 입력</button>
		</div>
	</form>
	</div>
	<div class="col-2"></div>
</div>
</body>
</html>