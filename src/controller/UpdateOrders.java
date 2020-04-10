package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdersDao;
import vo.Orders;
import vo.OrdersAndItemAndCategory;

@WebServlet("/mall/UpdateOrders")
public class UpdateOrders extends HttpServlet {
	private OrdersDao ordersDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		if(request.getParameter("state")==null) {
			int ordersId = Integer.parseInt(request.getParameter("ordersId"));
			this.ordersDao = new OrdersDao();
			OrdersAndItemAndCategory orders = ordersDao.selectOrdersOne(ordersId);
			request.setAttribute("orders", orders);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("/WEB-INF/jsp/mall/updateOrders.jsp").forward(request, response);
			
		} else {
			int ordersId = Integer.parseInt(request.getParameter("ordersId"));
			this.ordersDao = new OrdersDao();
			ordersDao.updateOrdersCancel(ordersId);
			OrdersAndItemAndCategory orders = ordersDao.selectOrdersOne(ordersId);
			//System.out.println(orders.getOrders().getUserPhone());
			response.sendRedirect(request.getContextPath()+"/mall/MyOrdersListPaging?userName="+orders.getOrders().getUserName()+"&userPhone="+orders.getOrders().getUserPhone()+"&currentPage="+currentPage);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int ordersId = Integer.parseInt(request.getParameter("ordersId"));
		int itemCount = Integer.parseInt(request.getParameter("itemCount"));
		int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
		int ordersPrice = itemCount*itemPrice;
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		String userAddress = request.getParameter("userAddress");
		Orders orders = new Orders();
		orders.setOrdersId(ordersId);
		orders.setItemCount(itemCount);
		orders.setOrdersPrice(ordersPrice);
		orders.setUserAddress(userAddress);
		orders.setUserName(userName);
		orders.setUserPhone(userPhone);
		this.ordersDao = new OrdersDao();
		ordersDao.updateOrders(orders);
		response.sendRedirect(request.getContextPath()+"/mall/MyOrdersListPaging?userName="+userName+"&userPhone="+userPhone+"&currentPage="+currentPage);
	}

}
