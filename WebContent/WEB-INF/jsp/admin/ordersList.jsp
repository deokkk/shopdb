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
<title>orderList</title>
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
	<div class="col-1"></div>
	<div class="col-10" style="margin-top: 50px;">
	<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/admin">index</a>
	<h1 class="display-4">주문 목록</h1>
	<table  class="table table-hover" style="margin-top: 30px;">
		<thead class="thead-light">
			<tr>
				<th style="text-align: center;">orders_id</th>
				<th style="text-align: center;">category_name</th>
				<th style="text-align: center;">item_name</th>
				<th style="text-align: center;">item_count</th>
				<th style="text-align: center;">user_name</th>
				<th style="text-align: center;">user_phone</th>
				<th style="text-align: center;">user_address</th>
				<th style="text-align: center;">orders_price</th>
				<th style="text-align: center;">orders_state</th>
				<th style="text-align: center;">orders_date</th>
				<th style="text-align: center;">수정</th>
			</tr>
		</thead>
		<c:if test="${list==null}">
			<tr>
				<td colspan="11">주문이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${list!=null}">
			<c:forEach var="o" items="${list}">
				<tr>
					<td style="text-align: center;">${o.orders.ordersId}</td>
					<td style="text-align: center;">${o.category.categoryName}</td>
					<td style="text-align: center;">${o.item.itemName}</td>
					<td style="text-align: center;">${o.orders.itemCount}</td>
					<td style="text-align: center;">${o.orders.userName}</td>
					<td style="text-align: center;">${o.orders.userPhone}</td>
					<td style="text-align: center;">${o.orders.userAddress}</td>
					<td style="text-align: center;">${o.orders.ordersPrice}</td>
					<td style="text-align: center;">${o.orders.ordersState}</td>
					<td style="text-align: center;">${o.orders.ordersDate}</td>
					<td style="text-align: center;">
						<c:if test="${o.orders.ordersState!='주문취소'}">
							<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/admin/UpdateOrdersState?ordersId=${o.orders.ordersId}">수정</a>
						</c:if>
						<c:if test="${o.orders.ordersState=='주문취소'}">
							<a class="btn btn-outline-light text-dark">수정</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<nav aria-label="Page navigation example">
		<ul class="pagination" style="justify-content: center;">
			<li class="page-item">
				<c:if test="${currentPageGroup > 1}">
					<a class="page-link" href="${pageContext.request.contextPath}/admin/OrdersList?currentPage=${currentPageGroup-pagePerGroup}"><</a>
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
							<a class="page-link" href="${pageContext.request.contextPath}/admin/OrdersList?currentPage=${stats.index}">${stats.index}</a>
						</c:if>
					</li>
					<c:if test="${(stats.index)==lastPage}">
						<c:set var="doneLoop" value="false"/>
					</c:if>
				</c:if>
			</c:forEach>
			<li class="page-item">
				<c:if test="${currentPageGroup < lastPageGroup}">
					<a href="${pageContext.request.contextPath}/admin/OrdersList?currentPage=${currentPageGroup-pagePerGroup}">></a>
				</c:if>
			</li>
		</ul>
	</nav>
	</div>
	<div class="col-1"></div>
</div>
</body>
</html>