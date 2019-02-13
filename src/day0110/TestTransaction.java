package day0110;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.connection.GetConnection;

/**
 * Transaction ó��
 * @author owner
 */
public class TestTransaction {
	//commit�� rollback�� DB�۾� �ܺο��� ó���� �� �ֵ��� class field ����
	private Connection con = null;
	
	public boolean insert(TransactionVO tv) throws SQLException {
		//transaction�� ����� ������ ����ŭ ���� ���� ��ü�� ����
		boolean flag = false;
		///////////////Ŀ�԰� �ѹ��� �޼ҵ� �ۿ���~!~!////////////////////////
		//prepared�� ������ ����ϴ°�!
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		
		try {
		//1.
		//2.
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			con = GetConnection.getInstance().getConn(url, id, pass);
			//auto commit ����!
			con.setAutoCommit(false);
		//3.
			String sql1 = "insert into test_transaction1(subject,writer)values(?,?)";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, tv.getSubject());
			pstmt.setString(2, tv.getWriter());
		//4.
			int cnt1 = pstmt.executeUpdate();
			
		//Ʈ����� ����ؼ� 3~5�� ������ �� �� ��!
		//3.
			String sql2 = "insert into test_transaction2(subject,writer)values(?,?)";
			pstmt1 = con.prepareStatement(sql2);
			pstmt1.setString(1, tv.getSubject());
			pstmt1.setString(2, tv.getWriter());
		//4.
			int cnt2 = pstmt1.executeUpdate();
			//Ʈ����ǿ� �ش��ϴ� ��� ������ ��ǥ ���� ���� ���Ͽ� commit�� rollback ���θ� �����Ѵ�.
			if(cnt1==1 && cnt2 ==1) {
				flag = true;
			}//end if
			
		} finally {
		//6.
		}//end finally
		return flag;
	}//insert
	
	//close�ϸ� �ڵ�����? Ŀ�ԵǱ� ������ ���°͵� �ۿ��� �ؾ��Ѵ�.
	
	public void add() {
		String inputData =
		JOptionPane.showInputDialog("����� �ۼ��ڸ� �Է����ּ���.\n����-�ۼ���");
		
		String[] data = inputData.split("-");
		
		if(data.length != 2) {
			JOptionPane.showMessageDialog(null, "�Է� ������ Ȯ�����ּ���");
			return;
		}//end if
		
		TransactionVO tv = new TransactionVO(data[0],data[1]);
		
		try {
			//DB������ ����ϴ� ������ ���� ����� �޾�
			boolean flag = insert(tv);
			if(flag) {
				//Ŀ���ϰų�
				con.commit();
				System.out.println("Ŀ��!");
			} else {
				//update�� delete�� transaction�� ��
				//�ѹ��Ѵ�.
				con.rollback();
				System.out.println("�� �ѹ��� update, delete�� �ѹ�!");
			}//end else
		} catch (SQLException se) {
			try {
				con.rollback();
				System.out.println("�� �ѹ��� insert�� �ѹ�!");
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			se.printStackTrace();
		} finally {
			try {
				con.close(); //connection ������ pstmt�� ��������. ���� ���� ������ pstmt�� con �ø���ó�� �ø��� �ȴ�.
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end finally
		
	}//add
	
	public static void main(String[] args) {
		TestTransaction tt = new TestTransaction();
		tt.add();
	}//main

}//class
