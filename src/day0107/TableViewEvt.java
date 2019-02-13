package day0107;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TableViewEvt extends WindowAdapter implements ActionListener{

	private TableView tv;
	
	public TableViewEvt(TableView tv) {
		this.tv = tv;
		try {
			selectAllTname();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//TableViewEvt
	
	@Override
	public void windowClosing(WindowEvent we) {
		tv.dispose();
	}//windowClosing
	
	public List<String> selectAllTname() throws SQLException {
		List<String> list = new ArrayList<String>();
		//1. 드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}//end catch
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		//2. Connection 얻기
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			
			con = DriverManager.getConnection(url, id, pass);
		//3. 쿼리문 생성객체 얻기
			stmt = con.createStatement();
			
		//4. 쿼리 수행 후 결과 얻기
			StringBuilder selectTname = new StringBuilder();
			selectTname.append("select tname ").append("from tab");
			
			rs = stmt.executeQuery(selectTname.toString());
			
			while(rs.next()) { //조회된 레코드가 존재한다면
				String temp = "";
				temp = rs.getString("tname");
				//같은 이름의 객체를 여러개 관리하기 위해 List에 추가
				list.add(temp);
			}//end while
			
			String[] allTname = list.toArray(new String[list.size()]);
			
			for(int i=0; i<allTname.length; i++) {
				tv.getJcbAllTname().addItem(allTname[i]);
			}//end for
			
		} finally {
		//5. 연결끊기
			if(rs != null) {
				rs.close();
			}//end if
			
			if(stmt != null) {
				stmt.close();
			}//end if
			
			if(con != null) {
				con.close();
			}//end if
			
		}//end finally
		
		return list;
	}//selectAllTname
	
	public List<TableVO> searchTableInfo(String tname) throws SQLException {
		List<TableVO> listTab = new ArrayList<TableVO>();
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//1.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		//2.
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			
			con = DriverManager.getConnection(url, id, pass);
		//3.
			StringBuilder selectTabInfo = new StringBuilder();
			selectTabInfo.append("select utc.column_name, utc.data_type, utc.data_precision, ucc.constraint_name ")
						 .append("from user_tab_columns utc, user_cons_columns ucc ")
						 .append("where utc.table_name = ucc.table_name(+) ")
						 .append("and utc.column_name = ucc.column_name(+) ")
						 .append("and utc.table_name=?");
			
//			select utc.column_name, utc.data_type, utc.data_precision, ucc.constraint_name
//			from user_tab_columns utc, user_cons_columns ucc
//			where utc.table_name = ucc.table_name(+)
//			and utc.column_name = ucc.column_name(+)
//			and utc.table_name = 'EMP';
			
			pstmt = con.prepareStatement(selectTabInfo.toString());
			
		//4. 바인드 변수에 값 넣기
			pstmt.setString(1, tname);
			
		//5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			
			TableVO tv = null;
			while(rs.next()) {
				tv = new TableVO(rs.getString("column_name"), rs.getString("data_type"),
								rs.getString("data_precision"), rs.getString("constraint_name"));
				listTab.add(tv);
			}//end while
			
		} finally {
		//6. 연결 끊기
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
		return listTab;
		
	}//searchTableInfo
	
	public void searchOneTableInfo(String tname) {
		try {
			//DB에서 조회한 결과를 받아서
			List<TableVO> listTab = searchTableInfo(tname);
			//DefaultTableModel에 추가 => JTable에 반영된다.
			DefaultTableModel dtm = tv.getDtmTable();
			//D.T.M 초기화
			dtm.setRowCount(0);
				
			String[] rowData = null;
			//조회된 결과를 가지고
			for(TableVO tv : listTab) {
				//배열에 넣고
				rowData = new String[4];
				rowData[0] = tv.getColumn_name();
				rowData[1] = tv.getData_type();
				rowData[2] = tv.getData_precision();
				rowData[3] = tv.getConstraint_name();
				//D.T.M에 행(Row : 우편번호, 주소) 추가
				dtm.addRow(rowData);
			}//end for
				
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(tv, "DB에서 문제가 발생하였습니다.");
			se.printStackTrace();
		}//end catch
		
	}//searchOneTableInfo
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String tname = tv.getJcbAllTname().getSelectedItem().toString().trim();
		if(ae.getSource() == tv.getJbtSearch()) {
			searchOneTableInfo(tname);
		}//end if
	}//actionPerformed

}//class
