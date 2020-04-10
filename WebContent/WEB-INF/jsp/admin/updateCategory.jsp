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
<title>updateCategory</title>
</head>
<body>
<div class="container-fluid row">
	<div class="col-2"></div>
	<div class="col-8" style="margin-top: 50px;">
	<h1 class="display-4">카테고리 수정</h1>
		<form class="was-validated" style="margin-top: 50px;" method="post" action="${pageContext.request.contextPath}/admin/UpdateCategory">
			<div class="form-group">
				<label for="categoryId">category_id :</label>
				<input class="form-control" type="text" name="categoryId" id="categoryId" value="${category.categoryId}" readonly required>
			</div>
			<div class="form-group">
				<label for="categoryName">category_name :</label>
				<input class="form-control" type="text" name="categoryName" i="categoryName" value="${category.categoryName}" required>
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