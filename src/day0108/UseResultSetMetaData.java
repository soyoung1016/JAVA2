package day0108;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import kr.co.sist.connection.GetConnection;

/**
 * ��ųʸ��� ������ ����ϴ°�
 * ����Ǵ� ��ȸ �������� ����Ͽ� �÷� ������ ���� �� ����ϴ� interface
 * @author owner
 */
public class UseResultSetMetaData {

	public UseResultSetMetaData() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;	//��� ������°� �ƴ�
		
		try {
		//1. ����̹� �ε�
		//2. Connection ���
			GetConnection gc = GetConnection.getInstance();
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			
			con = gc.getConn(url, id, pass);
			
		//3.
			String selectEmp = "select * from emp";
			pstmt = con.prepareStatement(selectEmp);
		//4.
			//���ε� ���� ����
		//5.
			rs = pstmt.executeQuery();
			
			rsmd = rs.getMetaData();
			//�÷��� ���� ���
			int cnt = rsmd.getColumnCount();
			System.out.println("�÷��� �� : "+cnt);
			
//			String columnName = rsmd.getColumnName(1);
			String columnName = rsmd.getColumnLabel(1);	//name�̳� label�̳� ���� ���
			System.out.println("ù��° �÷��� �÷� �� : "+columnName);
			
			String columnType = rsmd.getColumnTypeName(1);
			System.out.println("ù��° �÷��� �������� �� : "+columnType);
			
			int columnPrecision = rsmd.getPrecision(1);
			System.out.println("ù��° �÷��� �������� ũ�� : "+columnPrecision);
			
			System.out.println("---------------------------------------");
			
			StringBuilder output = new StringBuilder();
			for(int i=1; i<cnt+1; i++) {
				output.append(rsmd.getColumnName(i)).append("\t")
					  .append(rsmd.getColumnTypeName(i)).append("(")
					  .append(rsmd.getPrecision(i)).append(")\n");
			}//end for
			
			System.out.println(output);
			
		} finally {
		//6.
			if(rs != null) {
				rs.close();
			}//end if
			if(pstmt != null) {
				pstmt.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		}//end finally
		
	}//UseResultSetMetaData
	
	public static void main(String[] args) {
		try {
			new UseResultSetMetaData();
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
	}//main

}//class
