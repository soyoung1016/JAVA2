package day0107;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class TableView extends JFrame{
	private JButton jbtSearch;
	private JComboBox<String> jcbAllTname;
	private DefaultTableModel dtmTable;
	
	public TableView() {
		super("���̺� �˻�");
		
		jcbAllTname = new JComboBox<String>();
		jbtSearch = new JButton("�˻�");

		String[] columnNames = {"�÷���", "��������", "ũ��", "�������"};
		dtmTable = new DefaultTableModel(columnNames, 5);
		JTable tabInfo = new JTable(dtmTable);
		
		tabInfo.getTableHeader().setReorderingAllowed(false); //�÷� �̵� ����
		tabInfo.setRowHeight(25); //���� ũ��
		tabInfo.getColumnModel().getColumn(0).setPreferredWidth(90);
		tabInfo.getColumnModel().getColumn(1).setPreferredWidth(90);
		tabInfo.getColumnModel().getColumn(2).setPreferredWidth(70);
		tabInfo.getColumnModel().getColumn(3).setPreferredWidth(230);
		
		JScrollPane jsp = new JScrollPane(tabInfo);
		
		JPanel panelNorth = new JPanel();
		panelNorth.add(new JLabel("���̺� ����"));
		panelNorth.add(jcbAllTname);
		panelNorth.add(jbtSearch);
		
		add("North", panelNorth);
		add("Center", jsp);
		
		TableViewEvt tve = new TableViewEvt(this);
		addWindowListener(tve);
		jbtSearch.addActionListener(tve);
		
		setBounds(100,100,500,500);
		setVisible(true);
		setResizable(false);
		
	}//ZipcodeView

	public JComboBox<String> getJcbAllTname() {
		return jcbAllTname;
	}

	public JButton getJbtSearch() {
		return jbtSearch;
	}
	
	public DefaultTableModel getDtmTable() {
		return dtmTable;
	}

	public static void main(String[] args) {
		new TableView();
	}//main
	
}//class
