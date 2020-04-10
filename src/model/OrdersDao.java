package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import vo.*;

public class OrdersDao {
	String driver = "org.mariadb.jdbc.Driver";
	String dbaddr = "jdbc:mariadb://localhost:3306/shopdb";
	String dbid = "root";
	String dbpw = "java1234";
	
	public HashMap<String, Object> currentGroup(int currentPage, int pagePerGroup, int lastPage) {
		System.out.println("OrdersDao.currentGroup()");
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
	
	public int searchOrdersLastPage(int rowPerPage, String userName, String userPhone) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int lastPage = 1;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
			String sql = "SELECT COUNT(*) cnt FROM orders WHERE user_name=? AND user_phone=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, userPhone);
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
	
	public int ordersLastPage(int rowPerPage) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int lastPage = 1;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
			String sql = "SELECT COUNT(*) cnt FROM orders";
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
	
	public ArrayList<OrdersAndItemAndCategory> selectSearchOrders(Orders myOrders) {
		ArrayList<OrdersAndItemAndCategory> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
			String sql = "SELECT o.orders_id, o.item_id, o.item_count, o.orders_date, o.orders_price, o.orders_state, o.user_name, o.user_phone, o.user_address, i.item_id, i.category_id, i.item_name, i.item_price, i.item_contents, i.item_img, c.category_id, c.category_name FROM orders o INNER JOIN item i ON o.item_id=i.item_id INNER JOIN category c ON i.category_id=c.category_id WHERE o.user_name=? AND o.user_phone=? ORDER BY o.orders_id";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, myOrders.getUserName());
			stmt.setString(2, myOrders.getUserPhone());
			rs = stmt.executeQuery();
			list = new ArrayList<OrdersAndItemAndCategory>();
			while(rs.next()) {
				OrdersAndItemAndCategory oic = new OrdersAndItemAndCategory();
				Orders orders = new Orders();
				orders.setOrdersId(rs.getInt("o.orders_id"));
				orders.setItemId(rs.getInt("o.item_id"));
				orders.setItemCount(rs.getInt("o.item_count"));
				orders.setOrdersDate(rs.getString("o.orders_date"));
				orders.setOrdersPrice(rs.getInt("o.orders_price"));
				orders.setOrdersState(rs.getString("o.orders_state"));
				orders.setUserName(rs.getString("o.user_name"));
				orders.setUserPhone(rs.getString("o.user_phone"));
				orders.setUserAddress(rs.getString("o.user_address"));
				oic.setOrders(orders);
				
				Item item = new Item();
				item.setItemId(rs.getInt("i.item_id"));
				item.setCategoryId(rs.getInt("i.category_id"));
				item.setItemName(rs.getString("i.item_name"));
				item.setItemPrice(rs.getInt("i.item_price"));
				item.setItemContents(rs.getString("i.item_contents"));
				item.setItemImg(rs.getString("i.item_img"));
				oic.setItem(item);
				
				Category category = new Category();
				category.setCategoryId(rs.getInt("c.category_id"));
				category.setCategoryName(rs.getString("c.category_name"));
				oic.setCategory(category);
				
				list.add(oic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int insertOrders(Orders orders) {
		int ordersId = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
			String sql = "INSERT INTO orders(item_id, item_count, orders_date, orders_price, orders_state, user_name, user_phone, user_address) VALUES(?,?,now(),?,'주문완료',?,?,?)";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, orders.getItemId());
			stmt.setInt(2, orders.getItemCount());
			stmt.setInt(3, orders.getOrdersPrice()*orders.getItemCount());
			stmt.setString(4, orders.getUserName());
			stmt.setString(5, orders.getUserPhone());
			stmt.setString(6, orders.getUserAddress());
			System.out.println(stmt + " <-- OrdersDao.insertOrders() stmt");
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				ordersId = rs.getInt(1);
			}
			System.out.println(ordersId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ordersId;
	}
	
	public ArrayList<OrdersAndItemAndCategory> selectSearchOrdersByPage(int beginRow, int rowPerPage, Orders myOrders) {
		System.out.println("OrdersDao.selectOrdersAll()");
		ArrayList<OrdersAndItemAndCategory> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
			String sql = "SELECT o.orders_id, o.item_id, o.item_count, o.orders_date, o.orders_price, o.orders_state, o.user_name, o.user_phone, o.user_address, i.item_id, i.category_id, i.item_name, i.item_price, i.item_contents, i.item_img, c.category_id, c.category_name FROM orders o INNER JOIN item i ON o.item_id=i.item_id INNER JOIN category c ON i.category_id=c.category_id WHERE o.user_name=? AND o.user_phone=? ORDER BY o.orders_id LIMIT ?,?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, myOrders.getUserName());
			stmt.setString(2, myOrders.getUserPhone());
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
			System.out.println(stmt + " <--ordersDao.selectSearchOrdersByPage() stmt");
			rs = stmt.executeQuery();
			list = new ArrayList<OrdersAndItemAndCategory>();
			while(rs.next()) {
				OrdersAndItemAndCategory oic = new OrdersAndItemAndCategory();
				Orders orders = new Orders();
				orders.setOrdersId(rs.getInt("o.orders_id"));
				orders.setItemId(rs.getInt("o.item_id"));
				orders.setItemCount(rs.getInt("o.item_count"));
				orders.setOrdersDate(rs.getString("o.orders_date"));
				orders.setOrdersPrice(rs.getInt("o.orders_price"));
				orders.setOrdersState(rs.getString("o.orders_state"));
				orders.setUserName(rs.getString("o.user_name"));
				orders.setUserPhone(rs.getString("o.user_phone"));
				orders.setUserAddress(rs.getString("o.user_address"));
				oic.setOrders(orders);
				
				Item item = new Item();
				item.setItemId(rs.getInt("i.item_id"));
				item.setCategoryId(rs.getInt("i.category_id"));
				item.setItemName(rs.getString("i.item_name"));
				item.setItemPrice(rs.getInt("i.item_price"));
				item.setItemContents(rs.getString("i.item_contents"));
				oic.setItem(item);
				
				Category category = new Category();
				category.setCategoryId(rs.getInt("c.category_id"));
				category.setCategoryName(rs.getString("c.category_name"));
				oic.setCategory(category);
				
				list.add(oic);
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
	
	public ArrayList<OrdersAndItemAndCategory> selectOrdersByPage(int beginRow, int rowPerPage) {
		System.out.println("OrdersDao.selectOrdersAll()");
		ArrayList<OrdersAndItemAndCategory> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
			String sql = "SELECT o.orders_id, o.item_id, o.item_count, o.orders_date, o.orders_price, o.orders_state, o.user_name, o.user_phone, o.user_address, i.item_id, i.category_id, i.item_name, i.item_price, i.item_contents, c.category_id, c.category_name FROM orders o INNER JOIN item i ON o.item_id=i.item_id INNER JOIN category c ON i.category_id=c.category_id  ORDER BY o.orders_id LIMIT ?,?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			list = new ArrayList<OrdersAndItemAndCategory>();
			while(rs.next()) {
				OrdersAndItemAndCategory oic = new OrdersAndItemAndCategory();
				Orders orders = new Orders();
				orders.setOrdersId(rs.getInt("o.orders_id"));
				orders.setItemId(rs.getInt("o.item_id"));
				orders.setItemCount(rs.getInt("o.item_count"));
				orders.setOrdersDate(rs.getString("o.orders_date"));
				orders.setOrdersPrice(rs.getInt("o.orders_price"));
				orders.setOrdersState(rs.getString("o.orders_state"));
				orders.setUserName(rs.getString("o.user_name"));
				orders.setUserPhone(rs.getString("o.user_phone"));
				orders.setUserAddress(rs.getString("o.user_address"));
				oic.setOrders(orders);
				
				Item item = new Item();
				item.setItemId(rs.getInt("i.item_id"));
				item.setCategoryId(rs.getInt("i.category_id"));
				item.setItemName(rs.getString("i.item_name"));
				item.setItemPrice(rs.getInt("i.item_price"));
				item.setItemContents(rs.getString("i.item_contents"));
				oic.setItem(item);
				
				Category category = new Category();
				category.setCategoryId(rs.getInt("c.category_id"));
				category.setCategoryName(rs.getString("c.category_name"));
				oic.setCategory(category);
				
				list.add(oic);
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
	
	public List<OrdersAndItemAndCategory> selectOrdersListAll() {
		List<OrdersAndItemAndCategory> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
			String sql = "SELECT o.orders_id, o.item_id, o.item_count, o.orders_date, o.orders_price, o.orders_state, o.user_name, o.user_phone, o.user_address, i.item_id, i.category_id, i.item_name, i.item_price, i.item_contents, c.category_id, c.category_name FROM orders o INNER JOIN item i ON o.item_id=i.item_id INNER JOIN category c ON i.category_id=c.category_id  ORDER BY o.orders_id";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			list = new ArrayList<OrdersAndItemAndCategory>();
			while(rs.next()) {
				OrdersAndItemAndCategory oic = new OrdersAndItemAndCategory();
				Orders orders = new Orders();
				orders.setOrdersId(rs.getInt("o.orders_id"));
				orders.setItemId(rs.getInt("o.item_id"));
				orders.setItemCount(rs.getInt("o.item_count"));
				orders.setOrdersDate(rs.getString("o.orders_date"));
				orders.setOrdersPrice(rs.getInt("o.orders_price"));
				orders.setOrdersState(rs.getString("o.orders_state"));
				orders.setUserName(rs.getString("o.user_name"));
				orders.setUserPhone(rs.getString("o.user_phone"));
				orders.setUserAddress(rs.getString("o.user_address"));
				oic.setOrders(orders);
				
				Item item = new Item();
				item.setItemId(rs.getInt("i.item_id"));
				item.setCategoryId(rs.getInt("i.category_id"));
				item.setItemName(rs.getString("i.item_name"));
				item.setItemPrice(rs.getInt("i.item_price"));
				item.setItemContents(rs.getString("i.item_contents"));
				oic.setItem(item);
				
				Category category = new Category();
				category.setCategoryId(rs.getInt("c.category_id"));
				category.setCategoryName(rs.getString("c.category_name"));
				oic.setCategory(category);
				
				list.add(oic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public OrdersAndItemAndCategory selectOrdersOne(int ordersId) {
		OrdersAndItemAndCategory oic = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
			String sql = "SELECT o.orders_id, o.item_id, o.item_count, o.orders_date, o.orders_price, o.orders_state, o.user_name, o.user_phone, o.user_address, i.item_id, i.category_id, i.item_name, i.item_price, i.item_contents, c.category_id, c.category_name FROM orders o INNER JOIN item i ON o.item_id=i.item_id INNER JOIN category c ON i.category_id=c.category_id WHERE o.orders_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ordersId);
			rs = stmt.executeQuery();
			oic = new OrdersAndItemAndCategory();
			while(rs.next()) {
				Orders orders = new Orders();
				orders.setOrdersId(rs.getInt("o.orders_id"));
				orders.setItemId(rs.getInt("o.item_id"));
				orders.setItemCount(rs.getInt("o.item_count"));
				orders.setOrdersDate(rs.getString("o.orders_date"));
				orders.setOrdersPrice(rs.getInt("o.orders_price"));
				orders.setOrdersState(rs.getString("o.orders_state"));
				orders.setUserName(rs.getString("o.user_name"));
				orders.setUserPhone(rs.getString("o.user_phone"));
				orders.setUserAddress(rs.getString("o.user_address"));
				oic.setOrders(orders);
				
				Item item = new Item();
				item.setItemId(rs.getInt("i.item_id"));
				item.setCategoryId(rs.getInt("i.category_id"));
				item.setItemName(rs.getString("i.item_name"));
				item.setItemPrice(rs.getInt("i.item_price"));
				item.setItemContents(rs.getString("i.item_contents"));
				oic.setItem(item);
				
				Category category = new Category();
				category.setCategoryId(rs.getInt("c.category_id"));
				category.setCategoryName(rs.getString("c.category_name"));
				oic.setCategory(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return oic;
	}
	
	// admin update(orders_state)
	public void updateOrdersState(Orders orders) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
			String sql = "UPDATE orders SET orders_state=? WHERE orders_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, orders.getOrdersState());
			stmt.setInt(2, orders.getOrdersId());
			System.out.println(stmt + " <-- OrdersDao.updateOrdersState() stmt");
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// guest update
	public void updateOrders(Orders orders) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
			String sql = "UPDATE orders SET item_count=?, user_name=?, user_phone=?, user_address=?, orders_price=? WHERE orders_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orders.getItemCount());
			stmt.setString(2, orders.getUserName());
			stmt.setString(3, orders.getUserPhone());
			stmt.setString(4, orders.getUserAddress());
			stmt.setInt(5, orders.getOrdersPrice());
			stmt.setInt(6, orders.getOrdersId());
			System.out.println(stmt + " <-- OrdersDao.updateOrders() stmt");
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateOrdersCancel(int ordersId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
			String sql = "UPDATE orders SET orders_state='주문취소' WHERE orders_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ordersId);
			System.out.println(stmt + " <-- OrdersDao.updateOrdersCancel() stmt");
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
