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

/**
 * Servlet implementation class DeleteMember
 */
@WebServlet("/admin/DeleteMember")
public class DeleteMember extends HttpServlet {
	private MemberDao memberDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String state = request.getParameter("state");
		this.memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(memberId);
		System.out.println(memberPw+"?"+member.getMemberPw());
		if(member.getMemberPw().equals(memberPw)) {
			Member confirmMember = new Member();
			confirmMember.setMemberId(memberId);
			confirmMember.setMemberPw(memberPw);
			memberDao.deleteMember(confirmMember);
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/admin");
		} else {
			System.out.println(memberId);
			response.sendRedirect(request.getContextPath()+"/admin/ConfirmPw?memberId="+memberId+"&state="+state+"&correct=notCorrect");
		}
	}

}
