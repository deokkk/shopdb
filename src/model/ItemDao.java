package model;

import java.sql.*;
import java.util.*;

import commons.DBUtil;
import vo.*;;

public class ItemDao {
	public ArrayList<ItemAndCategory> selectItemByPage(int beginRow, int rowPerPage) {
		System.out.println("ItemDao.selectItemAll()");
		ArrayList<ItemAndCategory> list = new ArrayList<ItemAndCategory>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT i.item_id, i.category_id, i.item_name, i.item_price, i.item_contents, i.item_img, c.category_id, c.category_name FROM item i INNER JOIN category c ON i.category_id=c.category_id LIMIT ?,?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				ItemAndCategory iac = new ItemAndCategory();
				Item item = new Item();
				item.setItemId(rs.getInt("i.item_id"));
				item.setCategoryId(rs.getInt("i.category_id"));
				item.setItemName(rs.getString("i.item_name"));
				item.setItemPrice(rs.getInt("i.item_price"));
				item.setItemContents(rs.getString("i.item_contents"));
				System.out.println(item.getItemContents() + " <--contents");
				item.setItemImg(rs.getString("i.item_img"));
				System.out.println(item.getItemImg() + " <--img");
				iac.setItem(item);
				
				Category category = new Category();
				category.setCategoryId(rs.getInt("c.category_id"));
				category.setCategoryName(rs.getString("c.category_name"));
				//System.out.println(category.getCategoryName());
				iac.setCategory(category);
				
				list.add(iac);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
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
	
	public int itemLastPage(int rowPerPage) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int lastPage = 1;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) cnt FROM item";
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
	
	public List<ItemAndCategory> selectItemListAll() {
		System.out.println("ItemDao.selectItemListAll()");
		List<ItemAndCategory> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT i.item_id, i.category_id, i.item_name, i.item_price, i.item_contents, i.item_img, c.category_id, c.category_name FROM item i INNER JOIN category c ON i.category_id=c.category_id";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			list = new ArrayList<ItemAndCategory>();
			while(rs.next()) {
				ItemAndCategory iac = new ItemAndCategory();
				Item item = new Item();
				item.setItemId(rs.getInt("i.item_id"));
				item.setCategoryId(rs.getInt("i.category_id"));
				item.setItemName(rs.getString("i.item_name"));
				item.setItemPrice(rs.getInt("i.item_price"));
				item.setItemContents(rs.getString("i.item_contents"));
				System.out.println(item.getItemContents() + " <--contents");
				item.setItemImg(rs.getString("i.item_img"));
				System.out.println(item.getItemImg() + " <--img");
				iac.setItem(item);
				
				Category category = new Category();
				category.setCategoryId(rs.getInt("c.category_id"));
				category.setCategoryName(rs.getString("c.category_name"));
				//System.out.println(category.getCategoryName());
				iac.setCategory(category);
				
				list.add(iac);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public Item selectItemOne(String itemName) {
		Item item = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT i.item_id, i.category_id, i.item_name, i.item_price, i.item_contents, c.category_id, c.category_name FROM item i INNER JOIN category c ON i.category_id=c.category_id WHERE i.item_name=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, itemName);
			rs = stmt.executeQuery();
			item = new Item();
			if(rs.next()) {
				item.setItemId(rs.getInt("item_id"));
				item.setCategoryId(rs.getInt("category_id"));
				item.setItemName(rs.getString("item_name"));
				item.setItemPrice(rs.getInt("item_price"));
				item.setItemContents(rs.getString("item_contents"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}
	
	public Item selectItemOne(int itemId) {
		Item item = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT i.item_id, i.category_id, i.item_name, i.item_price, i.item_contents, c.category_id, c.category_name FROM item i INNER JOIN category c ON i.category_id=c.category_id WHERE i.item_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, itemId);
			rs = stmt.executeQuery();
			item = new Item();
			if(rs.next()) {
				item.setItemId(rs.getInt("item_id"));
				item.setCategoryId(rs.getInt("category_id"));
				item.setItemName(rs.getString("item_name"));
				item.setItemPrice(rs.getInt("item_price"));
				item.setItemContents(rs.getString("item_contents"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}
	
	public ItemAndCategory selectItemAndCategoryOne(int itemId) {
		ItemAndCategory ic = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT i.item_id, i.category_id, i.item_name, i.item_price, i.item_contents, i.item_img, c.category_id, c.category_name FROM item i INNER JOIN category c ON i.category_id=c.category_id WHERE i.item_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, itemId);
			rs = stmt.executeQuery();
			ic = new ItemAndCategory();
			if(rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getInt("i.item_id"));
				item.setCategoryId(rs.getInt("i.category_id"));
				item.setItemName(rs.getString("i.item_name"));
				item.setItemPrice(rs.getInt("i.item_price"));
				item.setItemContents(rs.getString("i.item_contents"));
				item.setItemImg(rs.getString("i.item_img"));
				ic.setItem(item);
				
				Category category = new Category();
				category.setCategoryId(rs.getInt("c.category_id"));
				category.setCategoryName(rs.getString("c.category_name"));
				ic.setCategory(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ic;
	}
	
	public void insertItem(Item item) {
		Connection conn = null;
		PreparedStatement stmt = null;
		System.out.println("ItemDao.insertItem()");
		System.out.println(item.getCategoryId() + " <--categoryId");
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO item(category_id, item_name, item_price, item_contents) VALUES(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, item.getCategoryId());
			stmt.setString(2, item.getItemName());
			stmt.setInt(3, item.getItemPrice());
			stmt.setString(4, item.getItemContents());
			System.out.println(stmt + " <--stmt");
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateItem(Item item) {
		Connection conn = null;
		PreparedStatement stmt = null;
		System.out.println("ItemDao.updateItem()");
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE item SET category_id=?, item_name=?, item_price=?, item_contents=? WHERE item_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, item.getCategoryId());
			stmt.setString(2, item.getItemName());
			stmt.setInt(3, item.getItemPrice());
			stmt.setString(4, item.getItemContents());
			stmt.setInt(5, item.getItemId());
			System.out.println(stmt + " <--stmt");
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
