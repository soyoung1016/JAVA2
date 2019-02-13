package kr.co.sist.chat.client.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.chat.client.evt.MultiChatClientEvt;

@SuppressWarnings("serial")
public class MultiChatClientView extends JFrame{
	
	private JTextArea jtaTalkDisplay;
	private JTextField jtfServerIp, jtfNick, jtfTalk;
	private JButton jbtConnect, jbtCapture, jbtClose, jbtUserList;
	private JScrollPane jspTalkDisplay;
	private int selectPort;
	
	public MultiChatClientView(int selectPort) {
		
		super("::::::::::: ä�� Ŭ���̾�Ʈ :::::::::::");
		
		this.selectPort = selectPort;
		
		jtaTalkDisplay = new JTextArea();
		
		jtfServerIp = new JTextField("211.63.89.", 10);
		jtfServerIp.setCaretPosition(10);
		
		jtfNick = new JTextField(10);
		jtfTalk = new JTextField();
		jtfTalk.setBorder(new TitledBorder("��ȭ"));
		
		jbtConnect = new JButton("����");
		jbtCapture = new JButton("����");
		jbtClose = new JButton("����");
		jbtUserList = new JButton("������ ���");
		
		jspTalkDisplay = new JScrollPane(jtaTalkDisplay);
		jspTalkDisplay.setBorder(new TitledBorder("��ȭ����"));
		
		jtaTalkDisplay.setEditable(false);
		
		JPanel jpNorth = new JPanel();
		jpNorth.add(new JLabel("�����ּ�",JLabel.CENTER));
		jpNorth.add(jtfServerIp);
		jpNorth.add(new JLabel("��ȭ��",JLabel.CENTER));
		jpNorth.add(jtfNick);
		jpNorth.add(jbtConnect);
		jpNorth.add(jbtCapture);
		jpNorth.add(jbtClose);
		jpNorth.add(jbtUserList);
		
		add("North", jpNorth);
		add("Center", jspTalkDisplay);
		add("South", jtfTalk);
		
		MultiChatClientEvt mcce = new MultiChatClientEvt(this);
		addWindowListener(mcce);
		
		jbtConnect.addActionListener(mcce);
		jbtClose.addActionListener(mcce);
		jbtCapture.addActionListener(mcce);
		jbtUserList.addActionListener(mcce);
		jtfTalk.addActionListener(mcce);
		
		setBounds(100,100,700,300);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//MultiChatClientView

	public JTextArea getJtaTalkDisplay() {
		return jtaTalkDisplay;
	}

	public JTextField getJtfServerIp() {
		return jtfServerIp;
	}

	public JTextField getJtfNick() {
		return jtfNick;
	}

	public JTextField getJtfTalk() {
		return jtfTalk;
	}

	public JButton getJbtConnect() {
		return jbtConnect;
	}

	public JButton getJbtCapture() {
		return jbtCapture;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}
	
	public JButton getJbtUserList() {
		return jbtUserList;
	}

	public JScrollPane getJspTalkDisplay() {
		return jspTalkDisplay;
	}

	public int getSelectPort() {
		return selectPort;
	}
	
}//class
