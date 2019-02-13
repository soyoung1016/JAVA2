package day0109;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

import kr.co.sist.connection.GetConnection;

/**
 * Procedure 호출
 * @author owner
 */
public class UseCallableStatement {

	public UseCallableStatement() throws SQLException {
		
		Connection con = null;
		CallableStatement cstmt = null;
		
		String tempData = JOptionPane.showInputDialog("숫자 2개 입력\n예)숫자,숫자");
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
			//전에 만든 procedure 불러서 쓴것.. 거기 안에 플러스 내용 있다.
		//4. 바인드 변수에 값 넣기
			//in parameter
			cstmt.setInt(1, num1);
			cstmt.setInt(2, num2);
			
			//out parameter
			cstmt.registerOutParameter(3, Types.NUMERIC);
		//5. 쿼리(Procedure) 실행 후 결과 얻기
			//프로시져를 실행하면 in parameter와 out parameter에 값 할당
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
