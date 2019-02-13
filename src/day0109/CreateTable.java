package day0109;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.connection.GetConnection;

/**
 * DDL (Data Definition Language) : create, drop, truncated�� ����Ͽ� ���̺��� ����
 * @author owner
 */
public class CreateTable {

	public CreateTable() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		//1.
		//2.
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			con = GetConnection.getInstance().getConn(url, id, pass);
			String tableName = JOptionPane.showInputDialog("������ ���̺�� �Է�");
			
		//3.
			//���̺��� �������� Ȯ��
			StringBuilder selectTname = new StringBuilder();
			selectTname.append("select tname from tab where tname=?");
		//4.
			pstmt = con.prepareStatement(selectTname.toString());
			pstmt.setString(1,  tableName.toUpperCase()); //���̺���� �빮�ڷ� ��ȸ�ؾ��ϱ� ������ Upper�� ����Ѵ�
		//5.
			rs = pstmt.executeQuery();
			boolean flag = false;
			if(rs.next()) {
				flag = JOptionPane.showConfirmDialog(null, tableName+" ���̺��� �����մϴ�. ������ �� �����Ͻðڽ��ϱ�?")
						== JOptionPane.OK_OPTION;	//????????????
			}//end if
			
			pstmt.close();// �ѹ� �������� ������ �޸𸮰� ������
			
			if(flag) { //���� �� �� ���̺� ����
				StringBuilder dropTable = new StringBuilder();
				//3.
				dropTable.append("drop table ").append(tableName);
				pstmt = con.prepareStatement(dropTable.toString());
				//4.
				pstmt.execute();
				pstmt.close(); //�޸� ����, �� ������
			}//end if
			
		//3.
			StringBuilder createTable = new StringBuilder();
			
			createTable.append("create table ").append(tableName).append("(")
						.append(" name varchar2(13) not null, ")
						.append(" age number(3) not null, ")
						.append(" id varchar2(20) constraint pk_").append(tableName).append(" primary key)");
			
			pstmt = con.prepareStatement(createTable.toString());
		//4.
		//5.
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "���̺��� �����Ǿ����ϴ�.");
			
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
	}//CreateTable
	
	public static void main(String[] args) {
		try {
			new CreateTable();
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ���̺��Դϴ�.");
			se.printStackTrace();
		}//end catch

	}//main

}//class
