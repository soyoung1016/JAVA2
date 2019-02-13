package day1220;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 16bit Stream만 사용하여 출력
 * @author owner
 */
public class UseFileReader {

	public void useFileReader() throws IOException{
		BufferedReader br = null;
		
		try {
			File file = new File("c:/dev/temp/java_read.txt");
			//1. 근원지 파일에 스트림 연결
			FileReader fr = new FileReader(file);
			//2. 줄단위 읽어들이는 기능을 가진 스트림 연결
			br = new BufferedReader(fr);
			
			String temp = "";
			//한줄씩 읽어들여 읽어들인 내용이 존재한다면 
			while((temp=br.readLine())!=null) {
				System.out.println(temp); //출력한다
			}
			
			
		}finally {
			//연결된 스트림을 끊는다.
			if(br!=null) {
				//객체가 만들어져있다면
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
