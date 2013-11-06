package smart.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import net.cellcloud.util.SlidingWindowExecutor;
import net.cellcloud.util.SlidingWindowTask;

/**
 * 提供mariadb的数据库的连接，资源关闭
 * 
 */
public class DButil {

	private static final DButil instance = new DButil();
	// 定义连接数据库的URL字符串
	private static final String URL = "jdbc:mariadb://localhost:3308/resource";
	// 连接数据库的用户名
	private static final String USER = "root";
	// 连接数据库的密码
	private static final String PASSWORD = "root";

	// 数据库的连接对象
	private static Connection conn;

	private SlidingWindowExecutor swe = SlidingWindowExecutor.newSlidingWindowThreadPool(4);

	public static DButil getInstance() {
		return DButil.instance;
	}

	// 使用静态块注册数据库的驱动类
	static {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库的连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		
//		SlidingWindowTask swt=new SlidingWindowTask(swe,new Runnable() {
//		
//			@Override
//			public void run() {
//				if (conn == null) {
//					try {
//						conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//						System.out.println("mythread...");
//					} catch (Exception e) {
//					}
//				}
//				
//			}
//		} );
//		
		
//		swt.run();
//		int i=swe.snapshootThreadNum();
//		System.out.println("thread-num    "+i);
		
		
//		swe.execute(new Runnable() {
//			@Override
//			
//			public void run() {
				if (conn == null) {
					try {
						conn = DriverManager.getConnection(URL, USER, PASSWORD);

						System.out.println("mythread...");
					} catch (Exception e) {
					}
				}
//			}
//
//		});

		
		// 等待子线程全部结束
//		try {
//			Thread.sleep(500);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		SlidingWindowTask swt=new SlidingWindowTask(swe);
//		swt.run();
		// 关闭所有子线程，不关的话主线程会一直阻塞
//		swe.shutdown();
//		try {
//			// 等待线程终止
//			if (!swe.awaitTermination(10, TimeUnit.SECONDS)) {
//				swe.shutdownNow();
//				if (!swe.awaitTermination(10, TimeUnit.SECONDS))
//					System.err.println("Pool did not terminate");
//			}
//		} catch (InterruptedException ie) {
//			swe.shutdownNow();
//			Thread.currentThread().interrupt();
//		}

		return conn;
	}

	/**
	 * 关闭数据库的资源
	 * 
	 * @param stmt
	 *            语句对象
	 * @param ts
	 *            查询的结果集
	 */
	public void close(Statement stmt, ResultSet rs) {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭数据库的连接
	 * 
	 * @param conn
	 */
	public void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

	public static void main(String[] args) {
		// 测试连接
		System.out.println(DButil.getInstance().getConnection());
	}

}
