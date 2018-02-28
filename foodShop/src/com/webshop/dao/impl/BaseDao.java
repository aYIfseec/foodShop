package com.webshop.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author 榛勫媷搴�
 * 
 * @date 2017
 * 
 * @鍔熻兘 connect database
 */

public class BaseDao {
	private final static String DRIVER = "com.mysql.jdbc.Driver";

	private final static String URL = "jdbc:mysql://localhost:3306/foodshop?useUnicode=true&characterEncoding=UTF-8";
	private static final String USER = "root";//
	private static final String PASS = "password";//

	static {
		try {
			Class.forName(DRIVER);// 鍔犺浇椹卞姩
		} catch (ClassNotFoundException e) {
			System.out.println("鎵句笉鍒伴┍鍔ㄧ▼搴忕被 锛屽姞杞介┍鍔ㄥけ璐ワ紒");
			e.printStackTrace();
		}
	}

	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);// 杩炴帴鏁版嵁搴�
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 鏇存柊鎿嶄綔(澧炲垹鏀�
	public static boolean operUpdate(String sql, List<Object> p) {
		Connection conn = null;
		PreparedStatement pste = null;
		int res = 0;
		conn = getConn();
		try {
			pste = conn.prepareStatement(sql);
			if (p != null) {
				for (int i = 0; i < p.size(); i++) {
					pste.setObject(i + 1, p.get(i));
				}
			}
			res = pste.executeUpdate();
			// 鎵ц
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseAll(null, pste, conn);// 鍏抽棴杩炴帴锛岄噴鏀捐祫婧�
		}
		return res > 0;//
	}

	// 鏌ヨ鎿嶄綔锛堟煡锛�
	public static <T> List<T> operQuery(String sql, List<Object> p, Class<T> cls)
			throws Exception {
		Connection conn = null;
		PreparedStatement pste = null;// 棰勫鐞嗚鍙�
		ResultSet rs = null;// 缁撴灉闆�
		List<T> list = new ArrayList<T>();
		conn = getConn();
		try {
			pste = conn.prepareStatement(sql);
			if (p != null) {// 灏嗘潯浠跺�瑁呭叆棰勫鐞嗚鍙�
				for (int i = 0; i < p.size(); i++) {
					pste.setObject(i + 1, p.get(i));
				}
			}
			
			rs = pste.executeQuery();// 鎵ц
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				T entity = cls.newInstance();// 鍙嶅皠
				for (int j = 0; j < rsmd.getColumnCount(); j++) {
					// 浠庢暟鎹簱涓彇寰楀瓧娈靛悕
					String col_name = rsmd.getColumnName(j + 1);
					Object value = rs.getObject(col_name);
					Field field = cls.getDeclaredField(col_name);
					field.setAccessible(true);// 绫讳腑鐨勬垚鍛樺彉閲忎负private,鏁呭繀椤昏繘琛屾鎿嶄綔
					field.set(entity, value);// 缁欏疄浣撶被entity鐨刦ield灞炴�璧嬪�
				}
				list.add(entity);// 鍔犲叆list鍒楄〃
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseAll(rs, pste, conn);// 鍏抽棴杩炴帴锛岄噴鏀捐祫婧�
		}
		return list;
	}

	// 澶勭悊鏈夊涓粨鏋滈泦鐨勬煡璇�
	public static <T> List<T> operQueryHaveManyResultSet(String sql,
			List<Object> p, Class<T> cls) throws Exception {
		Connection conn = null;
		PreparedStatement pste = null;// 棰勫鐞嗚鍙�
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		conn = getConn();
		boolean label = true;
		try {
			pste = conn.prepareStatement(sql);
			if (p != null) {// 灏嗘潯浠跺�瑁呭叆棰勫鐞嗚鍙�
				for (int i = 0; i < p.size(); i++) {
					pste.setObject(i + 1, p.get(i));
				}
			}
			rs = pste.executeQuery();// 鎵ц
			// System.out.println("#######" + rs.next());
			while (label) {
				int i = 0;
				i = pste.getUpdateCount();
				boolean up_label = false;
				if (i != -1) {
					up_label = pste.getMoreResults();
					continue;
				}
				boolean flag_label = false;
				rs = pste.getResultSet();
				if (rs != null) {
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();
					while (rs.next()) {
						T entity = cls.newInstance();// 鍙嶅皠
						for (int j = 0; j < columnCount; j++) {
							// 浠庢暟鎹簱涓彇寰楀瓧娈靛悕
							String col_name = rsmd.getColumnName(j + 1);
							Object value = rs.getObject(col_name);
							Field field = cls.getDeclaredField(col_name);
							field.setAccessible(true);// 绫讳腑鐨勬垚鍛樺彉閲忎负private,鏁呭繀椤昏繘琛屾鎿嶄綔
							field.set(entity, value);// 缁欏疄浣撶被entity鐨刦ield灞炴�璧嬪�
						}
						list.add(entity);// 鍔犲叆list鍒楄〃
					}
					flag_label = pste.getMoreResults();
					continue;
				}
				label = up_label || flag_label;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseAll(rs, pste, conn);// 鍏抽棴杩炴帴锛岄噴鏀捐祫婧�
		}
		return list;
	}

	// 鍏抽棴杩炴帴锛岄噴鏀捐祫婧�
	private static void releaseAll(ResultSet res, PreparedStatement pste,
			Connection conn) {
		try {
			if (res != null)
				res.close();
			if (pste != null)
				pste.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}