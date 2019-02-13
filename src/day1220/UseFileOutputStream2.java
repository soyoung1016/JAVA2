package day1220;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * 8bit stream과 16bit stream을 사용하여 문자열 데이터를 File에 기록
 * @author owner
 */
public class UseFileOutputStream2 {

	public UseFileOutputStream2() throws IOException {
		String data = "오지고 지리고 렛잇고~!";
		
		//1. File 객체 생성 : 파일이 존재한다면 덮어쓸것인지를 판단
		File file = new File("c:/dev/temp/java_write2.txt");
		
		boolean flag = false; //파일이 없을때
		if(file.exists()) { //파일이 존재할때
			boolean[] temp = {false, true, true}; //예, 아니오, 취소
			flag = temp[JOptionPane.showConfirmDialog(null, "덮어쓰는 부분 ㅇㅈ?")];
			//[]안에는 int가 나온다 0-예, 1-아니오, 2-아니오
		}//end if
		
		if(!flag) {
			BufferedWriter bw = null;
			
			try {
				//8bit stream과 16bit stream 연결
				//2. 파일을 생성하는 스트림 : FileNotFoundException 발생 -> 폴더가 없을때
//				FileOutputStream fos = new FileOutputStream(file);
//				//3. 문자열을 쓰기위한 스트림을 연결 (속도가 느리다)
//				OutputStreamWriter osw = new OutputStreamWriter(fos);
//				//4. 느린 속도를 개선하기위한 스트림 연결
//				bw = new BufferedWriter(osw);
				
				//위에꺼 줄여서
//				bw = new BufferedWriter(new OutputStreamWriter
//						(new FileOutputStream(file)));
				
				//////////////////////16bit만 사용/////////////////////
				bw = new BufferedWriter(new FileWriter(file));
				
				bw.write(data); //스트림에 데이터를 기록
				bw.flush(); //목적지 파일로 분출
				//flush를 쓰지않더라도 close를 하면 잘 된다, 단, flush만 쓸 경우엔 연결을 끊지않아서 데이터누수가 발생
				System.out.println("파일에 기록 완료!!!!");
				
			} finally {
				//스트림이 객체가 생성되어있다면 연결 끊기
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
