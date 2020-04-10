package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDao;
import vo.Member;

@WebServlet("/admin/UpdateMember")
public class UpdateMember extends HttpServlet {
	private MemberDao memberDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw);
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		//System.out.println(member.getMemberPw() + "!!!!!!!!!!!!!!!!!!!!!");
		this.memberDao = new MemberDao();
		memberDao.updateMember(member);
		response.sendRedirect(request.getContextPath()+"/admin/MemberList?memberId="+memberId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		System.out.println(memberId + " <-- confirmPw memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw + " <-- confirmPw memberpW");
		String state = request.getParameter("state");
		this.memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(memberId);
		System.out.println(memberPw+"?"+member.getMemberPw());
		if(member.getMemberPw().equals(memberPw)) {
			request.setAttribute("member", member);
			request.getRequestDispatcher("/WEB-INF/jsp/admin/updateMember.jsp").forward(request, response);
		} else {
			memberId = URLEncoder.encode(memberId, "UTF-8");
			response.sendRedirect(request.getContextPath()+"/admin/ConfirmPw?memberId="+memberId+"&state="+state+"&correct=notCorrect");
		}
	}

}
