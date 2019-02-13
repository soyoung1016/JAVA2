package day1220;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 16bit Stream�� ����Ͽ� ���
 * @author owner
 */
public class UseFileReader {

	public void useFileReader() throws IOException{
		BufferedReader br = null;
		
		try {
			File file = new File("c:/dev/temp/java_read.txt");
			//1. �ٿ��� ���Ͽ� ��Ʈ�� ����
			FileReader fr = new FileReader(file);
			//2. �ٴ��� �о���̴� ����� ���� ��Ʈ�� ����
			br = new BufferedReader(fr);
			
			String temp = "";
			//���پ� �о�鿩 �о���� ������ �����Ѵٸ� 
			while((temp=br.readLine())!=null) {
				System.out.println(temp); //����Ѵ�
			}
			
			
		}finally {
			//����� ��Ʈ���� ���´�.
			if(br!=null) {
				//��ü�� ��������ִٸ�
				br.close();
			}
		}
	}
	
	public static void main(String[] args) {
		UseFileReader ufr = new UseFileReader();
		try {
			ufr.useFileReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
