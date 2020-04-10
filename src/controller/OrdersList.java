package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdersDao;
import vo.OrdersAndItemAndCategory;

@WebServlet("/admin/OrdersList")
public class OrdersList extends HttpServlet {
	private OrdersDao ordersDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
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
		int lastPage = ordersDao.ordersLastPage(rowPerPage);
		
		HashMap<String, Object> group = ordersDao.currentGroup(currentPage, pagePerGroup, lastPage);
		currentPageGroup = Integer.parseInt(group.get("currentPageGroup").toString());
		lastPageGroup = Integer.parseInt(group.get("lastPageGroup").toString());
		
		//List<OrdersAndItemAndCategory> list = ordersDao.selectOrdersListAll();
		ArrayList<OrdersAndItemAndCategory> list = ordersDao.selectOrdersByPage(beginRow, rowPerPage);
		request.setAttribute("list", list);
		request.setAttribute("currentPageGroup", currentPageGroup);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pagePerGroup", pagePerGroup);
		request.setAttribute("lastPageGroup", lastPageGroup);
		request.setAttribute("lastPage", lastPage);
		
		request.getRequestDispatcher("/WEB-INF/jsp/admin/ordersList.jsp").forward(request, response);
	}
}