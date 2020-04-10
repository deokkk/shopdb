package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDao;
import vo.Member;

@WebServlet("/admin/InsertMember")
public class InsertMember extends HttpServlet {
	private MemberDao memberDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String correct = request.getParameter("correct");
		request.setAttribute("correct", correct);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/insertMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberPw2 = request.getParameter("memberPw2");
		if(memberPw.equals(memberPw2)) {
			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
			this.memberDao = new MemberDao();
			memberDao.insertMember(member);
			response.sendRedirect(request.getContextPath()+"/mall/MallIndex");
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/InsertMember?correct=notCorrect");
		}
	}

}
