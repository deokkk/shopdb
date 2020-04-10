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
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<title>confirmPw</title>
</head>
<body>
<div class="container-fluid row">
	<div class="col-3"></div>
	<div class="col-6" style="margin-top: 50px;">
		<h1 class="display-4">비밀번호 확인</h1>
		<c:if test="${state == 'update'}">
			<form class="was-validated" style="margin-top: 50px;" method="post" action="${pageContext.request.contextPath}/admin/UpdateMember">
		</c:if>
		<c:if test="${state == 'delete'}">
			<form class="was-validated" style="margin-top: 50px;" method="post" action="${pageContext.request.contextPath}/admin/DeleteMember">
		</c:if>
			<input type="hidden" name="state" value="${state}">
			<div class="form-group">
				<label for="memberId">ID :</label>
				<input class="form-control" type="text" name="memberId" id="memberId" value="${member.memberId}" readonly required>
			</div>	
			<div class="form-group">
				<label for="memberPw">PW :</label>
				<input class="form-control" type="password" name="memberPw" id="memberPw" placeholder="Enter password" required>
				<c:if test="${correct == 'notCorrect'}"><i class="fas fa-lightbulb"></i>비밀번호를 다시 입력하세요</c:if>
			</div>
			<div style="text-align: right; margin-top: 30px;">
				<button class="btn btn-outline-light text-dark" type="submit">확인</button>
			</div>
		</form>
	</div>
	<div class="col-3"></div>
</div>
</body>
</html>