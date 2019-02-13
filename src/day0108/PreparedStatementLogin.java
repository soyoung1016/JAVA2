package day0108;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PreparedStatementLogin extends JFrame{

	private JTextField jtfId;
	private JPasswordField jpfPass;
	private JButton jbtLogin, jbtCancle;
	
	public PreparedStatementLogin() {
		super("Statement 객체를 사용한 로그인");
		
		jtfId = new JTextField();
		jpfPass = new JPasswordField();
		jbtLogin = new JButton("로그인");
		jbtCancle = new JButton("취소");
		
		setLayout(new GridLayout(3, 1));
		JPanel panel = new JPanel();
		panel.add(jbtLogin);
		panel.add(jbtCancle);
		
		jtfId.setBorder(new TitledBorder("아이디"));
		jpfPass.setBorder(new TitledBorder("비밀번호"));
		
		add(jtfId);
		add(jpfPass);
		add(panel);
		
		PreparedStatementLoginEvt sle = new PreparedStatementLoginEvt(this);
		jtfId.addActionListener(sle);
		jpfPass.addActionListener(sle);
		jbtLogin.addActionListener(sle);
		jbtCancle.addActionListener(sle);
		
		addWindowListener(sle);
		
		setBounds(100,100,300,180);
		setVisible(true);
		setResizable(false);
		
	}//StatementLogin
	
	public JTextField getJtfId() {
		return jtfId;
	}

	public JPasswordField getJpfPass() {
		return jpfPass;
	}

	public JButton getJbtLogin() {
		return jbtLogin;
	}

	public JButton getJbtCancle() {
		return jbtCancle;
	}

	public static void main(String[] args) {
		new PreparedStatementLogin();
	}//main

}//class
