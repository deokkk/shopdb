package controller;

import java.io.IOException;
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

@WebServlet("/admin/InsertItem")
public class InsertItem extends HttpServlet {
	private ItemDao itemDao;
	private CategoryDao categoryDao;
	// 입력폼(C-M-V)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		this.categoryDao = new CategoryDao();
		List<Category> list = this.categoryDao.selectCategoryListAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/insertItem.jsp").forward(request, response);
	}
	// 입력액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("InsertItem.doPost()");
		request.setCharacterEncoding("utf-8");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		System.out.println(categoryId);
		String itemName = request.getParameter("itemName");
		System.out.println(itemName);
		int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
		System.out.println(itemPrice);
		String itemContents = request.getParameter("itemContents");
		System.out.println(itemContents);
		Item item = new Item();
		item.setCategoryId(categoryId);
		item.setItemName(itemName);
		item.setItemPrice(itemPrice);
		item.setItemContents(itemContents);
		
		this.itemDao = new ItemDao();
		itemDao.insertItem(item);
		
		response.sendRedirect(request.getContextPath() + "/admin/ItemList");
	}

}
