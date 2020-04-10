package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryDao;
import model.ItemDao;
import vo.Category;
import vo.Item;
import vo.ItemAndCategory;

@WebServlet("/admin/UpdateItem")
public class UpdateItem extends HttpServlet {
	private CategoryDao categoryDao;
	private ItemDao itemDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		this.itemDao = new ItemDao();
		Item item = this.itemDao.selectItemOne(itemId);
		this.categoryDao = new CategoryDao();
		List<Category> list = this.categoryDao.selectCategoryListAll();
		request.setAttribute("item", item);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/updateItem.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String itemName = request.getParameter("itemName");
		int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
		String itemContents = request.getParameter("itemContents");
		Item item = new Item();
		item.setItemId(itemId);
		item.setCategoryId(categoryId);
		item.setItemName(itemName);
		item.setItemPrice(itemPrice);
		item.setItemContents(itemContents);
		
		this.itemDao = new ItemDao();
		this.itemDao.updateItem(item);
		
		response.sendRedirect(request.getContextPath() + "/admin/ItemList");
	}

}
