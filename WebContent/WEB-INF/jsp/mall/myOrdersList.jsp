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
		<h1>내 주문 내역</h1>
		<div style="text-align: right; margin-top: 30px;">
			<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/mall/MallIndex">목록</a>
		</div>
		<table class="table table-hover" style="margin-top: 30px;">
			<c:if test="${list.size()==0}">
				<tr>
					<td colspan="10">주문이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${list!=null}">
				<thead class="thead-light">
					<tr>
						<th style="text-align: center; vertical-align: middle; width: 5%;">orders_id</th>
						<th style="text-align: center; vertical-align: middle;">item_name</th>
						<th style="text-align: center; vertical-align: middle; width: 5%">item_count</th>
						<th style="text-align: center; vertical-align: middle;">user_name</th>
						<th style="text-align: center; vertical-align: middle;">user_phone</th>
						<th style="text-align: center; vertical-align: middle;">user_address</th>
						<th style="text-align: center; vertical-align: middle;">orders_price</th>
						<th style="text-align: center; vertical-align: middle;">orders_state</th>
						<th style="text-align: center; vertical-align: middle;">orders_date</th>
						<th style="text-align: center; vertical-align: middle;">주문수정</th>
						<th style="text-align: center; vertical-align: middle;">주문취소</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="o" items="${list}">
						<tr>
							<td style="text-align: center; vertical-align: middle;">${o.orders.ordersId}</td>
							<td style="text-align: center; vertical-align: middle;">${o.item.itemName}</td>
							<td style="text-align: center; vertical-align: middle;">${o.orders.itemCount}</td>
							<td style="text-align: center; vertical-align: middle;">${o.orders.userName}</td>
							<td style="text-align: center; vertical-align: middle;">${o.orders.userPhone}</td>
							<td style="text-align: center; vertical-align: middle;">${o.orders.userAddress}</td>
							<td style="text-align: center; vertical-align: middle;">${o.orders.ordersPrice}</td>
							<td style="text-align: center; vertical-align: middle;">${o.orders.ordersState}</td>
							<td style="text-align: center; vertical-align: middle;">${o.orders.ordersDate}</td>
							<c:if test="${o.orders.ordersState=='주문완료'}">
								<td style="text-align: center; vertical-align: middle;"><a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/mall/UpdateOrders?ordersId=${o.orders.ordersId}&currentPage=${currentPage}">주문수정</a></td>
								<td style="text-align: center; vertical-align: middle;"><a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/mall/UpdateOrders?ordersId=${o.orders.ordersId}&state=cancel&currentPage=${currentPage}">주문취소</a></td>
							</c:if>
							<c:if test="${o.orders.ordersState!='주문완료'}">
								<td style="text-align: center; vertical-align: middle;"><a class="btn btn-outline-light text-dark">주문수정</a></td>
								<td style="text-align: center; vertical-align: middle;"><a class="btn btn-outline-light text-dark">주문취소</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</c:if>
		</table>
		<nav aria-label="Page navigation example">
			<ul class="pagination" style="justify-content: center;">
				<li class="page-item">
					<c:if test="${currentPageGroup > 1}">
						<a class="page-link" href="${pageContext.request.contextPath}/mall/MyOrdersListPaging?userName=${userName}&userPhone=${userPhone}&currentPage=${currentPageGroup-pagePerGroup}"><</a>
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
								<a class="page-link" href="${pageContext.request.contextPath}/mall/MyOrdersListPaging?userName=${userName}&userPhone=${userPhone}&currentPage=${stats.index}">${stats.index}</a>
							</c:if>
						</li>
						<c:if test="${(stats.index)==lastPage}">
							<c:set var="doneLoop" value="false"/>
						</c:if>
					</c:if>
				</c:forEach>
				<li class="page-item">
					<c:if test="${currentPageGroup < lastPageGroup}">
						<a href="${pageContext.request.contextPath}/mall/MyOrdersListPaging?userName=${userName}&userPhone=${userPhone}&currentPage=${currentPageGroup-pagePerGroup}">></a>
					</c:if>
				</li>
			</ul>
		</nav>
	</div>
	<div class="col-1"></div>
</div>
</body>
</html>