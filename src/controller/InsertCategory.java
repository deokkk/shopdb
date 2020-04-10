package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryDao;
import vo.Category;

@WebServlet("/admin/InsertCategory")
public class InsertCategory extends HttpServlet{
	// InsertCategory 요청이 get방식 -> 입력폼
	@Override	// C-V
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.getRequestDispatcher("/WEB-INF/jsp/admin/insertCategory.jsp").forward(request, response);
	}
	
	// InsertCategory 요청이 post방식 -> 입력
	@Override	// C-M --> C ("/CategoryList")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청 분석 (주소분석, request분석)
		request.setCharacterEncoding("utf-8");
		String categoryName = request.getParameter("categoryName");
		System.out.println(categoryName + " <-- InsertCategory.doPost() categoryName");
		// 2. 모델 호출
		CategoryDao categoryDao = new CategoryDao();
		Category category = new Category();
		category.setCategoryName(categoryName);
		categoryDao.insertCategory(category);
		// 3. 뷰 연결 or 다른 controller redirect
		response.sendRedirect(request.getContextPath() + "/admin/CategoryList");
	}
}
