package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/admin", "/admin/Index"})	// {}안에 넣어서 여러 url 매핑할 수 잇음
public class Index extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// System.out.println(request.getAttribute("RloginId") + " <-- request loginId");
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("SloginId") + " <-- session loginId");
		if(session.getAttribute("SloginId")==null) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/jsp/admin/index.jsp").forward(request, response);
	}
}
