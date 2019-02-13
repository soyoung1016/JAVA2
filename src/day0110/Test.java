package day0110;

import java.sql.Connection;
import java.sql.SQLException;

import kr.co.sist.connection.GetConnection;

public class Test {
	
	public Test() throws SQLException{
		String url = "jdbc:oracle:thin:@211.63.89.142:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		Connection con = null;
		con = GetConnection.getInstance().getConn(url, id, pass);
		System.out.println(con);
	}//Test

	public static void main(String[] args) {
		try {
			new Test();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//main

}//class
