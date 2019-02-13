package day0114;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * 접속자가 보내오는 파일을 저장하는 서버
 * @author owner
 */
@SuppressWarnings("serial")
public class FileServer extends JFrame implements Runnable, ActionListener{//Runnable은 무한반복으로 파일을 보낼 때마다 읽을 수 있게 해준다.
	
	private DefaultListModel<String> dlmFileList;
	private JButton btnStartServer;
	private Thread threadServer;
	private ServerSocket socketServerFile;
	
	
	public FileServer() {
		super("파일 서버");
		dlmFileList = new DefaultListModel<String>();
		JList<String> listFile = new JList<String>(dlmFileList);	//List에 값 넣는 방법은 defaultList 만들어서 JList에 넣기
		btnStartServer = new JButton("서버가동");
		
		JScrollPane jsp = new JScrollPane(listFile);
		jsp.setBorder(new TitledBorder("파일목록"));
		
		JPanel panel = new JPanel();
		panel.add(btnStartServer);
		
		add("Center", jsp);
		add("South", panel);
		
		btnStartServer.addActionListener(this);
		
		setBounds(100,100,400,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//FileServer

	@Override
	public void run() {
		try {
			Socket socketClient = null;
			DataInputStream dis = null;
			StringBuilder fileName = new StringBuilder();
			int dataCnt = 0;
			FileOutputStream fos = null;
			File file = null;
			
			byte[] readData = null;
			int dataLen = 0;
			try {
				while(true) {
					//접속자 소켓이 존재함.
					socketClient = socketServerFile.accept();
					
					//2. 클라이언트가 전송하는 파일명 저장
					dis = new DataInputStream(socketClient.getInputStream());
					fileName.delete(0, fileName.length());	//파일명이 기존의 이름과 겹칠일이 없다. 지우고 다시 받아서...그런가봄...
					fileName.append(dis.readUTF());
					
					//파일명을 겹치지 않도록 변경
					fileName.insert(fileName.lastIndexOf("."), "_".concat(String.valueOf(System.currentTimeMillis())));
					dlmFileList.addElement(fileName.toString());
					
					//4. 클라이언트가 전송하는 배열의 갯수(읽어들일 횟수)를 받는다.
					dataCnt = dis.readInt();
//					dlmFileList.addElement(String.valueOf(dataCnt));
//					System.out.println("서버" +dataCnt);
					
					//6. 전송횟수만큼 읽어들여 파일로 출력한다.
					file = new File("C:/dev/workspace/javase_prj2/src/day0114/image/"+fileName);
					
					fos = new FileOutputStream(file);
					readData = new byte[512];
					
					while(dataCnt>0) {
						dataLen = dis.read(readData); //클라이언트가 전송하는 배열의 크기만큼을 받아서 
						fos.write(readData,0,dataLen); //파일에 기록
						fos.flush();
						dataCnt--;
					}//end if
					
				}//end while
			} finally {
				if(fos != null) {
					fos.close();
				}//end if
				if(dis != null) {
					dis.close();
				}//end if
				if(socketClient != null) {
					socketClient.close();
				}//end if
			}//end finally
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
	}//run
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(threadServer == null) {
			try {
				socketServerFile = new ServerSocket(10000);
				dlmFileList.addElement("서버가 10000포트로 실행되었습니다.");
				threadServer = new Thread(this);
				threadServer.start();
			} catch (IOException se) {
				JOptionPane.showMessageDialog(this, "포트가 이미 사용중입니다.");
				se.printStackTrace();
			}//end catch
		} else {
			JOptionPane.showMessageDialog(this, "서버가 이미 동작 중입니다.");
		}//end else
	}//actionPerformed
	
	public static void main(String[] args) {
		new FileServer();
	}

}//class
