package kr.co.sist.chat.server.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import kr.co.sist.chat.server.helper.MultiChatServerHelper;
import kr.co.sist.chat.server.view.MultiChatServerView;

public class MultiChatServerEvt extends WindowAdapter implements ActionListener {

	private MultiChatServerView mcsv;
	private Thread threadServer1, threadServer2, threadServer3, threadServer4; //접속자에 대한 처리를 하기위한 Thread
	private ServerSocket server1, server2, server3, server4; //PORT열기
	private List<MultiChatServerHelper> listClient1, listClient2, listClient3, listClient4; //모든 접속자를 관리할 List
	
	public MultiChatServerEvt(MultiChatServerView mcsv) {
		this.mcsv = mcsv;
		listClient1 = new ArrayList<MultiChatServerHelper>();
		listClient2 = new ArrayList<MultiChatServerHelper>();
		listClient3 = new ArrayList<MultiChatServerHelper>();
		listClient4 = new ArrayList<MultiChatServerHelper>();
	}//MultiChatServerEvt

	@Override
	public void windowClosing(WindowEvent we) {
		mcsv.dispose();
	}//windowClosing
	
	@Override
	public void windowClosed(WindowEvent we) {
			try {
				if(server1 != null) {
					server1.close();	
				}//end if
				if(server2 != null) {
					server2.close();	
				}//end if
				if(server3 != null) {
					server3.close();	
				}//end if
				if(server4 != null) {
					server4.close();	
				}//end if
			} catch (IOException ie) {
				ie.printStackTrace();
			}//end catch
	}//windowClosed
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==mcsv.getJbtOpenServer()) {
			if(threadServer1 == null) {
				threadServer1 = new Thread(new Runnable() {
					
					@Override
					public void run() {
						//서버소켓을 열고 접속자 소켓을 받는다.
						try{
							server1 = new ServerSocket(35000); //포트는 0~65535개의 Port가 열릴 수 있다. 1024번대 아래에는 이미 열려있는것이 대부분.
							
							DefaultListModel<String> dlmTemp = mcsv.getDlmChatList1();
							dlmTemp.addElement("서버가 35000 PORT를 열고 가동 중...");
							
							Socket someClient = null;//반복문 안에서 변수를 선언하지 않는다, 객체 초기화값은 null.
							InetAddress ia = null;//접속자의 ip address를 얻기위한 객체
							
							MultiChatServerHelper mcsh = null;//접속자 소켓을 받아 스트림을 연결하고, 대화를 읽거나 모든 접속자에게 전송하는 일.
							for(int cnt=0;; cnt++) { //원래는 while, for로 하면 조건이 없으니 무한루프
								someClient = server1.accept();
								
								ia = someClient.getInetAddress();
//								dlmTemp.addElement("["+ia+"] 접속자 접속");
								
								//소켓, 서버의 화면, 접속 순서를 할당하여 Helper class를 생성한다.
								mcsh = new MultiChatServerHelper(someClient, dlmTemp, cnt, mcsv, listClient1, mcsv.getJspList1());
								//생성된 같은 이름의 소켓객체를 여러개 관리하기 위해 List에 추가
								listClient1.add(mcsh);
								//접속자의 메세지를 읽어들이기 위한 Thread 시작
								mcsh.start();
							}//end for
							//채팅방에 최대인원을 주고싶으면 for문에 조건을 걸면 된다. <20 이런식으로. 그게 아니고선 무한루프 돌려도 상관이 없다! 인원제한이 없어서!
							
						} catch(IOException ie) {
							ie.printStackTrace();
						}//end catch
					}
				});
				
				threadServer1.start();//start()에서 run()이 호출
				
			} else {
				JOptionPane.showMessageDialog(mcsv, "채팅서버가 이미 가동중입니다.");
			}//end else
		}//end if
		
		if(ae.getSource()==mcsv.getJbtOpenServer()) {
			if(threadServer2 == null) {
				threadServer2 = new Thread(new Runnable() {
					
					@Override
					public void run() {
						//서버소켓을 열고 접속자 소켓을 받는다.
						try{
							server2 = new ServerSocket(35001); //포트는 0~65535개의 Port가 열릴 수 있다. 1024번대 아래에는 이미 열려있는것이 대부분.
							
							DefaultListModel<String> dlmTemp = mcsv.getDlmChatList2();
							dlmTemp.addElement("서버가 35001 PORT를 열고 가동 중...");
							
							Socket someClient = null;//반복문 안에서 변수를 선언하지 않는다, 객체 초기화값은 null.
							InetAddress ia = null;//접속자의 ip address를 얻기위한 객체
							
							MultiChatServerHelper mcsh = null;//접속자 소켓을 받아 스트림을 연결하고, 대화를 읽거나 모든 접속자에게 전송하는 일.
							for(int cnt=0;; cnt++) { //원래는 while, for로 하면 조건이 없으니 무한루프
								someClient = server2.accept();
								
								ia = someClient.getInetAddress();
//								dlmTemp.addElement("["+ia+"] 접속자 접속");
								
								//소켓, 서버의 화면, 접속 순서를 할당하여 Helper class를 생성한다.
								mcsh = new MultiChatServerHelper(someClient, dlmTemp, cnt, mcsv, listClient2, mcsv.getJspList2());
								//생성된 같은 이름의 소켓객체를 여러개 관리하기 위해 List에 추가
								listClient2.add(mcsh);
								//접속자의 메세지를 읽어들이기 위한 Thread 시작
								mcsh.start();
							}//end for
							//채팅방에 최대인원을 주고싶으면 for문에 조건을 걸면 된다. <20 이런식으로. 그게 아니고선 무한루프 돌려도 상관이 없다! 인원제한이 없어서!
							
						} catch(IOException ie) {
							ie.printStackTrace();
						}//end catch
					}
				});
				
				threadServer2.start();//start()에서 run()이 호출
			} else {
				JOptionPane.showMessageDialog(mcsv, "채팅서버가 이미 가동중입니다.");
			}//end else
		}//end if
		
		if(ae.getSource()==mcsv.getJbtOpenServer()) {
			if(threadServer3 == null) {
				threadServer3 = new Thread(new Runnable() {
					
					@Override
					public void run() {
						//서버소켓을 열고 접속자 소켓을 받는다.
						try{
							server3 = new ServerSocket(35002); //포트는 0~65535개의 Port가 열릴 수 있다. 1024번대 아래에는 이미 열려있는것이 대부분.
							
							DefaultListModel<String> dlmTemp = mcsv.getDlmChatList3();
							dlmTemp.addElement("서버가 35002 PORT를 열고 가동 중...");
							
							Socket someClient = null;//반복문 안에서 변수를 선언하지 않는다, 객체 초기화값은 null.
							InetAddress ia = null;//접속자의 ip address를 얻기위한 객체
							
							MultiChatServerHelper mcsh = null;//접속자 소켓을 받아 스트림을 연결하고, 대화를 읽거나 모든 접속자에게 전송하는 일.
							for(int cnt=0;; cnt++) { //원래는 while, for로 하면 조건이 없으니 무한루프
								someClient = server3.accept();
								
								ia = someClient.getInetAddress();
//								dlmTemp.addElement("["+ia+"] 접속자 접속");
								
								//소켓, 서버의 화면, 접속 순서를 할당하여 Helper class를 생성한다.
								mcsh = new MultiChatServerHelper(someClient, dlmTemp, cnt, mcsv, listClient3, mcsv.getJspList3());
								//생성된 같은 이름의 소켓객체를 여러개 관리하기 위해 List에 추가
								listClient3.add(mcsh);
								//접속자의 메세지를 읽어들이기 위한 Thread 시작
								mcsh.start();
							}//end for
							//채팅방에 최대인원을 주고싶으면 for문에 조건을 걸면 된다. <20 이런식으로. 그게 아니고선 무한루프 돌려도 상관이 없다! 인원제한이 없어서!
							
						} catch(IOException ie) {
							ie.printStackTrace();
						}//end catch
					}
				});
				threadServer3.start();//start()에서 run()이 호출
			} else {
				JOptionPane.showMessageDialog(mcsv, "채팅서버가 이미 가동중입니다.");
			}//end else
		}//end if
		
		if(ae.getSource()==mcsv.getJbtOpenServer()) {
			if(threadServer4 == null) {
				threadServer4 = new Thread(new Runnable() {
					
					@Override
					public void run() {
						//서버소켓을 열고 접속자 소켓을 받는다.
						try{
							server4 = new ServerSocket(35003); //포트는 0~65535개의 Port가 열릴 수 있다. 1024번대 아래에는 이미 열려있는것이 대부분.
							
							DefaultListModel<String> dlmTemp = mcsv.getDlmChatList4();
							dlmTemp.addElement("서버가 35003 PORT를 열고 가동 중...");
							
							Socket someClient = null;//반복문 안에서 변수를 선언하지 않는다, 객체 초기화값은 null.
							InetAddress ia = null;//접속자의 ip address를 얻기위한 객체
							
							MultiChatServerHelper mcsh = null;//접속자 소켓을 받아 스트림을 연결하고, 대화를 읽거나 모든 접속자에게 전송하는 일.
							for(int cnt=0;; cnt++) { //원래는 while, for로 하면 조건이 없으니 무한루프
								someClient = server4.accept();
								
								ia = someClient.getInetAddress();
//								dlmTemp.addElement("["+ia+"] 접속자 접속");
								
								//소켓, 서버의 화면, 접속 순서를 할당하여 Helper class를 생성한다.
								mcsh = new MultiChatServerHelper(someClient, dlmTemp, cnt, mcsv, listClient4, mcsv.getJspList4());
								//생성된 같은 이름의 소켓객체를 여러개 관리하기 위해 List에 추가
								listClient4.add(mcsh);
								//접속자의 메세지를 읽어들이기 위한 Thread 시작
								mcsh.start();
							}//end for
							//채팅방에 최대인원을 주고싶으면 for문에 조건을 걸면 된다. <20 이런식으로. 그게 아니고선 무한루프 돌려도 상관이 없다! 인원제한이 없어서!
							
						} catch(IOException ie) {
							ie.printStackTrace();
						}//end catch
					}
				});
				threadServer4.start();//start()에서 run()이 호출
			} else {
				JOptionPane.showMessageDialog(mcsv, "채팅서버가 이미 가동중입니다.");
			}//end else
		}//end if
		
		if(ae.getSource()==mcsv.getJbtCloseServer()) {
			
			switch(JOptionPane.showConfirmDialog(mcsv, "채팅서버를 종료하시겟습니까?\n 종료하시면 모든 접속자의 연결이 끊어집니다.")){
			case JOptionPane.OK_OPTION : mcsv.dispose();
			}//end switch
		}//end if
	}//actionPerformed
}//class
