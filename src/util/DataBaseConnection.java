package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseConnection {
	public static String conn(String sql, String col) throws Exception {
		String result = null;
		try {
			// 1��������������
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("Success loading Mysql Driver!");

			// 2���������ݿ�DriverManager.getConnection(url,user,password)
			Connection connect = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/ruleverify?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
					"root", "123456");
			// System.out.println("Success connect Mysql server!");

			// 3������statement���������ִ��SQL��䣡��
			Statement stmt = connect.createStatement();

			// 4��ResultSet�࣬������Ż�ȡ�Ľ��������
			ResultSet rs = stmt.executeQuery(sql);

			// 5����������
			while (rs.next()) {
				result = rs.getString(col);

			}
			// System.out.println("���ݴ������");
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
		return result;
	}

}
