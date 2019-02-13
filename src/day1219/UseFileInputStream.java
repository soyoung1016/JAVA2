package day1219;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * HDD�� �����ϴ� File�� �����Ͽ� �� ������ �о���϶� ����ϴ� FileInputStream
 * @author owner
 */
public class UseFileInputStream {
	
	public UseFileInputStream() throws IOException{
		File file = new File("c:/dev/temp/java_read.txt");
		if(file.exists()) {
			BufferedReader br=null;
			try {
				//��Ʈ���� �����Ͽ� JVM���� HDD�� ���ϰ� ����
//				FileInputStream fis = new FileInputStream(file);
//				int temp=0;
//				
//					while((temp=fis.read()) != -1) { //�о���� ������ �����Ѵٸ�
//						System.out.print((char)temp);
//					}
//				//��Ʈ������� �Ϸ������� ��Ʈ���� ��� �޸𸮴����� ���´�.
//					fis.close();
					
				//////////////////////////12-20-2018 �ڵ� �߰� ///////////////////////////
				//8bit stream�� 16bit stream ���� : �ѱ��� ������ �����ذ�
//				FileInputStream fis = new FileInputStream(file); //���ϰ� ����
//				InputStreamReader isr = new InputStreamReader(fis); //8bit+16bit ����
//				br = new BufferedReader(isr); //�ӵ�����, �ٴ����� �б�
//				//Buffer��� �ϴ°� ���� ���� �� �ӵ������̴�! //������ java���� next�� ���� �����Ϳ� ���õȰ��̴�.
				
				//��ϸӽ� ���~!~!
				
				br=new BufferedReader(new InputStreamReader(
						new FileInputStream(file)));
				
				String temp = "";
				while((temp=br.readLine()) != null) { //�ٴ��� (\n������)�� �о �о���� ������ �ִٸ�
					System.out.println(temp); //���
					
				}
				
			} finally {
					if(br != null) {//���۰� ��ü�� ��������ִٸ� ������ ���ڴ�.
						br.close();
					}
				} //�ݵ�� ������ ����//br�� ������ try�ȿ� �����Ƿ� ���������� try�ȿ����� ���� �ִ�.
				//�ۿ��� �����ϸ� �ۿ��� ����� �� �ִ�.
				
		} else {
			System.out.println("��γ� ���ϸ��� Ȯ���ϼ���");
		}
	}

	public static void main(String[] args) {
		try {
			new UseFileInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
