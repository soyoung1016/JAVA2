package day0109;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class TableCreateEvt2 extends WindowAdapter implements ActionListener {
	private RunTableCreate rtc;
	private String createTabName, createColAdd;
	
	private String inputQuery;
	private String inputTabName;
	private String inputColName;
	private String inputColType;
	private String inputColSize;
	private String inputConstraint;
	private String inputConName;
	
	public TableCreateEvt2(RunTableCreate rtc) {
		this.rtc = rtc;
		
	}//TableCreateEvt
	
	public void tabNameAdd() {
		
		inputQuery = "create table "+inputTabName;
//		if(inputQuery.contains(");")) {
//			int index = inputQuery.indexOf(")");
//			inputQuery = inputQuery.substring(0,index);
//		}//end if
		
		rtc.getJtaQueryView().setText(inputQuery);
	}//TabNameAdd
	
	public void columnAdd() {
		
		if(inputQuery.contains(");")) {
			int index = inputQuery.indexOf(")");
			inputQuery = inputQuery.substring(0,index);
		}
		
		createColAdd = inputColName+" "+inputColType+"("+inputColSize+") "
				+inputConstraint+inputConName+");";
		
		if(inputConstraint.equals("null")) {
			createColAdd = inputColName+" "+inputColType+"("+inputColSize+") "
					+inputConstraint+");";
			
		}
		
//		if(inputConstraint.equals("null")) {
////			inputConName = "";
//			createColAdd = inputColName+" "+inputColType+"("+inputColSize+") "
//							+inputConstraint+inputConName+");";
//		} else {
//			createColAdd = inputColName+" "+inputColType+"("+inputColSize+") "
//					+inputConstraint+" "+inputConName+");";
//		}//end else
		
		rtc.getJtaQueryView().setText("");
		rtc.getJtaQueryView().setText(inputQuery+createColAdd);
	}//columnAdd
	
//	public void addPlus() {
//		String temp = rtc.getJtaQueryView().getText();
//		if(temp.contains(");")) {
//			int index = temp.indexOf(")");
//			temp = createTabName.substring(0,index);
//		}//end if
//		
//		rtc.getJtaQueryView().setText("");
//		rtc.getJtaQueryView().setText(createTabName+"\n"+createColAdd);
//		
//	}//addPlus
	
//	public void tableCreate() throws SQLException {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//		//1.
//		//2.
//			String url ="jdbc:oracle:thin:@localhost:1521:orcl";
//			String id ="scott";
//			String pass ="tiger";
//			con = GetConnection.getInstance().getConn(url, id, pass);
//			
//		//3. 쿼리문 생성객체 얻기
//			String inputTabName = rtc.getJtfTabName().getText().trim();
//			
//			StringBuilder createTabName = new StringBuilder();
//			createTabName.append("create table ").append(inputTabName).append("();");
//			pstmt = con.prepareStatement(createTabName.toString());
//		//4. 바인드변수
//		//5.
//			pstmt.executeQuery();
//			rtc.getJtaQueryView().setText(createTabName.toString());
//		} finally {	
//		//6.
//			if(pstmt != null) {
//				pstmt.close();
//			}//end if
//			if(con != null) {
//				con.close();
//			}//end if
//		}//end finally
//	}//tableCreate
	
	@Override
	public void windowClosing(WindowEvent we) {
		rtc.dispose();
	}//windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == rtc.getJbTabNameAdd()) {
			inputTabName = rtc.getJtfTabName().getText().trim()+"(\n);";
			inputColName = rtc.getJtfColumnName().getText().trim();
			inputColType = rtc.getJcbDataType().getSelectedItem().toString();
			inputColSize = rtc.getJtfSize().getText().trim();
			inputConstraint = rtc.getJcbConstraint().getSelectedItem().toString();
			inputConName = rtc.getJtfConName().getText().trim();
			tabNameAdd();
		}//end if
		if(ae.getSource() == rtc.getJbConNameAdd()) {
			inputTabName = rtc.getJtfTabName().getText().trim()+"(\n);";
			inputColName = rtc.getJtfColumnName().getText().trim();
			inputColType = rtc.getJcbDataType().getSelectedItem().toString();
			inputColSize = rtc.getJtfSize().getText().trim();
			inputConstraint = rtc.getJcbConstraint().getSelectedItem().toString();
			inputConName = rtc.getJtfConName().getText().trim();
			columnAdd();
		}//end if
	}//actionPerformed

}//class
