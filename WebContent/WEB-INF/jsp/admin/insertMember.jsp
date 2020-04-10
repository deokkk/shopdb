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
<title>insertMember</title>
</head>
<body>
<div class="container-fluid row">
	<div class="col-3"></div>
	<div class="col-6" style="margin-top: 50px;">
		<h1 class="display-4">관리자 회원가입</h1>
		<form class="was-validated" style="margin-top: 50px;" method="post" action="${pageContext.request.contextPath}/admin/InsertMember">
			<div class="form-group">
				<label for="memberId">ID :</label>
				<input class="form-control" type="text" name="memberId" id="memberId" placeholder="Enter id" required>
			</div>	
			<div class="form-group">
				<label for="memberPw">PW :</label>
				<input class="form-control" type="password" name="memberPw" id="memberPw" placeholder="Enter password" required>
			</div>
			<div class="form-group">
				<label for="memberPw2">CONFIRM PW :</label>
				<input class="form-control" type="password" name="memberPw2" id="memberPw2" placeholder="Enter confirm password" required>
			</div>
			<div style="text-align: right; margin-top: 30px;">
				<button class="btn btn-outline-light text-dark" type="submit">회원가입</button>
			</div>
		</form>
	</div>
	<div class="col-3"></div>
</div>
</body>
</html>