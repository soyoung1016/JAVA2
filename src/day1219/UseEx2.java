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

		inputPath = JOptionPane.showInputDialog("경로를 입력하세요");

		file = new File(inputPath);
		boolean flag = file.isDirectory();

		if (flag) {
			choiceDel();
		} else {
			JOptionPane.showMessageDialog(null, "잘못된 경로입니다.");
			return;
		}
	}// end UseEx2

	public void choiceDel() throws NullPointerException {
		
		File[] name = file.listFiles();
		javaList = new ArrayList<File>(); //list 생성
		
		for(int i = 0; i < name.length; i++) {
			if(name[i].getName().contains(".java")) {
				javaList.add(name[i]);
			}
		}
		
		
		int choice = JOptionPane.showConfirmDialog
		(null, "java파일이 "+javaList.size()+"개 존재합니다. 삭제 하시겠습니까?");
		
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
			System.out.println("경로없음");
		}
	}

}
