package day0109;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RunTableCreate extends JFrame{
	private JTextField jtfTabName, jtfColumnName, jtfSize, jtfConName;
	private JButton jbTabNameAdd, jbConNameAdd, jbCreate, jbReset;
	private JComboBox<String> jcbDataType, jcbConstraint;
	private JTextArea jtaQueryView;
	
	public RunTableCreate() {
		super("���̺� �߰��ϱ�");
		
		JLabel jlTableName = new JLabel("���̺��");
		jtfTabName = new JTextField(8);
		jbTabNameAdd = new JButton("�߰�");
		
		JLabel jlColumnName = new JLabel("�÷���");
		jtfColumnName = new JTextField(8);
		
		JLabel jlDataType = new JLabel("��������");
		String[] DataType = {"varchar2", "char", "number", "date"};
		jcbDataType = new JComboBox<String>(DataType);
		
		JLabel jlSize = new JLabel("ũ��");
		jtfSize = new JTextField(5);
		
		JLabel jlConstraint = new JLabel("�������");
		String[] Constraint = {"null", "PrimaryKey", "unique", "not null"};
		jcbConstraint = new JComboBox<String>(Constraint);
		
		JLabel jlConName = new JLabel("������� ��");
		jtfConName = new JTextField(10);
		jbConNameAdd = new JButton("�߰�");
		
		jtaQueryView = new JTextArea();
		jtaQueryView.setSize(100, 100);
		jtaQueryView.setFont(new Font("Sanserif",0,17));
		jbCreate = new JButton("���̺� ����");
		jbReset = new JButton("�ʱ�ȭ");
		
		JPanel All = new JPanel(new GridLayout(3, 1));
		
		JPanel Table = new JPanel();
		JPanel Column = new JPanel();
		JPanel Con = new JPanel();
		Table.add(jlTableName);
		Table.add(jtfTabName);
		Table.add(jbTabNameAdd);
		
		Column.add(jlColumnName);
		Column.add(jtfColumnName);
		Column.add(jlDataType);
		Column.add(jcbDataType);
		Column.add(jlSize);
		Column.add(jtfSize);
		
		Con.add(jlConstraint);
		Con.add(jcbConstraint);
		Con.add(jlConName);
		Con.add(jtfConName);
		Con.add(jbConNameAdd);
		
		All.add(Table);
		All.add(Column);
		All.add(Con);
		
		JPanel Button = new JPanel();
		Button.add(jbCreate);
		Button.add(jbReset);
		
		add("North", All);
		add("Center",jtaQueryView);
		add("South", Button);
		
		//�̺�Ʈ ���
		TableCreateEvt tce = new TableCreateEvt(this);
		addWindowListener(tce);
		//jbTabNameAdd, jbConNameAdd, jbCreate, jbReset;
		jbTabNameAdd.addActionListener(tce);
		jbConNameAdd.addActionListener(tce);
		jbCreate.addActionListener(tce);
		jbReset.addActionListener(tce);
		
//		jtaQueryView.setEditable(false);
		setBounds(100,100,470,500);
		setVisible(true);
		setResizable(false);
		
	}//RunTableCreate
	
	public JTextField getJtfTabName() {
		return jtfTabName;
	}

	public JTextField getJtfColumnName() {
		return jtfColumnName;
	}

	public JTextField getJtfSize() {
		return jtfSize;
	}

	public JTextField getJtfConName() {
		return jtfConName;
	}

	public JButton getJbTabNameAdd() {
		return jbTabNameAdd;
	}

	public JButton getJbConNameAdd() {
		return jbConNameAdd;
	}

	public JButton getJbCreate() {
		return jbCreate;
	}

	public JButton getJbReset() {
		return jbReset;
	}

	public JComboBox<String> getJcbDataType() {
		return jcbDataType;
	}

	public JComboBox<String> getJcbConstraint() {
		return jcbConstraint;
	}

	public JTextArea getJtaQueryView() {
		return jtaQueryView;
	}

	public static void main(String[] args) {
		new RunTableCreate();
	}//main
	
}//class
