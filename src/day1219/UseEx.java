package day1219;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UseEx {
	
	private File file;
	private BufferedReader br;
	private String st;

	public UseEx() {
		System.out.println("디렉토리명 입력 : ");
		
		br = new BufferedReader(
				new InputStreamReader(System.in));
		
		
		try {
			st = br.readLine();
			file = new File(st);
			boolean flag = file.isDirectory();
			if(flag) {
				msgDialog();
			} else {
				System.out.println("파일은 취급하지 않습니다.");
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void msgDialog() {
		
		File[] allFileInfo = file.listFiles();
		StringBuilder fileInfo = new StringBuilder();
		fileInfo.append("이름\t\t유형\t크기\t마지막으로 수정한날짜\n");
		
		String[] allPath = new String[allFileInfo.length];
		for(int i=0; i<allFileInfo.length; i++) {
			String path = allFileInfo[i].toString();
			allPath[i]=path;
		}
		
		
		for(int i=0; i<allPath.length; i++) {
		File allFile = new File(allPath[i]);
		Date d = new Date(allFile.lastModified());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");
		fileInfo.append(allFile.getName()+"\t\t")
		.append(allFile.isFile()?"파일\t":"디렉토리\t"+"\t")
		.append(allFile.length()+"byte\t")
		.append(sdf.format(d)+"\n");
		}
		
		JTextArea jta = new JTextArea(30,40);
		jta.append(fileInfo.toString());
		JScrollPane jsp = new JScrollPane(jta);
		JOptionPane.showMessageDialog(null, jsp);
		return;
	}
	
	public static void main(String[] args) {
		new UseEx();
	}

}
