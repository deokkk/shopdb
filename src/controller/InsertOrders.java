package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ItemDao;
import model.OrdersDao;
import vo.Item;
import vo.ItemAndCategory;
import vo.Orders;
import vo.OrdersAndItemAndCategory;

@WebServlet("/mall/InsertOrders")
public class InsertOrders extends HttpServlet {
	private ItemDao itemDao;
	private OrdersDao ordersDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		this.itemDao = new ItemDao();
		ItemAndCategory item = itemDao.selectItemAndCategoryOne(itemId);
		
		request.setAttribute("item", item);
		request.getRequestDispatcher("/WEB-INF/jsp/mall/insertOrders.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
		int itemCount = Integer.parseInt(request.getParameter("itemCount"));
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		String userAddress = request.getParameter("userAddress");
		int ordersPrice = itemCount*itemPrice;
		
		Orders orders = new Orders();
		orders.setItemCount(itemCount);
		orders.setItemId(itemId);
		orders.setOrdersPrice(itemPrice);
		orders.setUserAddress(userAddress);
		orders.setUserName(userName);
		orders.setUserPhone(userPhone);
		
		this.ordersDao = new OrdersDao();
		int ordersId = ordersDao.insertOrders(orders);
		OrdersAndItemAndCategory oic = ordersDao.selectOrdersOne(ordersId);
		request.setAttribute("orders", oic);
		
		request.getRequestDispatcher("/WEB-INF/jsp/mall/ordersDetail.jsp").forward(request, response);
	}

}
