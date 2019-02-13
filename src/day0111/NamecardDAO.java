package day0111;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.connection.GetConnection;

/**
 * DB Table에 명함 정보를 추가, 모든 명함정보를 조회
 * @author owner
 */
public class NamecardDAO {

	private static NamecardDAO n_dao;
	
	private NamecardDAO() {
		
	}//NamecardDAO
	
	public static NamecardDAO getInstance() {//NamecardDAO가 반환형인!
		//singleton
		if(n_dao == null) {
			n_dao = new NamecardDAO();
		}//end if
		return n_dao;
	}//getInstance
	
	public void insertNamecard(NamecardVO nvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
		//1.
		//2.
			String url = "jdbc:oracle:thin:@211.63.89.141:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			con = GetConnection.getInstance().getConn(url, id, pass);
		//3.
			String insertNamecard = "insert into namecard(name,addr,img) values(?,?,?)";
			pstmt = con.prepareStatement(insertNamecard);
		//4.
			pstmt.setString(1, nvo.getName());
			pstmt.setString(2, nvo.getAddr());
			pstmt.setString(3, nvo.getImg());
		//5.
			pstmt.executeUpdate(); //insert는 성공 아니면 실패만 있기 때문에, 굳이 받아주지 않아도 된다.
			
		} finally {
		//6.
			if(pstmt != null) {
				pstmt.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		}//end finally
		
	}//insertNamecard
	
	public List<NamecardVO> selectNamecard() throws SQLException {
		List<NamecardVO> list = new ArrayList<NamecardVO>();
		
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
		//3.
			String selectNamecard = "select name,addr,img from namecard order by input_date";
			pstmt = con.prepareStatement(selectNamecard);
		//4.
		//5.
			rs = pstmt.executeQuery();
			
			NamecardVO nvo = null;
			while(rs.next()) {
				nvo = new NamecardVO(rs.getString("name"), rs.getString("addr"), rs.getString("img"));
				list.add(nvo);
			}//end while
			
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
		
		return list;
	}//selectNamecard
	
//	public static void main(String[] args) {
//		NamecardDAO n = new NamecardDAO();
//		try {
//			n.insertNamecard(new NamecardVO("김건하", "서울시 성동구 옥수수", "no_image.png"));
//		} catch (SQLException se) {
//			se.printStackTrace();
//		}
//	}//main
	
}//class
