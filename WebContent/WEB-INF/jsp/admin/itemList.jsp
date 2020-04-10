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
<title>itemList</title>
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
	<div class="col-2"></div>
	<div class="col-8" style="margin-top: 50px;">
	<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/admin">index</a>
	<h1 class="display-4">상품 목록</h1>
	<div style="text-align: right;">
		<a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath}/admin/InsertItem">상품 입력</a>
	</div>
		<table class="table table-hover" style="margin-top: 30px;">
			<thead class="thead-light">
				<tr>
					<th style="text-align: center;">item_id</th>
					<th style="text-align: center;" >category_name</th>
					<th style="text-align: center;">item_name</th>
					<th style="text-align: center;">item_price</th>
					<th style="text-align: center;">item_contents</th>
					<th style="text-align: center;">수정</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${list==null}">
					<tr>
						<td colspan="6">상품이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${list!=null}">
					<c:forEach var="i" items="${list}">
						<tr>
							<td style="text-align: center;">${i.item.itemId}</td>	<!-- i.getItemId -->
							<td style="text-align: center;">${i.category.categoryName}</td>
							<td style="text-align: center;">${i.item.itemName}</td>
							<td style="text-align: center;">${i.item.itemPrice}</td>
							<td>${i.item.itemContents}</td>
							<td style="text-align: center;"><a class="btn btn-outline-light text-dark" href="${pageContext.request.contextPath }/admin/UpdateItem?itemId=${i.item.itemId}">수정</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<nav aria-label="Page navigation example">
			<ul class="pagination" style="justify-content: center;">
				<li class="page-item">
					<c:if test="${currentPageGroup > 1}">
						<a class="page-link" href="${pageContext.request.contextPath}/admin/ItemList?currentPage=${currentPageGroup-pagePerGroup}"><</a>
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
								<a class="page-link" href="${pageContext.request.contextPath}/admin/ItemList?currentPage=${stats.index}">${stats.index}</a>
							</c:if>
						</li>
						<c:if test="${(stats.index)==lastPage}">
							<c:set var="doneLoop" value="false"/>
						</c:if>
					</c:if>
				</c:forEach>
				<li class="page-item">
					<c:if test="${currentPageGroup < lastPageGroup}">
						<a href="${pageContext.request.contextPath}/admin/ItemList?currentPage=${currentPageGroup-pagePerGroup}">></a>
					</c:if>
				</li>
			</ul>
		</nav>
	</div>
	<div class="col-2"></div>
</div>
</body>
</html>