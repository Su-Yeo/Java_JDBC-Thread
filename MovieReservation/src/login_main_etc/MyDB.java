package login_main_etc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyDB {
	
	public static Connection getCon() {
		Connection con = null;
		try {
			//JDBC Driver 메모리에 Load (Driver 객체 생성)
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/yuhandb?charaterEncoding=ufg-8&serverTimezone=UTC&useSSL=false";
			String userId = "yuhan";
			String pwd = "yuhan1234";
			con = DriverManager.getConnection(url, userId, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
