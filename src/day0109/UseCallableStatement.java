package day0109;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

import kr.co.sist.connection.GetConnection;

/**
 * Procedure ȣ��
 * @author owner
 */
public class UseCallableStatement {

	public UseCallableStatement() throws SQLException {
		
		Connection con = null;
		CallableStatement cstmt = null;
		
		String tempData = JOptionPane.showInputDialog("���� 2�� �Է�\n��)����,����");
		int num1 = Integer.parseInt(tempData.split(",")[0]);
		int num2 = Integer.parseInt(tempData.split(",")[1]);
		
		try {
		//1.
		//2.
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			con = GetConnection.getInstance().getConn(url, id, pass);
		//3.
			cstmt = con.prepareCall("{ call proc_plus(?,?,?)}");
			//���� ���� procedure �ҷ��� ����.. �ű� �ȿ� �÷��� ���� �ִ�.
		//4. ���ε� ������ �� �ֱ�
			//in parameter
			cstmt.setInt(1, num1);
			cstmt.setInt(2, num2);
			
			//out parameter
			cstmt.registerOutParameter(3, Types.NUMERIC);
		//5. ����(Procedure) ���� �� ��� ���
			//���ν����� �����ϸ� in parameter�� out parameter�� �� �Ҵ�
			cstmt.execute();
			
			int total = cstmt.getInt(3);
			
			JOptionPane.showMessageDialog(null, num1+" + "+num2+" = "+total);
			
			
		} finally {
		//6.
			if(cstmt != null) {
				cstmt.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		}//end finally
		
	}//UseCallableStatement
	
	
	public static void main(String[] args) {
		try {
			new UseCallableStatement();
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
	}//main

}//class
