package day0109;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.connection.GetConnection;

/**
 * DDL (Data Definition Language) : create, drop, truncated을 사용하여 테이블을 생성
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
			String tableName = JOptionPane.showInputDialog("생성할 테이블명 입력");
			
		//3.
			//테이블이 존재유무 확인
			StringBuilder selectTname = new StringBuilder();
			selectTname.append("select tname from tab where tname=?");
		//4.
			pstmt = con.prepareStatement(selectTname.toString());
			pstmt.setString(1,  tableName.toUpperCase()); //테이블명은 대문자로 조회해야하기 때문에 Upper를 사용한다
		//5.
			rs = pstmt.executeQuery();
			boolean flag = false;
			if(rs.next()) {
				flag = JOptionPane.showConfirmDialog(null, tableName+" 테이블이 존재합니다. 삭제한 후 생성하시겠습니까?")
						== JOptionPane.OK_OPTION;	//????????????
			}//end if
			
			pstmt.close();// 한번 끊어주지 않으면 메모리가 누수됨
			
			if(flag) { //삭제 한 후 테이블 생성
				StringBuilder dropTable = new StringBuilder();
				//3.
				dropTable.append("drop table ").append(tableName);
				pstmt = con.prepareStatement(dropTable.toString());
				//4.
				pstmt.execute();
				pstmt.close(); //메모리 누수, 또 끊어줌
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
			JOptionPane.showMessageDialog(null, "테이블이 생성되었습니다.");
			
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
			JOptionPane.showMessageDialog(null, "이미 존재하는 테이블입니다.");
			se.printStackTrace();
		}//end catch

	}//main

}//class
