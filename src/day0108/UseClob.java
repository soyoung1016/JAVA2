package day0108;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.connection.GetConnection;

public class UseClob {
	
	public UseClob() throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BufferedReader br = null;
		
		try {
		//1.
		//2.
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			con = GetConnection.getInstance().getConn(url, id, pass);
			
		//3.
			String selectClob = "select subject,news,reporter,to_char(input_date,'yyyy-mm-dd') input_date from test_clob";
			pstmt = con.prepareStatement(selectClob);
		//4.
		//5.
			rs = pstmt.executeQuery();
			
			System.out.println("번호\t기자\t작성일\t기사\n");
			System.out.println("------------------------------------------------------");
			
			int cnt = 1;
			String reporter = "", input_date="";
			StringBuilder news = null;
			String temp = "";
			
			while(rs.next()) {
				reporter = rs.getString("reporter");
				input_date = rs.getString("input_date");
//				news = rs.getString("news"); //clob는 local상에서는 getString으로 얻어진다.
				news = new StringBuilder();
				//CLOB 데이터를 얻기 위한 스트림을 연결
				br = new BufferedReader(rs.getClob("news").getCharacterStream());
				
				//글에 엔터가 있으면 인식하게 된다.
				while((temp = br.readLine()) != null) {
					news.append(temp).append("\n");
				}
				
				System.out.printf("%d\t%s\t%s\t%s\n", cnt++,reporter,input_date,news.toString());
			}//end while 
			
		} finally {
			//6. 
			if(br != null) {
				br.close();
			}//end if
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
		
	}//UseClob

	public static void main(String[] args) {
		try {
			new UseClob();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}//end catch
	}//main

}//class
