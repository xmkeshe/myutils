package cn.xmkeshe.utils.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author 欣茂科技软件学院（xmkeshe.cn）
 *         <li>此类主要负责数据层实现类代码扩充，减少重复代码</li>
 */
public abstract class AbstractDAOImpl {
	protected Connection conn;
	protected PreparedStatement pstmt;

	public AbstractDAOImpl(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 批量删除整型数据
	 * @param ids
	 * @param tableName
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteBatchIntegerImpl(Set<Integer> ids, String tableName, String id) throws SQLException {
		StringBuffer buf = new StringBuffer();
		buf.append("DELETE FROM " + tableName + " WHERE " + id + "=( ");
		Iterator<?> iter = ids.iterator();
		while (iter.hasNext()) {
			buf.append(iter.hasNext()).append(",");
		}
		buf.delete((buf.length() - 1), buf.length());
		buf.append(")");
		this.pstmt = this.conn.prepareStatement(buf.toString());
		return this.pstmt.executeUpdate() == ids.size();
	}

	/**
	 * 批量删除字符串主键数据
	 * @param ids       表示要删除的字符串集合
	 * @param tableName 表名称
	 * @param id        要删除的主键名
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteBatchStringImpl(Set<String> ids, String tableName, String id) throws SQLException {
		StringBuffer buf = new StringBuffer();
		buf.append("DELETE FROM " + tableName + " WHERE " + id + " =( ");
		Iterator<?> iter = ids.iterator();
		while (iter.hasNext()) {
			buf.append(iter.hasNext()).append(",");
		}
		buf.delete((buf.length() - 1), buf.length());
		buf.append(")");
		this.pstmt = this.conn.prepareStatement(buf.toString());
		return this.pstmt.executeUpdate() == ids.size();
	}

	/**
	 * 统计数据量
	 * @param tabName
	 * @return
	 * @throws SQLException
	 */
	public Integer getAllCount(String tabName) throws SQLException {
		String sql = "SELECT COUNT(*) FROM" + " " + tabName;
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
	/**
	 * 模糊查询统计数据量
	 * @param tabName
	 * @param keyWord
	 * @param column
	 * @return
	 * @throws SQLException
	 */
	public Integer getAllCount(String tabName,String keyWord,String column) throws SQLException {
		String sql = "SELECT COUNT(*) FROM" + " " + tabName +" WHERE  " + column +" LIKE ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, "%"+keyWord+"%");
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	/**
	 * 取得对象中的属性。
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String getFields(String str) throws Exception {
		Class<?> cls = Class.forName(str);
		Field[] field = cls.getDeclaredFields(); // 取得对象中的全部属性
		StringBuffer sbf = new StringBuffer();
		for (int x = 0; x < field.length; x++) {
			sbf.append(field[x].getName()).append(",");
		}
		sbf.delete((sbf.length() - 1), sbf.length());
		return sbf.toString();
	}
}
