package cn.xmkeshe.utils.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * 
 * @author 欣茂科技软件学院 （xmkeshe.cn）
 * <li>此类主要负责数据库连接与关闭，通过读取资源文件中的内容加载数据库配置信息。</li>
 */
public class DatabaseConnection {
	private static final String DBDRIVER =ResourceBundle.getBundle("jdbc").getString("driver");
	private static final String DBURl =ResourceBundle.getBundle("jdbc").getString("url");
	private static final String DBUSER =ResourceBundle.getBundle("jdbc").getString("user");
	private static final String DBPASSWORD =ResourceBundle.getBundle("jdbc").getString("password");
	private Connection conn;
	/**
	 * 加载配置信息
	 */
	public DatabaseConnection() {
		try {
			Class.forName(DBDRIVER);
			this.conn = DriverManager.getConnection(DBURl, DBUSER, DBPASSWORD);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 执行数据连接
	 * @return 返回当前连接信息
	 */
	public Connection getConn() {
		return this.conn;
	}
	/**
	 * 数据库关闭
	 */
	public void close() {
		if(this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
