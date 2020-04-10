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
<title>adminLogin</title>
</head>
<body>
<div class="container-fluid row">
	<div class="col-3"></div>
	<div class="col-6" style="margin-top: 50px;">
		<a href="${pageContext.request.contextPath}/mall/MallIndex">mall</a>
		<h1>관리자 로그인</h1>
		<form method="post" action="${pageContext.request.contextPath}/admin/AdminLogin">
			<div class="form-group">
				<label for="adminId">ID :</label>
				<input class="form-control" type="text" name="adminId" id="adminId" placeholder="Enter id" required>
			</div>	
			<div class="form-group">
				<label for="adminPw">PW :</label>
				<input class="form-control" type="password" name="adminPw" id="adminPw" placeholder="Enter password" required>
			</div>
			<div style="text-align: right; margin-top: 30px;">
				<button class="btn btn-outline-light text-dark" type="submit">로그인</button>
			</div>
		</form>
	</div>
	<div class="col-3"></div>
</div>
</body>
</html>