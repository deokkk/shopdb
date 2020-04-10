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
<title>mallIndex</title>
<style>
	.page-item.active .page-link {
		background-color: #E9ECEF;
		border-color: #E9ECEF;
		color: #000000;
	}
	.page-link {
		color: #000000;
	}
</style>
</head>
<body>
<div class="container-fluid row">
	<div class="col-3"></div>
	<div class="col-6" style="margin-top: 50px;">
		<h1 class="display-4">MALL INDEX</h1>
		<div class="row" style="margin-top: 20px; margin-bottom: 20px;">
			<c:if test="${SloginId == null}">
				<div class="col">
					<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/admin/InsertMember">관리자 회원가입</a>
				</div>
				<div class="col">
					<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/admin/AdminLogin">관리자 로그인</a>
				</div>
				<div class="col" style="text-align: right;">
					<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/mall/MyOrdersList">조회</a>
				</div>
			</c:if>
			<c:if test="${SloginId != null}">
				<div class="col">
					<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/admin">관리자 페이지</a>
				</div>
				<div class="col" style="text-align: right;">
					<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/mall/MyOrdersList">조회</a>
				</div>
			</c:if>
		</div>
		<!-- 
			상품리스트 -> 상품상세 + 주문폼 -> 주문액션 -> 나의 주문목록 
			다시 상품리스트 키면  주문정보(이름, 전화번호) 입력해야 리스트
		-->
		<c:if test="${list==null}">
			<p>상품이 없습니다.</p>
		</c:if>
		<div class="row" style="margin: 20px;">
			<c:forEach var="i" items="${list}" varStatus="stats">
				<div class="col-4" style="margin-bottom: 25px;">
					<div class="card bg-light" style="width: 300px; height: 420px;">
						<img class="card-img-top" style="width: 100%" src="${pageContext.request.contextPath}/imgs/${i.item.itemImg}">
						<div class="card-body text-center">
							<a class="card-link" href="${pageContext.request.contextPath}/mall/InsertOrders?itemId=${i.item.itemId}">${i.item.itemName}</a>
							<p class="card-text">${i.item.itemContents}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<nav aria-label="Page navigation example">
			<ul class="pagination" style="justify-content: center;">
				<li class="page-item">
					<c:if test="${currentPageGroup > 1}">
						<a class="page-link" href="${pageContext.request.contextPath}/mall/MallIndex?currentPage=${currentPageGroup-pagePerGroup}"><</a>
					</c:if>
				</li>
				<c:set var="doneLoop" value="true" />
				<c:forEach begin="${currentPageGroup}" end="${currentPageGroup+pagePerGroup-2}" step="1" varStatus="stats">
					<c:if test="${doneLoop}">
						<li class="page-item active">
							<c:if test="${(stats.index)==currentPage}">
								<span class="page-link">${currentPage}<span class="sr-only">(current)</span></span>
							</c:if>
						</li>
						<li class="page-item">
							<c:if test="${(stats.index)!=currentPage}">
								<a class="page-link" href="${pageContext.request.contextPath}/mall/MallIndex?currentPage=${stats.index}">${stats.index}</a>
							</c:if>
						</li>
						<c:if test="${(stats.index)==lastPage}">
							<c:set var="doneLoop" value="false"/>
						</c:if>
					</c:if>
				</c:forEach>
				<li class="page-item">
					<c:if test="${currentPageGroup < lastPageGroup}">
						<a href="${pageContext.request.contextPath}/mall/MallIndex?currentPage=${currentPageGroup-pagePerGroup}">></a>
					</c:if>
				</li>
			</ul>
		</nav>
	</div>
	<div class="col-3"></div>
</div>
</body>
</html>