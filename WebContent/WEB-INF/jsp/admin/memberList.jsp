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
<title>memberList</title>
</head>
<body>
<div class="container-fluid row">
	<div class="col-3"></div>
	<div class="col-6" style="margin-top: 50px;">
		<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/admin">index</a>
		<h1 class="display-4">관리자 정보 수정</h1>
		<table class="table table-hover" style="margin-top: 30px;">
			<thead class="thead-light">
				<tr>
					<th style="text-align: center;">ID</th>
					<th style="text-align: center;">비밀번호 변경</th>
					<th style="text-align: center;">탈퇴</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="text-align: center;">${SloginId }</td>
					<td style="text-align: center;"><a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/admin/ConfirmPw?memberId=${SloginId}&state=update">비밀번호 변경</a></td>
					<td style="text-align: center;"><a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/admin/ConfirmPw?memberId=${SloginId}&state=delete">탈퇴</a></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="col-3"></div>
</div>
</body>
</html>