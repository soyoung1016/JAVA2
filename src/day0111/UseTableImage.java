package day0111;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class UseTableImage extends JFrame {

	public UseTableImage() {
		super("웹사이트");
		
		String[] columnNames = {"로고", "URL", "특징"};
		String path = "C:/dev/workspace/javase_prj2/src/day0111/images/";
		Object[][] info = new Object[3][3];
		info[0][0] = new ImageIcon(path+"daum.png");
		info[0][1] = "http://www.daum.net";
		info[0][2] = "카카오";
		
		info[1][0] = new ImageIcon(path+"naver.png");
		info[1][1] = "http://www.naver.com";
		info[1][2] = "네이버";
		
		info[2][0] = new ImageIcon(path+"google.png");
		info[2][1] = "http://www.google.com";
		info[2][2] = "검색의 왕";
		
		DefaultTableModel dtm = new DefaultTableModel(info, columnNames) {
			//안에 매개변수는 알아서 부모의 자원?이라고 해야하나.. 그걸 알아서 받아오는듯.
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		//ImageIcon 객체를 넣어도 이미지 출력이 아니고 주소가 나오는 이유는?
		///////////////////////////에버노트 190111 확인////////////////////
		//입력된 클래스가 그대로 Cell(Columns)에 표현되도록 method Override
		//getColumnClass(int column)
		JTable jtbWeb = new JTable(dtm) {
			@Override
			public Class getColumnClass(int column) {
				//row - JTable에 입력된 이차원 배열의 행에 속한다면 바꾼다.
				//row에 입력된 값이 이차원 배열에 속하기만 한다면 뭘 넣든 다 바꾼다. 없으면 안바뀜
				//모든 컬럼의 값을 입력된 형으로 반환한다.
				return getValueAt(0, column).getClass();
			}
		};	
		jtbWeb.getTableHeader().setReorderingAllowed(false); //컬럼의 이동 막기
		jtbWeb.getTableHeader().setResizingAllowed(false); //컬럼의 크기 변경 막기
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
