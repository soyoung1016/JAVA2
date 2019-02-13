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
	private Thread threadServer1, threadServer2, threadServer3, threadServer4; //�����ڿ� ���� ó���� �ϱ����� Thread
	private ServerSocket server1, server2, server3, server4; //PORT����
	private List<MultiChatServerHelper> listClient1, listClient2, listClient3, listClient4; //��� �����ڸ� ������ List
	
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
						//���������� ���� ������ ������ �޴´�.
						try{
							server1 = new ServerSocket(35000); //��Ʈ�� 0~65535���� Port�� ���� �� �ִ�. 1024���� �Ʒ����� �̹� �����ִ°��� ��κ�.
							
							DefaultListModel<String> dlmTemp = mcsv.getDlmChatList1();
							dlmTemp.addElement("������ 35000 PORT�� ���� ���� ��...");
							
							Socket someClient = null;//�ݺ��� �ȿ��� ������ �������� �ʴ´�, ��ü �ʱ�ȭ���� null.
							InetAddress ia = null;//�������� ip address�� ������� ��ü
							
							MultiChatServerHelper mcsh = null;//������ ������ �޾� ��Ʈ���� �����ϰ�, ��ȭ�� �аų� ��� �����ڿ��� �����ϴ� ��.
							for(int cnt=0;; cnt++) { //������ while, for�� �ϸ� ������ ������ ���ѷ���
								someClient = server1.accept();
								
								ia = someClient.getInetAddress();
//								dlmTemp.addElement("["+ia+"] ������ ����");
								
								//����, ������ ȭ��, ���� ������ �Ҵ��Ͽ� Helper class�� �����Ѵ�.
								mcsh = new MultiChatServerHelper(someClient, dlmTemp, cnt, mcsv, listClient1, mcsv.getJspList1());
								//������ ���� �̸��� ���ϰ�ü�� ������ �����ϱ� ���� List�� �߰�
								listClient1.add(mcsh);
								//�������� �޼����� �о���̱� ���� Thread ����
								mcsh.start();
							}//end for
							//ä�ù濡 �ִ��ο��� �ְ������ for���� ������ �ɸ� �ȴ�. <20 �̷�������. �װ� �ƴϰ� ���ѷ��� ������ ����� ����! �ο������� ���!
							
						} catch(IOException ie) {
							ie.printStackTrace();
						}//end catch
					}
				});
				
				threadServer1.start();//start()���� run()�� ȣ��
				
			} else {
				JOptionPane.showMessageDialog(mcsv, "ä�ü����� �̹� �������Դϴ�.");
			}//end else
		}//end if
		
		if(ae.getSource()==mcsv.getJbtOpenServer()) {
			if(threadServer2 == null) {
				threadServer2 = new Thread(new Runnable() {
					
					@Override
					public void run() {
						//���������� ���� ������ ������ �޴´�.
						try{
							server2 = new ServerSocket(35001); //��Ʈ�� 0~65535���� Port�� ���� �� �ִ�. 1024���� �Ʒ����� �̹� �����ִ°��� ��κ�.
							
							DefaultListModel<String> dlmTemp = mcsv.getDlmChatList2();
							dlmTemp.addElement("������ 35001 PORT�� ���� ���� ��...");
							
							Socket someClient = null;//�ݺ��� �ȿ��� ������ �������� �ʴ´�, ��ü �ʱ�ȭ���� null.
							InetAddress ia = null;//�������� ip address�� ������� ��ü
							
							MultiChatServerHelper mcsh = null;//������ ������ �޾� ��Ʈ���� �����ϰ�, ��ȭ�� �аų� ��� �����ڿ��� �����ϴ� ��.
							for(int cnt=0;; cnt++) { //������ while, for�� �ϸ� ������ ������ ���ѷ���
								someClient = server2.accept();
								
								ia = someClient.getInetAddress();
//								dlmTemp.addElement("["+ia+"] ������ ����");
								
								//����, ������ ȭ��, ���� ������ �Ҵ��Ͽ� Helper class�� �����Ѵ�.
								mcsh = new MultiChatServerHelper(someClient, dlmTemp, cnt, mcsv, listClient2, mcsv.getJspList2());
								//������ ���� �̸��� ���ϰ�ü�� ������ �����ϱ� ���� List�� �߰�
								listClient2.add(mcsh);
								//�������� �޼����� �о���̱� ���� Thread ����
								mcsh.start();
							}//end for
							//ä�ù濡 �ִ��ο��� �ְ������ for���� ������ �ɸ� �ȴ�. <20 �̷�������. �װ� �ƴϰ� ���ѷ��� ������ ����� ����! �ο������� ���!
							
						} catch(IOException ie) {
							ie.printStackTrace();
						}//end catch
					}
				});
				
				threadServer2.start();//start()���� run()�� ȣ��
			} else {
				JOptionPane.showMessageDialog(mcsv, "ä�ü����� �̹� �������Դϴ�.");
			}//end else
		}//end if
		
		if(ae.getSource()==mcsv.getJbtOpenServer()) {
			if(threadServer3 == null) {
				threadServer3 = new Thread(new Runnable() {
					
					@Override
					public void run() {
						//���������� ���� ������ ������ �޴´�.
						try{
							server3 = new ServerSocket(35002); //��Ʈ�� 0~65535���� Port�� ���� �� �ִ�. 1024���� �Ʒ����� �̹� �����ִ°��� ��κ�.
							
							DefaultListModel<String> dlmTemp = mcsv.getDlmChatList3();
							dlmTemp.addElement("������ 35002 PORT�� ���� ���� ��...");
							
							Socket someClient = null;//�ݺ��� �ȿ��� ������ �������� �ʴ´�, ��ü �ʱ�ȭ���� null.
							InetAddress ia = null;//�������� ip address�� ������� ��ü
							
							MultiChatServerHelper mcsh = null;//������ ������ �޾� ��Ʈ���� �����ϰ�, ��ȭ�� �аų� ��� �����ڿ��� �����ϴ� ��.
							for(int cnt=0;; cnt++) { //������ while, for�� �ϸ� ������ ������ ���ѷ���
								someClient = server3.accept();
								
								ia = someClient.getInetAddress();
//								dlmTemp.addElement("["+ia+"] ������ ����");
								
								//����, ������ ȭ��, ���� ������ �Ҵ��Ͽ� Helper class�� �����Ѵ�.
								mcsh = new MultiChatServerHelper(someClient, dlmTemp, cnt, mcsv, listClient3, mcsv.getJspList3());
								//������ ���� �̸��� ���ϰ�ü�� ������ �����ϱ� ���� List�� �߰�
								listClient3.add(mcsh);
								//�������� �޼����� �о���̱� ���� Thread ����
								mcsh.start();
							}//end for
							//ä�ù濡 �ִ��ο��� �ְ������ for���� ������ �ɸ� �ȴ�. <20 �̷�������. �װ� �ƴϰ� ���ѷ��� ������ ����� ����! �ο������� ���!
							
						} catch(IOException ie) {
							ie.printStackTrace();
						}//end catch
					}
				});
				threadServer3.start();//start()���� run()�� ȣ��
			} else {
				JOptionPane.showMessageDialog(mcsv, "ä�ü����� �̹� �������Դϴ�.");
			}//end else
		}//end if
		
		if(ae.getSource()==mcsv.getJbtOpenServer()) {
			if(threadServer4 == null) {
				threadServer4 = new Thread(new Runnable() {
					
					@Override
					public void run() {
						//���������� ���� ������ ������ �޴´�.
						try{
							server4 = new ServerSocket(35003); //��Ʈ�� 0~65535���� Port�� ���� �� �ִ�. 1024���� �Ʒ����� �̹� �����ִ°��� ��κ�.
							
							DefaultListModel<String> dlmTemp = mcsv.getDlmChatList4();
							dlmTemp.addElement("������ 35003 PORT�� ���� ���� ��...");
							
							Socket someClient = null;//�ݺ��� �ȿ��� ������ �������� �ʴ´�, ��ü �ʱ�ȭ���� null.
							InetAddress ia = null;//�������� ip address�� ������� ��ü
							
							MultiChatServerHelper mcsh = null;//������ ������ �޾� ��Ʈ���� �����ϰ�, ��ȭ�� �аų� ��� �����ڿ��� �����ϴ� ��.
							for(int cnt=0;; cnt++) { //������ while, for�� �ϸ� ������ ������ ���ѷ���
								someClient = server4.accept();
								
								ia = someClient.getInetAddress();
//								dlmTemp.addElement("["+ia+"] ������ ����");
								
								//����, ������ ȭ��, ���� ������ �Ҵ��Ͽ� Helper class�� �����Ѵ�.
								mcsh = new MultiChatServerHelper(someClient, dlmTemp, cnt, mcsv, listClient4, mcsv.getJspList4());
								//������ ���� �̸��� ���ϰ�ü�� ������ �����ϱ� ���� List�� �߰�
								listClient4.add(mcsh);
								//�������� �޼����� �о���̱� ���� Thread ����
								mcsh.start();
							}//end for
							//ä�ù濡 �ִ��ο��� �ְ������ for���� ������ �ɸ� �ȴ�. <20 �̷�������. �װ� �ƴϰ� ���ѷ��� ������ ����� ����! �ο������� ���!
							
						} catch(IOException ie) {
							ie.printStackTrace();
						}//end catch
					}
				});
				threadServer4.start();//start()���� run()�� ȣ��
			} else {
				JOptionPane.showMessageDialog(mcsv, "ä�ü����� �̹� �������Դϴ�.");
			}//end else
		}//end if
		
		if(ae.getSource()==mcsv.getJbtCloseServer()) {
			
			switch(JOptionPane.showConfirmDialog(mcsv, "ä�ü����� �����Ͻðٽ��ϱ�?\n �����Ͻø� ��� �������� ������ �������ϴ�.")){
			case JOptionPane.OK_OPTION : mcsv.dispose();
			}//end switch
		}//end if
	}//actionPerformed
}//class
