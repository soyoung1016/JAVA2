package day1220;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * 8bit stream�� 16bit stream�� ����Ͽ� ���ڿ� �����͸� File�� ���
 * @author owner
 */
public class UseFileOutputStream2 {

	public UseFileOutputStream2() throws IOException {
		String data = "������ ������ ���հ�~!";
		
		//1. File ��ü ���� : ������ �����Ѵٸ� ����������� �Ǵ�
		File file = new File("c:/dev/temp/java_write2.txt");
		
		boolean flag = false; //������ ������
		if(file.exists()) { //������ �����Ҷ�
			boolean[] temp = {false, true, true}; //��, �ƴϿ�, ���
			flag = temp[JOptionPane.showConfirmDialog(null, "����� �κ� ����?")];
			//[]�ȿ��� int�� ���´� 0-��, 1-�ƴϿ�, 2-�ƴϿ�
		}//end if
		
		if(!flag) {
			BufferedWriter bw = null;
			
			try {
				//8bit stream�� 16bit stream ����
				//2. ������ �����ϴ� ��Ʈ�� : FileNotFoundException �߻� -> ������ ������
//				FileOutputStream fos = new FileOutputStream(file);
//				//3. ���ڿ��� �������� ��Ʈ���� ���� (�ӵ��� ������)
//				OutputStreamWriter osw = new OutputStreamWriter(fos);
//				//4. ���� �ӵ��� �����ϱ����� ��Ʈ�� ����
//				bw = new BufferedWriter(osw);
				
				//������ �ٿ���
//				bw = new BufferedWriter(new OutputStreamWriter
//						(new FileOutputStream(file)));
				
				//////////////////////16bit�� ���/////////////////////
				bw = new BufferedWriter(new FileWriter(file));
				
				bw.write(data); //��Ʈ���� �����͸� ���
				bw.flush(); //������ ���Ϸ� ����
				//flush�� �����ʴ��� close�� �ϸ� �� �ȴ�, ��, flush�� �� ��쿣 ������ �����ʾƼ� �����ʹ����� �߻�
				System.out.println("���Ͽ� ��� �Ϸ�!!!!");
				
			} finally {
				//��Ʈ���� ��ü�� �����Ǿ��ִٸ� ���� ����
				if(bw != null) {
					bw.close();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		try {
			new UseFileOutputStream2();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
	}//end main

}//end class
