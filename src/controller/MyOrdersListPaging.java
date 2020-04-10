package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdersDao;
import vo.Orders;
import vo.OrdersAndItemAndCategory;

@WebServlet("/mall/MyOrdersListPaging")
public class MyOrdersListPaging extends HttpServlet {
	private OrdersDao ordersDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		Orders orders = new Orders();
		orders.setUserName(userName);
		orders.setUserPhone(userPhone);
		this.ordersDao = new OrdersDao();
		
		// 페이징
		int pagePerGroup = 5;
		int currentPageGroup = 0;
		int lastPageGroup = 0;
		int rowPerPage = 10;
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println(currentPage + " <--currentPage");
		int beginRow = (currentPage-1)*rowPerPage;
		int lastPage = ordersDao.searchOrdersLastPage(rowPerPage, userName, userPhone);
		System.out.println(lastPage + " <-- lastPage");
		System.out.println(beginRow + "," + rowPerPage);
		
		HashMap<String, Object> group = ordersDao.currentGroup(currentPage, pagePerGroup, lastPage);
		currentPageGroup = Integer.parseInt(group.get("currentPageGroup").toString());
		lastPageGroup = Integer.parseInt(group.get("lastPageGroup").toString());
		ArrayList<OrdersAndItemAndCategory> list = ordersDao.selectSearchOrdersByPage(beginRow, rowPerPage, orders);
		System.out.println(list.size() + " <-- list.size()");
		
		request.setAttribute("list", list);
		request.setAttribute("userName", userName);
		request.setAttribute("userPhone", userPhone);
		request.setAttribute("currentPageGroup", currentPageGroup);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pagePerGroup", pagePerGroup);
		request.setAttribute("lastPageGroup", lastPageGroup);
		request.setAttribute("lastPage", lastPage);
		
		request.getRequestDispatcher("/WEB-INF/jsp/mall/myOrdersList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
