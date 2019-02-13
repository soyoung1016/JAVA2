package kr.co.sist.chat.client.view;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MultiChatClientRoomView extends JFrame implements ActionListener{
	
	private JButton jbtChoice;
	private Checkbox group1, group2, group3, group4;
	private int SelectPort;
	
	public MultiChatClientRoomView() {
		super("::::::채팅방선택::::::");
		
		JLabel jlbTitle = new JLabel("접속할 채팅방을 선택하세요");
		
		JPanel jpCheck = new JPanel();
		CheckboxGroup cg = new CheckboxGroup();
		group1 = new Checkbox("1조", false, cg); 
		group2 = new Checkbox("2조", false, cg);
		group3 = new Checkbox("3조", false, cg);
		group4 = new Checkbox("4조", false, cg);
		
		jpCheck.add(group1);
		jpCheck.add(group2);
		jpCheck.add(group3);
		jpCheck.add(group4);
		
		jbtChoice = new JButton("접속");
		
		add(jlbTitle);
		add(jpCheck);
		add(jbtChoice);
		
		setLayout(null);
		
		jlbTitle.setBounds(65,20,200,30);
		jpCheck.setBounds(5,50,280,30);
		jbtChoice.setBounds(105,100,80,30);
		
		jbtChoice.addActionListener(this);
		
		setBounds(100,100,300,200);
		setVisible(true);
		
	}//MultiChatClientRoomView

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(jbtChoice)) {
			if(group1.getState()) {
				SelectPort = 35000;
			}//end if
			if(group2.getState()) {
				SelectPort = 35001;
			}//end if
			if(group3.getState()) {
				SelectPort = 35002;
			}//end if
			if(group4.getState()) {
				SelectPort = 35003;
			}//end if
		}//end if
		
		new MultiChatClientView(SelectPort);
		dispose();
	}//actionPerformed
	
}//class
