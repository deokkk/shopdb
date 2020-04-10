package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryDao;
import vo.Category;

@WebServlet("/admin/CategoryList")
// /admin/categoryList?currentPage=2
public class CategoryList extends HttpServlet{
	@Override	// C-M-V
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CategoryDao categoryDao = new CategoryDao();
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
		int lastPage = categoryDao.categoryLastPage(rowPerPage);
		
		HashMap<String, Object> group = categoryDao.currentGroup(currentPage, pagePerGroup, lastPage);
		currentPageGroup = Integer.parseInt(group.get("currentPageGroup").toString());
		lastPageGroup = Integer.parseInt(group.get("lastPageGroup").toString());

		
		// controller 역할
		//  1) request 분석
		System.out.println(request.getRemoteAddr());
		//  2) model 호출
		
		//ArrayList<Category> list = categoryDao.selectCategoryListAll();
		ArrayList<Category> list = categoryDao.selectCategoryByPage(beginRow, rowPerPage);
		System.out.println(list.size() + " <--list.size()");
		request.setAttribute("list", list); // Object형으로 담김
		
		request.setAttribute("currentPageGroup", currentPageGroup);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pagePerGroup", pagePerGroup);
		request.setAttribute("lastPageGroup", lastPageGroup);
		request.setAttribute("lastPage", lastPage);
		
		System.out.println(lastPage);
		
		
		//  3) view 연결 
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/categoryList.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("!!!!!!!!!!!!!!!!!!");
	}
}
