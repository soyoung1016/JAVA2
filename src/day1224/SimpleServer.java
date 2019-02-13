package day1224;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * port(1~65535중 하나)를 열고, 접속자 소켓을 받는 일
 * @author owner
 */
public class SimpleServer {
	
	public SimpleServer() throws IOException {
		//1. PORT를 열고 접속자가 들어오기를 기다린다.
		ServerSocket server = null;
		//접속자에게 보내줄 메세지
		String msg = "배고프다";
		String revMsg = ""; //접속자가 보내온 메세지를 저장
		
		//접속자에게 메세지를 보내기위한 스트림
		DataOutputStream dos = null;
		//접속자가 보내오는 메세지를 읽기 위한 스트림
		DataInputStream dis = null;
		
		try {
			server = new ServerSocket(3000);
			System.out.println("서버 가동 중 "+server);
			//3. 접속자가 들어오면 접속자 소켓을 받는다.
			while(true) {
				Socket client = server.accept();
				System.out.println("접속자 있음 : "+client);
				//4. 접속자에게 메세지를 보내기위한 스트림 연결
				dos = new DataOutputStream(client.getOutputStream());
				//5. 얻어낸 스트림에 데이터 쓰기
				dos.writeUTF(msg);
				//6. 스트림의 데이터를 목적지(소켓) 분출
				dos.flush();
				//12. 클라이언트가 보내오는 메세지를 읽기위한 스트림연결
				dis = new DataInputStream(client.getInputStream());
				//13. 스트림에서 메세지읽기
				revMsg = dis.readUTF();
				JOptionPane.showMessageDialog(null, "클라이언트의 메세지입니다.\n"+revMsg);
			}//end while
			
		} finally { //메모리누수가 발생하지 않도록 무조건 실행하게 하여 끊어줌
			if(dos != null) {
				dos.close();
			}//end if
			if(dis != null) {
				dis.close();
			}//end if
			if(server != null) {
				server.close();
			}//end if
		}//end finally
		
	}//SimpleServer

	public static void main(String[] args) {
		try {
			new SimpleServer();
		} catch (IOException e) {
			System.out.println("포트가 이미 사용 중입니다.");
			e.printStackTrace();
		}//end catch
	}//main

}//class
