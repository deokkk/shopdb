package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDao;
import vo.Member;

@WebServlet("/admin/AdminLogin")
public class AdminLogin extends HttpServlet {
	private MemberDao memberDao;
	// login form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.getRequestDispatcher("/WEB-INF/jsp/admin/adminLogin.jsp").forward(request, response);
	}
	// login action(인증)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String adminId = request.getParameter("adminId");
		String adminPw = request.getParameter("adminPw");
		Member member = new Member();
		member.setMemberId(adminId);
		member.setMemberPw(adminPw);
		
		System.out.println(adminId + " <--adminId, " + adminPw + " <--adminPw");
		
		this.memberDao = new MemberDao();
		Member returnMember = memberDao.login(member);
		
		if(returnMember!=null) {
			System.out.println("로그인 성공");
			// 1. 로그인 했다는 정보를 톰캣(변수)안에 저장
			/*
			 *  1) request : view로 응답하고나면 사라짐
			 *  2) session : 접속자?접속브라우즈마다 생성되는 공간
			 *  3) application : 톰캣이 부팅되면 전체에서 공유하는 공간
			*/
			//request.setAttribute("RloginId", "admin");
			HttpSession session = request.getSession();
			session.setAttribute("SloginId", returnMember.getMemberId());
			// 2. 관리자 index 요청
			response.sendRedirect(request.getContextPath()+"/admin");
		} else {
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
		}
	}

}
