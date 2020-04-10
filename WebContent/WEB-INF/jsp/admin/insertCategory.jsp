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
<title>insertCategoryForm</title>
</head>
<body>
<div class="container-fluid row">
	<div class="col-2"></div>
	<div class="col-8" style="margin-top: 50px;">
	<h1 class="display-4">카테고리 입력</h1>
	<form class="was-validated" style="margin-top: 50px;" method="post" action="${pageContext.request.contextPath}/admin/InsertCategory">
		<div class="form-group">
			<label for="categoryName">카테고리 이름 :</label>
			<input class="form-control" type="text" name="categoryName" id="categoryName" placeholder="Enter category_name" required>
		</div>
		<div style="text-align: right; margin-top: 30px;">
			<button class="btn btn-outline-light text-dark" type="submit">카테고리 추가</button>
		</div>
	</form>
	</div>
	<div class="col-2"></div>
</div>
</body>
</html>