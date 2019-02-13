package day0111;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class UseTableImage extends JFrame {

	public UseTableImage() {
		super("������Ʈ");
		
		String[] columnNames = {"�ΰ�", "URL", "Ư¡"};
		String path = "C:/dev/workspace/javase_prj2/src/day0111/images/";
		Object[][] info = new Object[3][3];
		info[0][0] = new ImageIcon(path+"daum.png");
		info[0][1] = "http://www.daum.net";
		info[0][2] = "īī��";
		
		info[1][0] = new ImageIcon(path+"naver.png");
		info[1][1] = "http://www.naver.com";
		info[1][2] = "���̹�";
		
		info[2][0] = new ImageIcon(path+"google.png");
		info[2][1] = "http://www.google.com";
		info[2][2] = "�˻��� ��";
		
		DefaultTableModel dtm = new DefaultTableModel(info, columnNames) {
			//�ȿ� �Ű������� �˾Ƽ� �θ��� �ڿ�?�̶�� �ؾ��ϳ�.. �װ� �˾Ƽ� �޾ƿ��µ�.
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		//ImageIcon ��ü�� �־ �̹��� ����� �ƴϰ� �ּҰ� ������ ������?
		///////////////////////////������Ʈ 190111 Ȯ��////////////////////
		//�Էµ� Ŭ������ �״�� Cell(Columns)�� ǥ���ǵ��� method Override
		//getColumnClass(int column)
		JTable jtbWeb = new JTable(dtm) {
			@Override
			public Class getColumnClass(int column) {
				//row - JTable�� �Էµ� ������ �迭�� �࿡ ���Ѵٸ� �ٲ۴�.
				//row�� �Էµ� ���� ������ �迭�� ���ϱ⸸ �Ѵٸ� �� �ֵ� �� �ٲ۴�. ������ �ȹٲ�
				//��� �÷��� ���� �Էµ� ������ ��ȯ�Ѵ�.
				return getValueAt(0, column).getClass();
			}
		};	
		jtbWeb.getTableHeader().setReorderingAllowed(false); //�÷��� �̵� ����
		jtbWeb.getTableHeader().setResizingAllowed(false); //�÷��� ũ�� ���� ����
		jtbWeb.setRowHeight(80);
		jtbWeb.getColumnModel().getColumn(0).setPreferredWidth(150);
		jtbWeb.getColumnModel().getColumn(1).setPreferredWidth(200);
		jtbWeb.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		JScrollPane jsp = new JScrollPane(jtbWeb);
		
		add("Center", jsp);
		
		setBounds(100,100,550,340);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//UseTableImage
	
	public static void main(String[] args) {
		new UseTableImage();
	}//main

}//class
