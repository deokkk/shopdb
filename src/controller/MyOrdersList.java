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
import vo.Category;
import vo.Orders;
import vo.OrdersAndItemAndCategory;;

@WebServlet("/mall/MyOrdersList")
public class MyOrdersList extends HttpServlet {
	private OrdersDao ordersDao;
	// 이름 + 전화번호 입력하는 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("MyOrderList.doGet()");
		
		if(request.getParameter("userName")==null) {
			request.getRequestDispatcher("/WEB-INF/jsp/mall/myOrdersListForm.jsp").forward(request, response);
		} else {
			request.setCharacterEncoding("utf-8");
			String userName = request.getParameter("userName");
			String userPhone = request.getParameter("userPhone");
			System.out.println(userPhone + "!!!!!!!!");
			Orders orders = new Orders();
			orders.setUserName(userName);
			orders.setUserPhone(userPhone);
			this.ordersDao = new OrdersDao();
			
			ArrayList<OrdersAndItemAndCategory> list = ordersDao.selectSearchOrders(orders);

			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/jsp/mall/myOrdersList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MyOrderList.doPost()");
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
		
		HashMap<String, Object> group = ordersDao.currentGroup(currentPage, pagePerGroup, lastPage);
		currentPageGroup = Integer.parseInt(group.get("currentPageGroup").toString());
		lastPageGroup = Integer.parseInt(group.get("lastPageGroup").toString());
		
		//ArrayList<OrdersAndItemAndCategory> list = ordersDao.selectSearchOrders(orders);
		ArrayList<OrdersAndItemAndCategory> list = ordersDao.selectSearchOrdersByPage(beginRow, rowPerPage, orders);
		
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

}
