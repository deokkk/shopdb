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
<title>index</title>
</head>
<body>
<div class="container-fluid row">
	<div class="col-2"></div>
	<div class="col-8" style="margin-top: 50px;">
		<h1 class="display-3">INDEX</h1>
		<c:if test="${SloginId != null}">
			<div>
				<mark>${SloginId}</mark>님 반갑습니다.
				<!-- 관리자 정보 수정 -->
				<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/admin/MemberList?memberId=${SloginId}">관리자 정보 관리</a>
				<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/admin/AdminLogout">로그아웃</a>
			</div>
		</c:if>
		<c:if test="${SloginId == null}">
			<div>
				<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/admin/AdminLogin">로그인</a>
			</div>
		</c:if>
		
		<!-- 관리자 메뉴 -->
		<div class="list-group" style="margin-top: 50px;">
			<!-- category CRU -->
			<a class="list-group-item list-group-item-action list-group-item-light" href="${pageContext.request.contextPath}/admin/CategoryList">카테고리 관리(C,R,U)</a>
			<!-- item CRU -->
			<a class="list-group-item list-group-item-action list-group-item-light" href="${pageContext.request.contextPath}/admin/ItemList">상품 관리(C,R,U)</a>
			<!-- orders RUD -->
			<a class="list-group-item list-group-item-action list-group-item-light" href="${pageContext.request.contextPath}/admin/OrdersList">주문 관리(R,U(orders_state))</a>
			<!-- mall -->
			<a class="list-group-item list-group-item-action list-group-item-light" href="${pageContext.request.contextPath}/mall/MallIndex">mall</a>
		</div>
	</div>
	<div class="col-2"></div>
</div>
</body>
</html>