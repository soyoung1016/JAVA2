package day0109;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class TableCreateEvt extends WindowAdapter implements ActionListener {
	private RunTableCreate rtc;
	private StringBuilder query;
	private int cnt;
	
	public TableCreateEvt(RunTableCreate rtc) {
		this.rtc = rtc;
		
	}//TableCreateEvt
	
	public void addTabName() {
		query = new StringBuilder("create table ");
		query/*.append("create table ")*/.append(rtc.getJtfTabName().getText()).append("(\n");
		
		rtc.getJtaQueryView().setText(query.toString()+");");
		
	}//addTabName
	
	public void addColumn(int cnt) {
		String data = query.toString();
		
	
			if(cnt >= 2) {
				query.append(",\n").append(rtc.getJtfColumnName().getText()).append(" ")
				.append(rtc.getJcbDataType().getSelectedItem().toString())
				.append("(").append(rtc.getJtfSize().getText()).append(") ")
				.append(rtc.getJcbConstraint().getSelectedItem().toString()).append(" ")
				.append(rtc.getJtfConName().getText());
				rtc.getJtaQueryView().setText(query.toString()+"\n);");
			} else {
				query.append(rtc.getJtfColumnName().getText()).append(" ")
				.append(rtc.getJcbDataType().getSelectedItem().toString())
				.append("(").append(rtc.getJtfSize().getText()).append(") ")
				.append(rtc.getJcbConstraint().getSelectedItem().toString()).append(" ")
				.append(rtc.getJtfConName().getText());
//				String temp = query.toString();
//				int index = temp.indexOf(",");
//				String result = temp.substring(0, index);
				rtc.getJtaQueryView().setText(query+"\n);");
			}
//				if(cnt >= 2) {
//					rtc.getJtaQueryView().setText(result+"\n);");
//				}
	}
	
	public void allReset() {
		rtc.getJtfTabName().setText("");
		rtc.getJtfColumnName().setText("");
		rtc.getJtfSize().setText("");
		rtc.getJtfConName().setText("");
		
		rtc.getJcbDataType().setSelectedIndex(0);
		rtc.getJcbConstraint().setSelectedIndex(0);
		
		rtc.getJtaQueryView().setText("");
	}//allReset
	
	@Override
	public void windowClosing(WindowEvent we) {
		rtc.dispose();
	}//windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == rtc.getJbTabNameAdd()) {
			addTabName();
		}//end if
		if(ae.getSource() == rtc.getJbConNameAdd()) {
			cnt++;
			addColumn(cnt);
		}//end if
		if(ae.getSource() == rtc.getJbReset()) {
			allReset();
		}//end if
	}//actionPerformed

}//class
