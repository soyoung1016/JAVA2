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
		//1. ����̹��ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}//end catch
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		//2. Connection ���
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			
			con = DriverManager.getConnection(url, id, pass);
		//3. ������ ������ü ���
			stmt = con.createStatement();
			
		//4. ���� ���� �� ��� ���
			StringBuilder selectTname = new StringBuilder();
			selectTname.append("select tname ").append("from tab");
			
			rs = stmt.executeQuery(selectTname.toString());
			
			while(rs.next()) { //��ȸ�� ���ڵ尡 �����Ѵٸ�
				String temp = "";
				temp = rs.getString("tname");
				//���� �̸��� ��ü�� ������ �����ϱ� ���� List�� �߰�
				list.add(temp);
			}//end while
			
			String[] allTname = list.toArray(new String[list.size()]);
			
			for(int i=0; i<allTname.length; i++) {
				tv.getJcbAllTname().addItem(allTname[i]);
			}//end for
			
		} finally {
		//5. �������
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
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, tname);
			
		//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			
			TableVO tv = null;
			while(rs.next()) {
				tv = new TableVO(rs.getString("column_name"), rs.getString("data_type"),
								rs.getString("data_precision"), rs.getString("constraint_name"));
				listTab.add(tv);
			}//end while
			
		} finally {
		//6. ���� ����
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
			//DB���� ��ȸ�� ����� �޾Ƽ�
			List<TableVO> listTab = searchTableInfo(tname);
			//DefaultTableModel�� �߰� => JTable�� �ݿ��ȴ�.
			DefaultTableModel dtm = tv.getDtmTable();
			//D.T.M �ʱ�ȭ
			dtm.setRowCount(0);
				
			String[] rowData = null;
			//��ȸ�� ����� ������
			for(TableVO tv : listTab) {
				//�迭�� �ְ�
				rowData = new String[4];
				rowData[0] = tv.getColumn_name();
				rowData[1] = tv.getData_type();
				rowData[2] = tv.getData_precision();
				rowData[3] = tv.getConstraint_name();
				//D.T.M�� ��(Row : �����ȣ, �ּ�) �߰�
				dtm.addRow(rowData);
			}//end for
				
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(tv, "DB���� ������ �߻��Ͽ����ϴ�.");
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
