package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseConnection {
	public static String conn(String sql, String col) throws Exception {
		String result = null;
		try {
			// 1、加载驱动程序
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("Success loading Mysql Driver!");

			// 2、连接数据库DriverManager.getConnection(url,user,password)
			Connection connect = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/ruleverify?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
					"root", "123456");
			// System.out.println("Success connect Mysql server!");

			// 3、创建statement类对象，用来执行SQL语句！！
			Statement stmt = connect.createStatement();

			// 4、ResultSet类，用来存放获取的结果集！！
			ResultSet rs = stmt.executeQuery(sql);

			// 5、处理数据
			while (rs.next()) {
				result = rs.getString(col);

			}
			// System.out.println("数据处理完毕");
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
		return result;
	}

}
