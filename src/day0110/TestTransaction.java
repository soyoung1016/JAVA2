package day0110;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.connection.GetConnection;

/**
 * Transaction 처리
 * @author owner
 */
public class TestTransaction {
	//commit과 rollback을 DB작업 외부에서 처리할 수 있도록 class field 정의
	private Connection con = null;
	
	public boolean insert(TransactionVO tv) throws SQLException {
		//transaction에 사용할 쿼리의 수만큼 쿼리 실행 객체를 선언
		boolean flag = false;
		///////////////커밋과 롤백은 메소드 밖에서~!~!////////////////////////
		//prepared는 여러개 써야하는것!
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		
		try {
		//1.
		//2.
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			con = GetConnection.getInstance().getConn(url, id, pass);
			//auto commit 해제!
			con.setAutoCommit(false);
		//3.
			String sql1 = "insert into test_transaction1(subject,writer)values(?,?)";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, tv.getSubject());
			pstmt.setString(2, tv.getWriter());
		//4.
			int cnt1 = pstmt.executeUpdate();
			
		//트랜잭션 써야해서 3~5번 과정은 한 번 더!
		//3.
			String sql2 = "insert into test_transaction2(subject,writer)values(?,?)";
			pstmt1 = con.prepareStatement(sql2);
			pstmt1.setString(1, tv.getSubject());
			pstmt1.setString(2, tv.getWriter());
		//4.
			int cnt2 = pstmt1.executeUpdate();
			//트랜잭션에 해당하는 모든 쿼리의 목표 수행 수를 비교하여 commit과 rollback 여부를 설정한다.
			if(cnt1==1 && cnt2 ==1) {
				flag = true;
			}//end if
			
		} finally {
		//6.
		}//end finally
		return flag;
	}//insert
	
	//close하면 자동으로? 커밋되기 때문에 끊는것도 밖에서 해야한다.
	
	public void add() {
		String inputData =
		JOptionPane.showInputDialog("제목과 작성자를 입력해주세요.\n제목-작성자");
		
		String[] data = inputData.split("-");
		
		if(data.length != 2) {
			JOptionPane.showMessageDialog(null, "입력 형식을 확인해주세요");
			return;
		}//end if
		
		TransactionVO tv = new TransactionVO(data[0],data[1]);
		
		try {
			//DB업무를 사용하는 곳에서 수행 결과를 받아
			boolean flag = insert(tv);
			if(flag) {
				//커밋하거나
				con.commit();
				System.out.println("커밋!");
			} else {
				//update나 delete가 transaction일 때
				//롤백한다.
				con.rollback();
				System.out.println("이 롤백은 update, delete의 롤백!");
			}//end else
		} catch (SQLException se) {
			try {
				con.rollback();
				System.out.println("이 롤백은 insert의 롤백!");
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			se.printStackTrace();
		} finally {
			try {
				con.close(); //connection 끊으면 pstmt도 끊어진다. 직접 끊고 싶으면 pstmt도 con 올린것처럼 올리면 된다.
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
