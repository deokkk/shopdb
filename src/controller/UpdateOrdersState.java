package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdersDao;
import vo.Category;
import vo.Item;
import vo.Orders;
import vo.OrdersAndItemAndCategory;

@WebServlet("/admin/UpdateOrdersState")
public class UpdateOrdersState extends HttpServlet {
	private OrdersDao ordersDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String[] arr = {"주문완료", "배송중", "배송완료"};
		int ordersId = Integer.parseInt(request.getParameter("ordersId"));
		this.ordersDao = new OrdersDao();
		OrdersAndItemAndCategory oic = ordersDao.selectOrdersOne(ordersId);
		request.setAttribute("orders", oic);
		request.setAttribute("state", arr);
		System.out.println(arr.length);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/updateOrders.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int ordersId = Integer.parseInt(request.getParameter("ordersId"));
		System.out.println(ordersId + " <--UpdateOrders.doPost() ordersId");
		String ordersState = request.getParameter("ordersState");
		System.out.println(ordersState + " <--UpdateOrders.doPost() ordersState");
		
		Orders orders = new Orders();
		orders.setOrdersId(ordersId);
		orders.setOrdersState(ordersState);
		this.ordersDao = new OrdersDao();
		this.ordersDao.updateOrdersState(orders);
		
		response.sendRedirect(request.getContextPath() + "/admin/OrdersList");
	}

}
