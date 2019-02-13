package day1219;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class UseEx2 {

	private String inputPath;
	private File file;
	private List<File> javaList;

	public UseEx2() throws NullPointerException {

		inputPath = JOptionPane.showInputDialog("��θ� �Է��ϼ���");

		file = new File(inputPath);
		boolean flag = file.isDirectory();

		if (flag) {
			choiceDel();
		} else {
			JOptionPane.showMessageDialog(null, "�߸��� ����Դϴ�.");
			return;
		}
	}// end UseEx2

	public void choiceDel() throws NullPointerException {
		
		File[] name = file.listFiles();
		javaList = new ArrayList<File>(); //list ����
		
		for(int i = 0; i < name.length; i++) {
			if(name[i].getName().contains(".java")) {
				javaList.add(name[i]);
			}
		}
		
		
		int choice = JOptionPane.showConfirmDialog
		(null, "java������ "+javaList.size()+"�� �����մϴ�. ���� �Ͻðڽ��ϱ�?");
		
		switch (choice) {
		case JOptionPane.OK_OPTION :
			for(int i=0; i<javaList.size();i++) {
				javaList.get(i).delete();
			}
			break;
		case JOptionPane.NO_OPTION :
			return;
		case JOptionPane.CANCEL_OPTION :
			return;
		}
			
	}

	public static void main(String[] args) {
		try {
			new UseEx2();
		} catch (NullPointerException e) {
			System.out.println("��ξ���");
		}
	}

}
