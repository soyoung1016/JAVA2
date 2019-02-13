package day0103;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC (Java DataBase Connectivity)�� ����Ͽ� DBMS�� Connection�� ���
 * @author owner
 */
public class UseConnection {

	public UseConnection() throws SQLException {
		//1. ����̹� �ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("����̹� �ε� ����");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}//end catch
		
		//2. �ε��� ����̹��� ����Ͽ� DB���� ���
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = DriverManager.getConnection(url, id, pass);
			System.out.println("DB���� ��� ���� : "+con);
			
			//3. ������ ������ü ���
			stmt = con.createStatement();
			
			//4. ������ ���� �� ��� ���
			String insertQuery="insert into cp_emp2(empno,ename,hiredate,sal) values(1234,'������',sysdate, 3000)";
			//JAVA���� DB ������ ������ ������ �����ݷ��� ���� �ʴ´�.
			int cnt = stmt.executeUpdate(insertQuery);
			System.out.println(cnt + "�� �߰� ����");
			
		} finally {
			//5. ���� ����
			if(stmt != null) {
				stmt.close();
			}//end if
			
			if(con != null) {
				con.close();
			}//end if
		}//end finally
	}//UseConnection
	
	public static void main(String[] args) {
		try {
			new UseConnection();
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
	}//main

}//class
