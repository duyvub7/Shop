package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class dungchung {
	public Connection cn;
	public void KetNoi() throws Exception{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url="jdbc:sqlserver://DESKTOP-H31ND8D:1433;DatabaseName=ShopJava;user=sa;password=123";
		cn= DriverManager.getConnection(url);
		System.out.println("Da ket noi");
	}
}
