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

import model.ItemDao;
import vo.Category;
import vo.Item;
import vo.ItemAndCategory;

@WebServlet("/mall/MallIndex")
public class MallIndex extends HttpServlet {
	private ItemDao itemDao;	// 1. 추상화	2. 상속	3.다형성	4.캡슐화
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		this.itemDao = new ItemDao();
		
		// 페이징
		int pagePerGroup = 5;
		int currentPageGroup = 0;
		int lastPageGroup = 0;
		int rowPerPage = 6;
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println(currentPage + " <--currentPage");
		int beginRow = (currentPage-1)*rowPerPage;
		int lastPage = itemDao.itemLastPage(rowPerPage);
		
		HashMap<String, Object> group = itemDao.currentGroup(currentPage, pagePerGroup, lastPage);
		currentPageGroup = Integer.parseInt(group.get("currentPageGroup").toString());
		lastPageGroup = Integer.parseInt(group.get("lastPageGroup").toString());
		
		//List<ItemAndCategory> list = itemDao.selectItemListAll();
		ArrayList<ItemAndCategory> list = itemDao.selectItemByPage(beginRow, rowPerPage);
		request.setAttribute("list", list);
		request.setAttribute("currentPageGroup", currentPageGroup);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pagePerGroup", pagePerGroup);
		request.setAttribute("lastPageGroup", lastPageGroup);
		request.setAttribute("lastPage", lastPage);
		
		request.getRequestDispatcher("/WEB-INF/jsp/mall/mallIndex.jsp").forward(request, response);
	}

}
