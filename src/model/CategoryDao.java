package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import commons.DBUtil;
import vo.Category;

public class CategoryDao {
	public HashMap<String, Object> currentGroup(int currentPage, int pagePerGroup, int lastPage) {
		System.out.println("CategoryDao.currentGroup()");
		int currentPageGroup = 0;
		int lastPageGroup = 0;
		
		currentPageGroup = (currentPage-1)/pagePerGroup*pagePerGroup+1;
		lastPageGroup = (lastPage-1)/pagePerGroup*pagePerGroup+1;
		System.out.println(currentPageGroup + " <- currentPageGroup");
		System.out.println(lastPageGroup + " <- lastPageGroup");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("currentPageGroup", currentPageGroup);
		map.put("lastPageGroup", lastPageGroup);
		
		return map;
	}
	
	public int categoryLastPage(int rowPerPage) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int lastPage = 1;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) cnt FROM category ";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int totalRow = 0;
			if(rs.next()) {
				totalRow = rs.getInt("cnt");
			}
			if(totalRow/rowPerPage!=0) {
				lastPage = totalRow/rowPerPage;
				if(totalRow%rowPerPage != 0) {
					lastPage+=1;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lastPage;
	}
	
	public void updateCategory(Category category) {
		System.out.println("CategoryDao.updateCategory()");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE category SET category_name=? WHERE category_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, category.getCategoryName());
			stmt.setInt(2, category.getCategoryId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();	// 콘솔창에 예외 메세지를 다 출력하세요.
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Category selectCategoryOne(String categoryName) {
		System.out.println("CategoryDao.selectCategoryOne()");
		Category category = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT category_id, category_name FROM category WHERE category_name=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, categoryName);
			rs = stmt.executeQuery();
			category = new Category();
			if(rs.next()) {
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
			}
		} catch (Exception e) {
			System.out.println("CategoryDao.selectCategoryOne() 예외발생");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return category;
	}
	
	public Category selectCategoryOne(int categoryId) {
		System.out.println("CategoryDao.selectCategoryOne()");
		Category category = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT category_id, category_name FROM category WHERE category_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			rs = stmt.executeQuery();
			category = new Category();
			if(rs.next()) {
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
			}
		} catch (Exception e) {
			System.out.println("CategoryDao.selectCategoryOne() 예외발생");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return category;
	}
	
	public void insertCategory(Category category) {
		System.out.println("CategoryDao.insertCategory()");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO category(category_name) VALUES(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, category.getCategoryName());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();	// 콘솔창에 예외 메세지를 다 출력하세요.
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Category> selectCategoryListAll() {
		System.out.println("CategoryDao.selectCategoryAll()");
		ArrayList<Category> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT category_id, category_name FROM category";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			list = new ArrayList<Category>();
			while(rs.next()) {
				Category category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				list.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<Category> selectCategoryByPage(int beginRow, int rowPerPage) {
		System.out.println("CategoryDao.selectCategoryAll()");
		ArrayList<Category> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT category_id, category_name FROM category LIMIT ?,?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			list = new ArrayList<Category>();
			while(rs.next()) {
				Category category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				list.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
