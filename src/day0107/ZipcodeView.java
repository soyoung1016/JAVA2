package day0107;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ZipcodeView extends JFrame{

	private JTextField jtfDong;
	private JButton jbtSearch;
	private DefaultTableModel dtmZipcode;	//데이터를 주고받기 위해서
	
	public ZipcodeView() {
		super("지번주소 검색");
		
		jtfDong = new JTextField(10);
		jbtSearch = new JButton("검색");
		
		String[] columnNames = {"우편번호", "주소"};
		dtmZipcode = new DefaultTableModel(columnNames, 5);
		JTable tabZipcode = new JTable(dtmZipcode);
		
		tabZipcode.getTableHeader().setReorderingAllowed(false); //컬럼 이동 막기
		tabZipcode.setRowHeight(25); //행의 크기
		tabZipcode.getColumnModel().getColumn(0).setPreferredWidth(70);
		tabZipcode.getColumnModel().getColumn(1).setPreferredWidth(400);
		
		JScrollPane jsp = new JScrollPane(tabZipcode);
		
		JPanel panelNorth = new JPanel();
		panelNorth.add(new JLabel("동 이름"));
		panelNorth.add(jtfDong);
		panelNorth.add(jbtSearch);
		
		add("North", panelNorth);
		add("Center", jsp);
		
		ZipcodeViewEvt zve = new ZipcodeViewEvt(this);
		addWindowListener(zve);
		jtfDong.addActionListener(zve);
		jbtSearch.addActionListener(zve);
		
		setBounds(100,100,470,500);
		setVisible(true);
		setResizable(false);
		
	}//ZipcodeView
	
	public JTextField getJtfDong() {
		return jtfDong;
	}

	public JButton getJbtSearch() {
		return jbtSearch;
	}

	public DefaultTableModel getDtmZipcode() {
		return dtmZipcode;
	}

	public static void main(String[] args) {
		new ZipcodeView();
	}//view

}//class
