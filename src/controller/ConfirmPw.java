package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDao;
import vo.Member;

@WebServlet("/admin/ConfirmPw")
public class ConfirmPw extends HttpServlet {
	private MemberDao memberDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println(request.getParameter("correct") + "~~~~~~~~~~~~~~~");
		String memberId = request.getParameter("memberId");
		String state = request.getParameter("state");
		String correct = request.getParameter("correct");
		this.memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(memberId);
		request.setAttribute("member", member);
		request.setAttribute("state", state);
		request.setAttribute("correct", correct);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/confirmPw.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
