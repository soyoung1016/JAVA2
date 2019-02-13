package kr.co.sist.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton pattern���� ����� Ŭ����<br>
 * DB Connection�� ��ȯ�ϴ� ��
 * @author owner
 */
public class GetConnection {
	private static GetConnection gc;
	
	private GetConnection() {
		
	}//GetConnection
	
	public static GetConnection getInstance() {
		if(gc == null) {
			gc = new GetConnection();
		}//end if
		return gc;
	}//getInstance

	public Connection getConn(String url, String id, String pass) throws SQLException {
		Connection con = null;
		
		//1. ����̹��ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//2. Connection ���
//		String url = "";
//		String id = "";
//		String pass = "";
		con = DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getConn
	
}//class
