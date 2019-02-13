package day0108;

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

public class ZipcodeViewEvt extends WindowAdapter implements ActionListener{
	
	private ZipcodeView zv;
	
	public ZipcodeViewEvt(ZipcodeView zv) {
		this.zv = zv;
	}//ZipcodeViewEvt
	
	@Override
	public void windowClosing(WindowEvent we) {
		zv.dispose();
	}//windowClosing
	
	/**
	 * PreparedStatement�� ����Ͽ� SQL Injection ���
	 * @param dong
	 * @return
	 * @throws SQLException
	 */
	public List<ZipcodeVO> selectZipcode(String dong) throws SQLException {
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		//1. ����̹��ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}//end catch
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		//2. Connection ���
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			
			con = DriverManager.getConnection(url, id, pass);
			
		//3. ������ ������ü ���
			StringBuilder selectZipcode = new StringBuilder();
			selectZipcode.append("select zipcode,sido,gugun,dong,nvl(bunji,' ') bunji ")
						 .append("from zipcode ")
						 .append("where dong like ?||'%'");
			
			System.out.println(selectZipcode);
			pstmt = con.prepareStatement(selectZipcode.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, dong);
			
		//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			
			ZipcodeVO zv = null;
			while(rs.next()) { //��ȸ�� ���ڵ尡 �����Ѵٸ�
				//VO�� ���� �Ҵ��ϰ�
				zv = new ZipcodeVO(rs.getString("zipcode"), rs.getString("sido"), rs.getString("gugun"),
						rs.getString("dong"), rs.getString("bunji"));
				//���� �̸��� ��ü�� ������ �����ϱ� ���� List�� �߰�
				list.add(zv);
			}//end while
			
		} finally {
		//6. �������
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
	}//selectZipcode
	
	public void searchZipcode(String dong) {
		try {
			//DB���� ��ȸ�� ����� �޾Ƽ�
			List<ZipcodeVO> listZip = selectZipcode(dong);
			//DefaultTableModel�� �߰� => JTable�� �ݿ��ȴ�.
			DefaultTableModel dtm = zv.getDtmZipcode();
			//D.T.M �ʱ�ȭ
			dtm.setRowCount(0);
			
			String[] rowData = null;
			//��ȸ�� ����� ������
			for(ZipcodeVO zv : listZip) {
				//�迭�� �ְ�
				rowData = new String[2];
				rowData[0] = zv.getZipcode();
				rowData[1] = zv.getSido()+" "+zv.getGugun()+" "+zv.getDong()+" "+zv.getBunji();
				
				//D.T.M�� ��(Row : �����ȣ, �ּ�) �߰�
				dtm.addRow(rowData);
			}//end for
			
			if(listZip.isEmpty()) {
				rowData = new String[2];
				rowData[0] = "";
				rowData[1] = "�ش� ���� �������� �ʽ��ϴ�.";
				
				dtm.addRow(rowData);
			}//end if
			
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(zv, "DB���� ������ �߻��Ͽ����ϴ�.");
			se.printStackTrace();
		}
	}//searchZipcode

	@Override
	public void actionPerformed(ActionEvent ae) {
		String dong = zv.getJtfDong().getText().trim();
		if(!dong.equals("")) {
			searchZipcode(dong);
		}//end if
		
	}//actionPerformed
	
}//class
