package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryDao;
import vo.Category;


@WebServlet("/admin/UpdateCategory")
public class UpdateCategory extends HttpServlet {
	public CategoryDao categoryDao;
	// 수정 폼(C-M-V)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		System.out.println(categoryId + " <--UpdateCategory.doGet() categoryId");
		
		this.categoryDao = new CategoryDao();
		Category category = this.categoryDao.selectCategoryOne(categoryId);
		
		request.setAttribute("category", category);
		// view forward
		request.getRequestDispatcher("/WEB-INF/jsp/admin/updateCategory.jsp").forward(request, response);
	}
	
	// 수정액션(C-M) -> redirect
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		System.out.println(categoryId + " <--UpdateCategory.doPost() categoryId");
		String categoryName = request.getParameter("categoryName");
		System.out.println(categoryName + " <--UpdateCategory.doPost() categoryName");
		Category category = new Category();
		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName);
		
		this.categoryDao = new CategoryDao();
		this.categoryDao.updateCategory(category);
		
		response.sendRedirect(request.getContextPath()+"/admin/CategoryList");
	}
}
