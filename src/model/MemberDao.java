package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import commons.DBUtil;
import vo.Member;

public class MemberDao {
	public Member login(Member member) {
		Member returnMember = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT member_id, member_pw FROM member WHERE member_id=? AND member_pw=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("member_id"));
				returnMember.setMemberPw(rs.getString("member_pw"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, stmt, conn);	// select
			// DBUtil.close(null, stmt, conn);	// insert, update, delete
		}
		return returnMember;
	}
	
	public ArrayList<Member> selectMemberListAll() {
		ArrayList<Member> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn=DBUtil.getConnection();
			String sql = "SELECT member_id, member_pw FROM member";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			list = new ArrayList<Member>();
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
				list.add(member);
			}
			//System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return list;
	}
	
	public void deleteMember(Member member) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn=DBUtil.getConnection();
			String sql = "DELETE FROM member WHERE member_id=? AND member_pw=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, stmt, conn);
		}
	}
	
	public Member selectMemberOne(String memberId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			conn=DBUtil.getConnection();
			String sql = "SELECT member_id, member_pw FROM member WHERE member_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			member = new Member();
			if(rs.next()) {
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return member;
	}
	
	public void updateMember(Member member) {
		System.out.println("MemberDao.updateMember()");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn=DBUtil.getConnection();
			String sql = "UPDATE member SET member_pw=? WHERE member_id=?";
			stmt = conn.prepareStatement(sql);
			System.out.println(stmt + " <--stmt");
			stmt.setString(1, member.getMemberPw());
			stmt.setString(2, member.getMemberId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, stmt, conn);
		}
	}
	
	public void insertMember(Member member) {
		System.out.println("MemberDao.updateMember()");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn=DBUtil.getConnection();
			String sql = "INSERT INTO member(member_id, member_pw) VALUES(?,?)";
			stmt = conn.prepareStatement(sql);
			System.out.println(stmt + " <--stmt");
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, stmt, conn);
		}
	}
}
