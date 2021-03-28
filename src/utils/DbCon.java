package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
	public static Connection getConnection() {
		try {
			Class.forName("com.jdbc.Driver");
			Connection con=DriverManager.getConnection("");
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
